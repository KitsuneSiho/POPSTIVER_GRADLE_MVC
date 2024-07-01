package kr.bit.function.board.boardDTO;

import lombok.Data;

@Data
public class LikeListDTO {

    private int like_no;
    private int event_type;
    private int event_no;
    private String user_id;
    private String user_name;

}
