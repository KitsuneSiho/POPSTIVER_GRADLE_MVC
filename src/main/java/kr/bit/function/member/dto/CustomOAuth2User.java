package kr.bit.function.member.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class CustomOAuth2User implements OAuth2User {

    private final UserDTO userDTO;
    private final Map<String, Object> attributes;

    public CustomOAuth2User(UserDTO userDTO, Map<String, Object> attributes) {
        this.userDTO = userDTO;
        this.attributes = attributes; // 실제 사용자 속성을 할당합니다.
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes; // 사용자 속성을 반환합니다.
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        if (userDTO.getUserType() == 0) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        return authorities;
    }

    @Override
    public String getName() {
        return userDTO.getName();
    }

    public String getUsername() {
        return userDTO.getUsername();
    }

    // 제공자(Provider) 구분 메서드 추가
    public String getProvider() {
        if (attributes.containsKey("sub")) {
            return "google";
        } else if (attributes.containsKey("response")) {
            Map<String, Object> response = (Map<String, Object>) attributes.get("response");
            if (response.containsKey("id")) {
                return "naver";
            }
        } else if (attributes.containsKey("id")) {
            return "kakao";
        }
        return null; // 구분할 수 없는 경우
    }

}
