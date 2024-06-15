package kr.bit.function.member.memberController;

import com.google.gson.JsonObject;
import kr.bit.function.member.service.OAuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class OAuth2LoginController {

    private final OAuthService oauthService;

    public OAuth2LoginController(OAuthService oauthService) {
        this.oauthService = oauthService;
    }

    // 로그인 창 가는 거 임시로...
    @RequestMapping(value="/login_practice", method= RequestMethod.GET)
    public String login_practice() {
        return "/page/login_practice"; // login.jsp로 이동
    }

    @GetMapping("/login/oauth2/code/kakao")
    public String getLoginInfo(@RequestParam("code") String code, Model model) {
        System.out.println("Received authorization code: " + code);

        // 액세스 토큰 요청
        String accessToken = oauthService.getKakaoAccessToken(code);
        if (accessToken == null) {
            System.out.println("Access Token: 없음");
            return "redirect:/login_practice"; // 로그인 실패 시 리디렉션
        } else {
            System.out.println("Access Token: " + accessToken);
        }

        // 사용자 정보 요청
        JsonObject userInfo = oauthService.getKakaoUserInfo(accessToken);
        if (userInfo == null) {
            System.out.println("User Info: 없음");
            return "redirect:/login_practice"; // 사용자 정보 가져오기 실패 시 리디렉션
        } else {
            System.out.println("User Info: " + userInfo);
        }

        // 사용자 정보를 모델에 추가
        String userId = userInfo.get("id").getAsString();
        String userNickname = userInfo.get("nickname").getAsString();
        String userProfileImage = userInfo.get("profileImage").getAsString();
        String userEmail = userInfo.get("email").getAsString();
        String userName = userInfo.get("name").getAsString();
        String userGender = userInfo.get("gender").getAsString();
        String userAgeRange = userInfo.get("ageRange").getAsString();
        String userBirthday = userInfo.get("birthday").getAsString();
        String userBirthyear = userInfo.get("birthyear").getAsString();

        model.addAttribute("userId", userId);
        model.addAttribute("userNickname", userNickname);
        model.addAttribute("userProfileImage", userProfileImage);
        model.addAttribute("userEmail", userEmail);
        model.addAttribute("userName", userName);
        model.addAttribute("userGender", userGender);
        model.addAttribute("userAgeRange", userAgeRange);
        model.addAttribute("userBirthday", userBirthday);
        model.addAttribute("userBirthyear", userBirthyear);

        return "/page/loginSuccess"; // 뷰 이름 반환 (loginSuccess.jsp)
    }


}