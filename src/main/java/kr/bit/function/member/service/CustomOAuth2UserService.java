package kr.bit.function.member.service;

import kr.bit.function.member.dto.*;
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
        UserEntity userEntity = userRepository.findByUserId(userId);

        if (userEntity == null) {
            userEntity = new UserEntity();
            userEntity.setUserId(userId);
            userEntity.setUserEmail(oAuth2Response.getEmail());
            userEntity.setUserName(oAuth2Response.getName());
            userEntity.setUserType(1); // Default to user type 1 (regular user)
            userEntity.setUserGender(oAuth2Response.getGender());
            userEntity.setUserBirth(oAuth2Response.getBirthday()); // Date 타입으로 설정
            userEntity.setUserBirthYear(oAuth2Response.getBirthYear());
        } else {
            userEntity.setUserEmail(oAuth2Response.getEmail());
            userEntity.setUserName(oAuth2Response.getName());
            userEntity.setUserGender(oAuth2Response.getGender());
            userEntity.setUserBirth(oAuth2Response.getBirthday()); // Date 타입으로 설정
            userEntity.setUserBirthYear(oAuth2Response.getBirthYear());
        }

        userRepository.saveOrUpdateUser(userEntity);

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(userEntity.getUserId());
        userDTO.setName(userEntity.getUserName());
        userDTO.setUserType(userEntity.getUserType());
        userDTO.setUserEmail(userEntity.getUserEmail());
        userDTO.setUserGender(userEntity.getUserGender());
        userDTO.setUserBirth(userEntity.getUserBirth());
        userDTO.setUserBirthYear(userEntity.getUserBirthYear());

        return new CustomOAuth2User(userDTO, oAuth2User.getAttributes());
    }
}
