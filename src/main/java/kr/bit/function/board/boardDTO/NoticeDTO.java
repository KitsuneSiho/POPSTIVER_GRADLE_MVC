package kr.bit.function.board.boardDTO;

public class NoticeDTO {
    private int notice_no;
    private String notice_title;
    private String notice_content;
    private String notice_date;

    public NoticeDTO(){
        super();
    }

    public NoticeDTO(int notice_no,
                     String notice_title,
                     String notice_content,
                     String notice_date) {
        super();
        this.notice_no = notice_no;
        this.notice_title = notice_title;
        this.notice_content = notice_content;
        this.notice_date = notice_date;

    }

    public int getNotice_no() {
        return notice_no;
    }

    public void setNotice_no(int notice_no) {
        this.notice_no = notice_no;
    }

    public String getNotice_title() {
        return notice_title;
    }

    public void setNotice_title(String notice_title) {
        this.notice_title = notice_title;
    }

    public String getNotice_content() {
        return notice_content;
    }

    public void setNotice_content(String notice_content) {
        this.notice_content = notice_content;
    }

    public String getNotice_date() {
        return notice_date;
    }

    public void setNotice_date(String notice_date) {
        this.notice_date = notice_date;
    }
}
