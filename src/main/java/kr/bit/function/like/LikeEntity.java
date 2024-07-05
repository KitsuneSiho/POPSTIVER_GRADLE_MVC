package kr.bit.function.like;

import lombok.Data;

@Data
public class LikeEntity {
    private int like_no;
    private String user_name;
    private String user_id;
    private int event_no;
    private int event_type;
}
