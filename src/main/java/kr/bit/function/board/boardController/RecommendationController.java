package kr.bit.function.board.boardController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import kr.bit.function.member.entity.MemberEntity;
import kr.bit.function.member.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.logging.Logger;

@PropertySource("classpath:properties/application.properties")
@Controller
public class RecommendationController {

    @Autowired
    private UserRepository userRepository;

    private static final Logger LOGGER = Logger.getLogger(RecommendationController.class.getName());

    @Value("${ServerURL}")
    private String ServerURL;

    @GetMapping("/recommended")
    public String getRecommended(@AuthenticationPrincipal OAuth2User principal, Model model, OAuth2AuthenticationToken authentication) {
        try {
            String userId = null;
            String userEmail = null;
            String registrationId = authentication.getAuthorizedClientRegistrationId();

            LOGGER.info("Registration ID: " + registrationId);

            if ("google".equals(registrationId)) {
                userEmail = (String) principal.getAttributes().get("email");
            } else if ("naver".equals(registrationId)) {
                Map<String, Object> response = (Map<String, Object>) principal.getAttributes().get("response");
                userEmail = (String) response.get("email");
            } else if ("kakao".equals(registrationId)) {
                Map<String, Object> kakaoAccount = (Map<String, Object>) principal.getAttributes().get("kakao_account");
                userEmail = (String) kakaoAccount.get("email");
            }

            LOGGER.info("User Email: " + userEmail);

            if (userEmail == null) {
                throw new RuntimeException("User email not found");
            }

            MemberEntity user = userRepository.findByUserEmail(userEmail);
            if (user != null) {
                userId = user.getUser_id();
            }

            LOGGER.info("User ID: " + userId);

            if (userId == null) {
                throw new RuntimeException("User ID not found for email: " + userEmail);
            }

            // Python 서버에 요청 보내기
            String pythonServerUrl = ServerURL + "/recommendations?user_id=" + userId;            URL url = new URL(pythonServerUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            StringBuilder sb = new StringBuilder();
            String output;
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }

            conn.disconnect();

            // Jackson 설정 변경
            ObjectMapper mapper = JsonMapper.builder()
                    .enable(JsonReadFeature.ALLOW_NON_NUMERIC_NUMBERS)
                    .build();
            Map<String, Object> recommendations = mapper.readValue(sb.toString(), Map.class);

            model.addAttribute("festivals", recommendations.get("festivals"));
            model.addAttribute("popups", recommendations.get("popups"));

        } catch (JsonParseException e) {
            LOGGER.severe("JSON parsing error: " + e.getMessage());
        } catch (JsonMappingException e) {
            LOGGER.severe("JSON mapping error: " + e.getMessage());
        } catch (JsonProcessingException e) {
            LOGGER.severe("JSON processing error: " + e.getMessage());
        } catch (IOException e) {
            LOGGER.severe("IO error: " + e.getMessage());
        } catch (Exception e) {
            LOGGER.severe("Error: " + e.getMessage());
        }

        return "page/board/recommendedList";
    }


}
