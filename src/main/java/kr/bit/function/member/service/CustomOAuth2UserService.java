package kr.bit.function.member.service;

import kr.bit.function.member.dto.*;
import kr.bit.function.member.entity.MemberEntity;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberService memberService;

    public CustomOAuth2UserService(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response;

        switch (registrationId) {
            case "naver":
                oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
                break;
            case "google":
                oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
                break;
            case "kakao":
                oAuth2Response = new KakaoResponse(oAuth2User.getAttributes());
                break;
            default:
                throw new OAuth2AuthenticationException("Unsupported registration id: " + registrationId);
        }

        String userId = (oAuth2Response.getProvider() + oAuth2Response.getProviderId()).replaceAll("\\s+", "");
        MemberEntity user = memberService.findById(userId);

        if (user == null) {
            // Save the user information in the session
            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(userId);
            userDTO.setName(oAuth2Response.getName());
            userDTO.setUserType("ROLE_USER"); // Default to user type ROLE_USER
            userDTO.setUserEmail(oAuth2Response.getEmail());
            userDTO.setUserGender(oAuth2Response.getGender());
            userDTO.setUserBirth(oAuth2Response.getBirthYear() + oAuth2Response.getBirthday());

            // Store the userDTO in the session attribute
            CustomOAuth2User customOAuth2User = new CustomOAuth2User(userDTO, oAuth2User.getAttributes());
            customOAuth2User.setPendingRegistration(true);  // 사용자 등록이 필요함을 표시

            return customOAuth2User;
        } else {
            user.setUser_email(oAuth2Response.getEmail());
            user.setUser_name(oAuth2Response.getName());
            user.setUser_gender(oAuth2Response.getGender());
            user.setUser_birth(oAuth2Response.getBirthYear() + oAuth2Response.getBirthday());
            memberService.updateUserInfo(user); // Update existing user information in the database
            System.out.println("Existing user updated: " + user);
        }

        // Ensure the user_type is being read from the DB
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUser_id());
        userDTO.setName(user.getUser_name());
        userDTO.setUserType(user.getUser_type());
        userDTO.setUserEmail(user.getUser_email());
        userDTO.setUserGender(user.getUser_gender());
        userDTO.setUserBirth(user.getUser_birth());

        System.out.println("User DTO created: " + userDTO);

        return new CustomOAuth2User(userDTO, oAuth2User.getAttributes());
    }
}
