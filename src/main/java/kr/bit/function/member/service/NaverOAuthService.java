package kr.bit.function.member.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.ibankapp.base.exception.BaseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
@PropertySource("classpath:properties/application.properties")

@Service
public class NaverOAuthService {
    @Value("${naver.clientId}")
    private String clientId;

    @Value("${naver.clientSecret}")
    private String clientSecret;

    @Value("${naver.redirectUri}")
    private String redirectUri;
    // 네이버 OAuth 인증 코드 받기
    public String getNaverAccessToken(String code) {
        String access_Token = "";
        String refresh_Token = "";
        String reqURL = "https://nid.naver.com/oauth2.0/token";

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // POST 요청을 위해 기본값이 false인 setDoOutput을 true로
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            // POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=").append(URLEncoder.encode(clientId, "UTF-8"));
            sb.append("&client_secret=").append(URLEncoder.encode(clientSecret, "UTF-8"));
            sb.append("&redirect_uri=").append(URLEncoder.encode(redirectUri, "UTF-8"));
            sb.append("&code=").append(URLEncoder.encode(code, "UTF-8"));
            bw.write(sb.toString());
            bw.flush();

            // 결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            // 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();

            // Gson 라이브러리에 포함된 클래스로 JSON 파싱 객체 생성
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(response.toString());

            access_Token = element.getAsJsonObject().get("access_token").getAsString();

            System.out.println("access_token : " + access_Token);

            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return access_Token;
    }

    // 네이버 사용자 정보 가져오기
    public JsonObject getNaverUserInfo(String token) throws BaseException {
        String reqURL = "https://openapi.naver.com/v1/nid/me";
        JsonObject userInfo = new JsonObject();

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "Bearer " + token); // 전송할 header 작성, access_token 전송

            // 결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            // 요청을 통해 얻은 JSON 타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();

            // Gson 라이브러리로 JSON 파싱
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(response.toString());

            // 사용자 정보 파싱
            JsonObject naverAccount = element.getAsJsonObject().get("response").getAsJsonObject();
            String id = naverAccount.get("id").getAsString();
            String nickname = naverAccount.get("nickname").getAsString();
            String profileImage = naverAccount.get("profile_image").getAsString();
            String email = naverAccount.get("email").getAsString();
            String name = naverAccount.get("name").getAsString();
            String gender = naverAccount.get("gender").getAsString();
            String birthday = naverAccount.get("birthday").getAsString();
            String birthyear = naverAccount.get("birthyear").getAsString();
            String age = naverAccount.get("age").getAsString();

            // 사용자 정보를 JsonObject에 담기
            userInfo.addProperty("id", id);
            userInfo.addProperty("nickname", nickname);
            userInfo.addProperty("profileImage", profileImage);
            userInfo.addProperty("email", email);
            userInfo.addProperty("name", name);
            userInfo.addProperty("gender", gender);
            userInfo.addProperty("birthday", birthday);
            userInfo.addProperty("birthyear", birthyear);
            userInfo.addProperty("age", age);

            System.out.println("id : " + id);
            System.out.println("nickname : " + nickname);
            System.out.println("profileImage : " + profileImage);
            System.out.println("email : " + email);
            System.out.println("name : " + name);
            System.out.println("gender : " + gender);
            System.out.println("birthday : " + birthday);
            System.out.println("age : " + age);
            System.out.println("birthyear : " + birthyear);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return userInfo;
    }
    public void naverLogout(String accessToken) {
        try {
            // 네이버 로그아웃 API 호출 URL
            String logoutUrl = "https://nid.naver.com/oauth2.0/token?grant_type=delete&client_id=jUYpMer5eMaFflJnxZX6&client_secret=pYrIANkNKI&access_token=" + accessToken + "&service_provider=NAVER";
            URL url = new URL(logoutUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // 요청 메서드 설정
            conn.setRequestMethod("GET");

            // 연결 시도
            int responseCode = conn.getResponseCode();
            System.out.println("GET Response Code :: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // 정상적으로 로그아웃 처리된 경우
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // 결과 출력
                System.out.println("네이버 로그아웃 응답: " + response.toString());
            } else {
                // 로그아웃 요청이 실패한 경우
                System.out.println("GET request not worked");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
