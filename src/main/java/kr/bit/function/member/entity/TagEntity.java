package kr.bit.function.member.entity;

import lombok.Data;

@Data
public class TagEntity {
    private int tag_no;
    private String tag_name;

    public int getTag_no() {
        return tag_no;
    }

    public void setTag_no(int tag_no) {
        this.tag_no = tag_no;
    }

    public String getTag_name() {
        return tag_name;
    }

    public void setTag_name(String tag_name) {
        this.tag_name = tag_name;
    }
}


