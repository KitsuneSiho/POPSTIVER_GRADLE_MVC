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
    private boolean pendingRegistration = false;

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
        String userType = userDTO.getUserType();
        authorities.add(new SimpleGrantedAuthority(userType));
        return authorities;
    }

    @Override
    public String getName() {
        return userDTO.getName();
    }

    public String getUsername() {
        return userDTO.getUsername();
    }

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
        return null;
    }

    public boolean isPendingRegistration() {
        return pendingRegistration;
    }

    public void setPendingRegistration(boolean pendingRegistration) {
        this.pendingRegistration = pendingRegistration;
    }
}
