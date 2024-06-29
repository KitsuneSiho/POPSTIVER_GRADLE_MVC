package kr.bit.function.board.boardDTO;

import lombok.Data;

@Data
public class CompanionDTO {
    private int comp_no;
    private String comp_title;
    private String comp_content;
    private String user_name;
    private String user_id;
    private String comp_post_date;
    private int comp_views;
    public CompanionDTO() {
        super();
    }
    public CompanionDTO(int comp_no, String comp_title,
                           String comp_content,
                           String user_name,
                           String user_id,
                           String comp_post_date,
                           int comp_views) {
        super();
        this.comp_no = comp_no;
        this.comp_title = comp_title;
        this.comp_content = comp_content;
        this.user_name = user_name;
        this.user_id = user_id;
        this.comp_post_date = comp_post_date;
        this.comp_views = comp_views;

    }

}
