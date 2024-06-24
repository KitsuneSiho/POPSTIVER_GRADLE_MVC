package kr.bit.function.board.boardEntity;

public class Community {
    private int board_no;
    private String board_title;
    private String board_content;
    private String user_id;
    private String user_name;
    private String board_post_date;
    private int views;

    public Community(){
        super();
    }
    public Community(int board_no, String board_title, String board_content, String user_id, String user_name, int views) {}
}
