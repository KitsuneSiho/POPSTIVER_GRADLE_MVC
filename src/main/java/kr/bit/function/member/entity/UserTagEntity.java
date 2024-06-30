package kr.bit.function.member.entity;

import lombok.Data;

public class UserTagEntity {
    private String user_id;
    private int tag_no;

    // 기본 생성자
    public UserTagEntity() {}

    // 모든 필드를 포함한 생성자
    public UserTagEntity(String user_id, int tag_no) {
        this.user_id = user_id;
        this.tag_no = tag_no;
    }

    // Getter와 Setter
    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getTag_no() {
        return tag_no;
    }

    public void setTag_no(int tag_no) {
        this.tag_no = tag_no;
    }
}


