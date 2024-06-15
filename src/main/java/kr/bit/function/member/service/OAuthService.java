package kr.bit.function.member.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.ibankapp.base.exception.BaseException;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class OAuthService {

    public String getKakaoAccessToken(String code) {
        String access_Token = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";

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
            sb.append("&client_id=cffd2c8562ed8ebbb1d01137aa0349f7"); // TODO REST_API_KEY 입력
            sb.append("&redirect_uri=http://localhost:8080/login/oauth2/code/kakao"); // TODO 인가코드 받은 redirect_uri 입력
            sb.append("&code=").append(code);
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

    public JsonObject getKakaoUserInfo(String token) throws BaseException {
        String reqURL = "https://kapi.kakao.com/v2/user/me";
        JsonObject userInfo = new JsonObject();

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
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
            JsonObject kakaoAccount = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
            int id = element.getAsJsonObject().get("id").getAsInt();
            String nickname = kakaoAccount.getAsJsonObject().get("profile").getAsJsonObject().get("nickname").getAsString();
            String profileImage = kakaoAccount.getAsJsonObject().get("profile").getAsJsonObject().get("profile_image_url").getAsString();
            String email = kakaoAccount.get("email").getAsString();
            String name = kakaoAccount.getAsJsonObject().get("profile").getAsJsonObject().get("nickname").getAsString();
            String gender = kakaoAccount.get("gender").getAsString();
            String ageRange = kakaoAccount.get("age_range").getAsString();
            String birthday = kakaoAccount.get("birthday").getAsString();
            String birthyear = kakaoAccount.get("birthyear").getAsString();

            // 사용자 정보를 JsonObject에 담기
            userInfo.addProperty("id", id);
            userInfo.addProperty("nickname", nickname);
            userInfo.addProperty("profileImage", profileImage);
            userInfo.addProperty("email", email);
            userInfo.addProperty("name", name);
            userInfo.addProperty("gender", gender);
            userInfo.addProperty("ageRange", ageRange);
            userInfo.addProperty("birthday", birthday);
            userInfo.addProperty("birthyear", birthyear);

            System.out.println("id : " + id);
            System.out.println("nickname : " + nickname);
            System.out.println("profileImage : " + profileImage);
            System.out.println("email : " + email);
            System.out.println("name : " + name);
            System.out.println("gender : " + gender);
            System.out.println("ageRange : " + ageRange);
            System.out.println("birthday : " + birthday);
            System.out.println("birthyear : " + birthyear);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return userInfo;
    }
}
