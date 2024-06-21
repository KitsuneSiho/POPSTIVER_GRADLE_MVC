package kr.bit.function.member.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserDTO {
    private String username; // 사용자 ID
    private String name; // 사용자 이름
    private int userType; // 0: Admin, 1: User
    private String userEmail; // 사용자 이메일
    private String userGender; // 사용자 성별
    private String userBirth; // 사용자 생일
    private String userBirthYear; // 사용자 출생연도
}
