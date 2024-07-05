package kr.bit.function.board.boardDTO;

import lombok.Data;

@Data
public class TotalCommentDTO {
    private String type;
    private String comment_writer;
    private String comment_content;
    private String comment_date;
}
