package kr.bit.function.board.boardEntity;

import lombok.Data;

@Data
public class CommunityEntity {
    private int board_no;
    private String board_title;
    private String board_content;
    private String user_id;
    private String user_name;
    private String board_post_date;
    private String board_attachment;
    private int board_views;

    public CommunityEntity(){
        super();
    }

    public CommunityEntity(int board_no,String board_title, String board_content, String user_id, String user_name, int board_views, String board_post_date, String board_attachment) {
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

    public int getBoard_no() {
        return board_no;
    }

    public void setBoard_no(int board_no) {
        this.board_no = board_no;
    }

    public String getBoard_title() {
        return board_title;
    }

    public void setBoard_title(String board_title) {
        this.board_title = board_title;
    }

    public String getBoard_content() {
        return board_content;
    }

    public void setBoard_content(String board_content) {
        this.board_content = board_content;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getBoard_post_date() {
        return board_post_date;
    }

    public void setBoard_post_date(String board_post_date) {
        this.board_post_date = board_post_date;
    }

    public String getBoard_attachment() {
        return board_attachment;
    }

    public void setBoard_attachment(String board_attachment) {
        this.board_attachment = board_attachment;
    }

    public int getBoard_views() {
        return board_views;
    }

    public void setBoard_views(int board_views) {
        this.board_views = board_views;
    }
}
