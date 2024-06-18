package kr.bit.function.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserDTO {
    private String username;
    private String name;
    private int userType; // 0: Admin, 1: User
}
