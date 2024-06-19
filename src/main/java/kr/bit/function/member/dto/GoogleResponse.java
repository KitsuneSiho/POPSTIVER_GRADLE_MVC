package kr.bit.function.member.dto;

import java.util.Date;
import java.util.Map;

public class GoogleResponse implements OAuth2Response {

    private final Map<String, Object> attribute;

    public GoogleResponse(Map<String, Object> attribute) {
        this.attribute = attribute;
    }

    @Override
    public String getProvider() {
        return "google";
    }

    @Override
    public String getProviderId() {
        return attribute.get("sub").toString();
    }

    @Override
    public String getEmail() {
        return attribute.get("email").toString();
    }

    @Override
    public String getName() {
        return attribute.get("name").toString();
    }

    // 추가 정보가 없으므로 기본값을 반환하도록 구현
    @Override
    public String getGender() {
        return null;
    }

    @Override
    public Date getBirthday() {
        return null;
    }

    @Override
    public String getBirthYear() {
        return null;
    }
}
