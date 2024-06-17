package kr.bit.function.member.service;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
@PropertySource("classpath:properties/application.properties")

@Service
public class GoogleOAuthService {

    @Value("${google.clientId}")
    private String clientId;

    @Value("${google.clientSecret}")
    private String clientSecret;

    @Value("${google.redirectUri}")
    private String redirectUri;

    public String getGoogleAccessToken(String code) {
        String accessToken = "";
        String reqURL = "https://oauth2.googleapis.com/token";

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // POST 요청을 위해 기본값이 false인 setDoOutput을 true로 설정
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            String parameters = "client_id=" + clientId +
                    "&clientSecret=" + clientSecret +
                    "&redirect_uri=" + redirectUri +
                    "&code=" + code +
                    "&grant_type=authorization_code";

            // 파라미터 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            bw.write(parameters);
            bw.flush();

            // 응답 코드 확인
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            // 응답 본문 읽기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();

            // Gson 라이브러리로 JSON 파싱
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(response.toString());

            accessToken = element.getAsJsonObject().get("access_token").getAsString();

            System.out.println("access_token : " + accessToken);

            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return accessToken;
    }

    public JsonObject getGoogleUserInfo(String accessToken) {
        String reqURL = "https://www.googleapis.com/oauth2/v3/userinfo";
        JsonObject userInfo = new JsonObject();

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // GET 요청 설정
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "Bearer " + accessToken);

            // 요청 결과 코드 확인
            int responseCode = conn.getResponseCode();
            System.out.println("Google User Info Response Code : " + responseCode);

            // JSON 형식의 응답 데이터를 읽어오기 위한 BufferedReader 생성
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Gson 라이브러리를 사용하여 JSON 파싱
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(response.toString());

            // 사용자 정보 파싱
            String email = element.getAsJsonObject().get("email").getAsString();
            String name = element.getAsJsonObject().get("name").getAsString();
            userInfo.addProperty("email", email);
            userInfo.addProperty("name", name);
            System.out.println("Google User Info : " + userInfo);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return userInfo;
    }

    public void googleLogout(String accessToken) {
        try {
            System.out.println("구글 로그아웃 호출됨");
            String logoutUrl = "https://accounts.google.com/o/oauth2/revoke?token=" + accessToken;
            URL url = new URL(logoutUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET"); // 변경된 부분: POST에서 GET으로 변경
            conn.setDoOutput(true);

            int responseCode = conn.getResponseCode();
            System.out.println("GET Response Code :: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // print result
                System.out.println("구글 로그아웃 응답: " + response.toString());
            } else {
                System.out.println("GET request not worked");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
