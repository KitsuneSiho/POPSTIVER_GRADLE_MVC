package kr.bit.function.member.entity;

import lombok.Data;

@Data
public class TagEntity {
    private String title;
    private String content;
    private int tag_No;  // 축제 또는 팝업의 고유 번호

}

