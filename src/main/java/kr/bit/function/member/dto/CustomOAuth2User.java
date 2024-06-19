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
        this.attributes = attributes;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        // userType에 따라 권한 부여 로직 수정
        switch (userDTO.getUserType()) {
            case 1: // 주최자
                authorities.add(new SimpleGrantedAuthority("ROLE_HOST"));
                break;
            case 2: // 사용자
                authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
                break;
            case 3: // 관리자
                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                break;
            default:
                // userType이 정의되지 않은 경우 예외 처리
                throw new IllegalArgumentException("Invalid userType: " + userDTO.getUserType());
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

    public UserDTO getUserDTO() {
        return userDTO;
    }
}