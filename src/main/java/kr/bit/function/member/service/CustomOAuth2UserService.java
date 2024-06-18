package kr.bit.function.member.service;

import kr.bit.function.member.dto.*;
import kr.bit.function.member.entity.UserEntity;
import kr.bit.function.member.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import java.util.Collections;
import java.util.Map;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private static final Logger logger = LoggerFactory.getLogger(CustomOAuth2UserService.class);

    private final UserRepository userRepository;

    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // OAuth2UserRequest 객체 정보 확인
        System.out.println("OAuth2UserRequest: " + userRequest);
        System.out.println("Access Token: " + userRequest.getAccessToken().getTokenValue());

        OAuth2User oAuth2User = super.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = null;

        // Access Token을 사용하여 카카오 사용자 정보를 가져오는 API 호출
        if ("kakao".equals(registrationId)) {
            String accessToken = userRequest.getAccessToken().getTokenValue();
            getKakaoUserInfo(accessToken);
        }

        System.out.println("OAuth2User Attributes: " + oAuth2User.getAttributes());

        switch (registrationId) {
            case "naver":
                oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
                break;
            case "google":
                oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
                break;
            case "kakao":
                oAuth2Response = new KakaoResponse(oAuth2User.getAttributes());
                logger.debug("KakaoResponse: ID={}, Email={}, Name={}",
                        oAuth2Response.getProviderId(), oAuth2Response.getEmail(), oAuth2Response.getName());
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
            userEntity.setUserPw(""); // OAuth2 로그인 사용자의 경우 비밀번호는 빈 문자열로 설정
            userEntity.setUserType(1); // Default to user type 1 (regular user)
        } else {
            userEntity.setUserEmail(oAuth2Response.getEmail());
            userEntity.setUserName(oAuth2Response.getName());
        }

        userRepository.saveOrUpdateUser(userEntity);

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(userEntity.getUserId());
        userDTO.setName(userEntity.getUserName());
        userDTO.setUserType(userEntity.getUserType()); // Setting the user type

        // 속성 데이터를 포함한 CustomOAuth2User 반환
        return new CustomOAuth2User(userDTO, oAuth2User.getAttributes());
    }

    // Access Token을 사용하여 카카오 사용자 정보를 가져오는 메소드 추가
    public void getKakaoUserInfo(String accessToken) {
        String userInfoUri = "https://kapi.kakao.com/v2/user/me";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(userInfoUri, HttpMethod.GET, entity, String.class);

        System.out.println("Kakao User Info: " + response.getBody());
    }
}
