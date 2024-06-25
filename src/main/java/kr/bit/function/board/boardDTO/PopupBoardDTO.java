package kr.bit.function.board.boardDTO;

public class PopupBoardDTO {
    private int popup_no; // 보드넘버
    private String popup_title; // 제목
    private String popup_content; // 본문
    private String host; // 주최자
    private String popup_dist;
    private String popup_subdist;
    private String popup_location; // 행사위치
    private String popup_start; // 행사시작날짜
    private String popup_end; // 행사끝나는날짜
    private String open_time; // 열리는 시간
    private String popup_post_date; // 이건 언제작성됐는지 자동으로 판단하기에 읽기만 하면 될 것 같아요
    private String popup_attachment; // 첨부파일 링크
    private int event_type; // 이벤트타입
    private int like_that; // 좋아요 수
    private int views; // 조회수
    private String brand_link; // 공식홈피링크
    private String brand_sns; // SNS링크

    // 빈 생성자
    public PopupBoardDTO() {
        super();
    }

    // 모든 데이터를 초기화하는 생성자
    public PopupBoardDTO(int popup_no, String popup_title, String popup_content, String host, String popup_dist,
                         String popup_subdist, String popup_location, String popup_start, String popup_end,
                         String open_time, String popup_post_date, String popup_attachment,
                         int event_type, int like_that, int views, String brand_link, String brand_sns) {
        super();
        this.popup_no = popup_no;
        this.popup_title = popup_title;
        this.popup_content = popup_content;
        this.host = host;
        this.popup_dist = popup_dist;
        this.popup_subdist = popup_subdist;
        this.popup_location = popup_location;
        this.popup_start = popup_start;
        this.popup_end = popup_end;
        this.open_time = open_time;
        this.popup_post_date = popup_post_date;
        this.popup_attachment = popup_attachment;
        this.event_type = event_type;
        this.like_that = like_that;
        this.views = views;
        this.brand_link = brand_link;
        this.brand_sns = brand_sns;
    }

    public int getPopup_no() {
        return popup_no;
    }

    public void setPopup_no(int popup_no) {
        this.popup_no = popup_no;
    }

    public String getPopup_title() {
        return popup_title;
    }

    public void setPopup_title(String popup_title) {
        this.popup_title = popup_title;
    }

    public String getPopup_content() {
        return popup_content;
    }

    public void setPopup_content(String popup_content) {
        this.popup_content = popup_content;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPopup_dist() {
        return popup_dist;
    }

    public void setPopup_dist(String popup_dist) {
        this.popup_dist = popup_dist;
    }

    public String getPopup_subdist() {
        return popup_subdist;
    }

    public void setPopup_subdist(String popup_subdist) {
        this.popup_subdist = popup_subdist;
    }

    public String getPopup_location() {
        return popup_location;
    }

    public void setPopup_location(String popup_location) {
        this.popup_location = popup_location;
    }

    public String getPopup_start() {
        return popup_start;
    }

    public void setPopup_start(String popup_start) {
        this.popup_start = popup_start;
    }

    public String getPopup_end() {
        return popup_end;
    }

    public void setPopup_end(String popup_end) {
        this.popup_end = popup_end;
    }

    public String getOpen_time() {
        return open_time;
    }

    public void setOpen_time(String open_time) {
        this.open_time = open_time;
    }

    public String getPopup_post_date() {
        return popup_post_date;
    }

    public void setPopup_post_date(String popup_post_date) {
        this.popup_post_date = popup_post_date;
    }

    public String getPopup_attachment() {
        return popup_attachment;
    }

    public void setPopup_attachment(String popup_attachment) {
        this.popup_attachment = popup_attachment;
    }

    public int getEvent_type() {
        return event_type;
    }

    public void setEvent_type(int event_type) {
        this.event_type = event_type;
    }

    public int getLike_that() {
        return like_that;
    }

    public void setLike_that(int like_that) {
        this.like_that = like_that;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getBrand_link() {
        return brand_link;
    }

    public void setBrand_link(String brand_link) {
        this.brand_link = brand_link;
    }

    public String getBrand_sns() {
        return brand_sns;
    }

    public void setBrand_sns(String brand_sns) {
        this.brand_sns = brand_sns;
    }
}
