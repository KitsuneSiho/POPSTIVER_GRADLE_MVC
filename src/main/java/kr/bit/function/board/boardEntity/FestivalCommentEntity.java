package kr.bit.function.board.boardEntity;

//페스티벌 게사물 댓글저장테이블

public class FestivalCommentEntity {
    private int comment_no; //댓글번호(자동증가라 삽입할땐 0)
    private int event_type; //행사유형
    private String comment_writer; //댓글쓴이. 유저네임 들어가면 됩니다.
    private String comment_date;//자동으로 언제썼는지 찍힘. 삽입할땐 null 주시면 됩니다.
    private String visit_date;//방문한날짜
    private String comment_content;//댓글내용
    private int festival_no; //본 게시글 번호. 이거 기준으로 찾아갑니다.
    private String comment_attachment; //댓글 첨부파일
    private int star_rate; //별점

    public FestivalCommentEntity() {
        super();
    }

    public FestivalCommentEntity(int comment_no,
                              int event_type,
                              String comment_writer,
                              String comment_date,
                              String visit_date,
                              String comment_content,
                              int festival_no,
                              String comment_attachment,
                              int star_rate) {
        super();
        this.comment_no = comment_no;
        this.event_type = event_type;
        this.comment_writer = comment_writer;
        this.comment_date = comment_date;
        this.visit_date = visit_date;
        this.comment_content = comment_content;
        this.festival_no = festival_no;
        this.comment_attachment = comment_attachment;
        this.star_rate = star_rate;

    }

    public int getComment_no() {
        return comment_no;
    }

    public void setComment_no(int comment_no) {
        this.comment_no = comment_no;
    }

    public int getEvent_type() {
        return event_type;
    }

    public void setEvent_type(int event_type) {
        this.event_type = event_type;
    }

    public String getComment_writer() {
        return comment_writer;
    }

    public void setComment_writer(String comment_writer) {
        this.comment_writer = comment_writer;
    }

    public String getComment_date() {
        return comment_date;
    }

    public void setComment_date(String comment_date) {
        this.comment_date = comment_date;
    }

    public String getVisit_date() {
        return visit_date;
    }

    public void setVisit_date(String visit_date) {
        this.visit_date = visit_date;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    public int getFestival_no() {
        return festival_no;
    }

    public void setFestival_no(int festival_no) {
        this.festival_no = festival_no;
    }

    public String getComment_attachment() {
        return comment_attachment;
    }

    public void setComment_attachment(String comment_attachment) {
        this.comment_attachment = comment_attachment;
    }

    public int getStar_rate() {
        return star_rate;
    }

    public void setStar_rate(int star_rate) {
        this.star_rate = star_rate;
    }
}
