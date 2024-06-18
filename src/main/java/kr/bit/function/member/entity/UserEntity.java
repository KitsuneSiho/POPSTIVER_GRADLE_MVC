package kr.bit.function.member.entity;

import lombok.Data;

import java.util.Date;

@Data
public class UserEntity {
    private int userNo;     // user_no, primary key
    private int userType;   // user_type
    private String userId;  // user_id, unique
    private String userPw;  // user_pw
    private String userName; // user_name
    private String userEmail; // user_email
    private Date userBirth; // user_birth, DATE 타입
    private String userGender; // user_gender
}
