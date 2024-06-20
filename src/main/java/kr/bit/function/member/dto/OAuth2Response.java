package kr.bit.function.member.dto;

import java.util.Date;

public interface OAuth2Response {

    String getProvider();
    String getProviderId();
    String getEmail();
    String getName();
    String getGender();  // 성별
    String getBirthday();  // 생일
    String getBirthYear();  // 출생연도
}