package kr.bit.function.board.boardController;

import kr.bit.function.member.entity.MemberEntity;
import kr.bit.function.member.repository.UserRepository;
import kr.bit.function.member.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Controller
public class RecommendationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecommendationService recommendationService;

    private static final Logger LOGGER = Logger.getLogger(RecommendationController.class.getName());

    @GetMapping("/getRecommended")
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
            Map<String, List<Map<String, Object>>> recommendations = recommendationService.getRecommendationsFromPythonServer(userId);

            // JSP에 전달
            model.addAttribute("festivals", recommendations.get("festivals"));
            model.addAttribute("popups", recommendations.get("popups"));

        } catch (Exception e) {
            LOGGER.severe("Error while getting recommendations: " + e.getMessage());
            e.printStackTrace();
            return "error";  // 에러 페이지로 이동 (적절한 에러 페이지를 설정해야 합니다)
        }
        return "recommended";
    }
}
