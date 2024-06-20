package kr.bit.function.member.service;

import kr.bit.function.member.dto.*;
import kr.bit.function.member.entity.MemberEntity;
import kr.bit.function.member.entity.UserEntity;
import kr.bit.function.member.repository.UserRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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

        String userId = oAuth2Response.getProvider() + " " + oAuth2Response.getProviderId();
        MemberEntity user = userRepository.findByUserId(userId);

        if (user == null) {
            user = new MemberEntity();
            user.setUser_id(userId);
            user.setUser_email(oAuth2Response.getEmail());
            user.setUser_name(oAuth2Response.getName());
            user.setUser_type(1); // Default to user type 1 (regular user)
            user.setUser_gender(oAuth2Response.getGender());
            user.setUser_birth(oAuth2Response.getBirthYear() + oAuth2Response.getBirthday());
        } else {
            user.setUser_email(oAuth2Response.getEmail());
            user.setUser_name(oAuth2Response.getName());
            user.setUser_gender(oAuth2Response.getGender());
            user.setUser_birth(oAuth2Response.getBirthYear() + oAuth2Response.getBirthday());
        }


        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUser_id());
        userDTO.setName(user.getUser_name());
        userDTO.setUserType(user.getUser_type());
        userDTO.setUserEmail(user.getUser_email());
        userDTO.setUserGender(user.getUser_gender());
        userDTO.setUserBirth(user.getUser_birth());


        return new CustomOAuth2User(userDTO, oAuth2User.getAttributes());
    }
}
