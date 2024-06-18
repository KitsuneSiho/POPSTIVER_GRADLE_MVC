package kr.bit.function.member.dto;

import java.util.Map;

public class KakaoResponse implements OAuth2Response {

    private Map<String, Object> attribute;
    private Map<String, Object> kakaoAccountAttribute;
    private Map<String, Object> profileAttribute;

    public KakaoResponse(Map<String, Object> attribute) {
        this.attribute = attribute;

        // 안전한 타입 캐스팅을 위한 변환과 확인
        this.kakaoAccountAttribute = getMapFromAttributes(attribute, "kakao_account");
        this.profileAttribute = getMapFromAttributes(kakaoAccountAttribute, "profile");

        // 전체 응답 구조 출력 (디버깅용)
        System.out.println("Full Kakao Response: " + attribute);
    }

    private Map<String, Object> getMapFromAttributes(Map<String, Object> attributes, String key) {
        if (attributes != null && attributes.containsKey(key)) {
            try {
                return (Map<String, Object>) attributes.get(key);
            } catch (ClassCastException e) {
                System.err.println("Failed to cast attribute " + key + " to Map<String, Object>");
            }
        }
        return null; // 키가 없으면 null 반환
    }

    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getProviderId() {
        return attribute.get("id") != null ? attribute.get("id").toString() : null;
    }

    @Override
    public String getEmail() {
        return kakaoAccountAttribute != null && kakaoAccountAttribute.get("email") != null
                ? kakaoAccountAttribute.get("email").toString()
                : null;
    }

    @Override
    public String getName() {
        return profileAttribute != null && profileAttribute.get("nickname") != null
                ? profileAttribute.get("nickname").toString()
                : null;
    }
}
