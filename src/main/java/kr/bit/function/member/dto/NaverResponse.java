package kr.bit.function.member.dto;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class NaverResponse implements OAuth2Response {

    private final Map<String, Object> attribute;

    public NaverResponse(Map<String, Object> attribute) {
        this.attribute = (Map<String, Object>) attribute.get("response");

        // 전체 응답 구조를 출력하여 디버깅
        System.out.println("Full Original Attribute: " + attribute);

        // response 키 내부의 모든 키-값 쌍을 상세히 출력
        if (this.attribute != null) {
            System.out.println("Detailed Naver Response: " + this.attribute);
            for (Map.Entry<String, Object> entry : this.attribute.entrySet()) {
                System.out.println("Response Key: " + entry.getKey() + ", Value: " + entry.getValue());
            }
        } else {
            System.out.println("Response is null");
        }
    }

    @Override
    public String getProvider() {
        return "naver";
    }

    @Override
    public String getProviderId() {
        return attribute.get("id") != null ? attribute.get("id").toString() : null;
    }

    @Override
    public String getEmail() {
        return attribute.get("email") != null ? attribute.get("email").toString() : null;
    }

    @Override
    public String getName() {
        return attribute.get("name") != null ? attribute.get("name").toString() : null;
    }

    @Override
    public String getGender() {
        Object genderObj = attribute.get("gender");
        System.out.println("Gender: " + genderObj); // 디버깅을 위해 추가
        return genderObj != null ? genderObj.toString() : null;
    }

    @Override
    public String getBirthday() {
        String birthdayStr = attribute.get("birthday") != null ? attribute.get("birthday").toString() : null;
        System.out.println("Birthday: " + birthdayStr); // 디버깅을 위해 추가
        return birthdayStr;
    }

    @Override
    public String getBirthYear() {
        String birthYearStr = attribute.get("birthyear") != null ? attribute.get("birthyear").toString() : null;
        System.out.println("BirthYear: " + birthYearStr); // 디버깅을 위해 추가
        return birthYearStr;
    }
}
