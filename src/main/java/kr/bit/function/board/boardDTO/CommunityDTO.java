package kr.bit.function.board.boardDTO;

import lombok.Data;

@Data
public class CommunityDTO {
    private int board_no;
    private String board_title;
    private String board_content;
    private String user_id;
    private String user_name;
    private String board_post_date;
    private byte[] board_attachment;
    private int board_views;

    public CommunityDTO(){
        super();
    }

    public CommunityDTO(int board_no,String board_title, String board_content, String user_id, String user_name, int board_views, String board_post_date, byte[] board_attachment) {
        super();
        this.board_no = board_no;
        this.board_title = board_title;
        this.board_content = board_content;
        this.user_id = user_id;
        this.user_name = user_name;
        this.board_post_date = board_post_date;
        this.board_attachment = board_attachment;
        this.board_views = board_views;

    }


}
