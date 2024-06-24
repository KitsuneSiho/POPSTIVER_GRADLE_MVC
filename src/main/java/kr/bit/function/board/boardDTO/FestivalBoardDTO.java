package kr.bit.function.board.boardDTO;

public class FestivalBoardDTO {
    private int festival_no; // 보드넘버
    private String festival_title; // 제목
    private String festival_content; // 본문
    private String host; // 주최자
    private String festival_dist;
    private String festival_subdist;
    private String festival_location; // 행사위치
    private String festival_start; // 행사시작날짜
    private String festival_end; // 행사끝나는날짜
    private String open_time; // 열리는 시간
    private String festival_post_date; // 이건 언제작성됐는지 자동으로 판단하기에 읽기만 하면 될 것 같아요
    private String festival_attachment; // 첨부파일 링크
    private int event_type; // 이벤트타입
    private int like_that; // 좋아요 수
    private int views; // 조회수
    private String brand_link; // 공식홈피링크
    private String brand_sns; // SNS링크

    // 빈 생성자
    public FestivalBoardDTO() {
        super();
    }

    // 모든 데이터를 초기화하는 생성자
    public FestivalBoardDTO(int festival_no, String festival_title, String festival_content, String host, String festival_dist,
                            String festival_subdist, String festival_location, String festival_start, String festival_end,
                            String open_time, String festival_post_date, String festival_attachment,
                            int event_type, int like_that, int views, String brand_link, String brand_sns) {
        super();
        this.festival_no = festival_no;
        this.festival_title = festival_title;
        this.festival_content = festival_content;
        this.host = host;
        this.festival_dist = festival_dist;
        this.festival_subdist = festival_subdist;
        this.festival_location = festival_location;
        this.festival_start = festival_start;
        this.festival_end = festival_end;
        this.open_time = open_time;
        this.festival_post_date = festival_post_date;
        this.festival_attachment = festival_attachment;
        this.event_type = event_type;
        this.like_that = like_that;
        this.views = views;
        this.brand_link = brand_link;
        this.brand_sns = brand_sns;
    }

    public int getFestival_no() {
        return festival_no;
    }

    public void setFestival_no(int festival_no) {
        this.festival_no = festival_no;
    }

    public String getFestival_title() {
        return festival_title;
    }

    public void setFestival_title(String festival_title) {
        this.festival_title = festival_title;
    }

    public String getFestival_content() {
        return festival_content;
    }

    public void setFestival_content(String festival_content) {
        this.festival_content = festival_content;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getFestival_dist() {
        return festival_dist;
    }

    public void setFestival_dist(String festival_dist) {
        this.festival_dist = festival_dist;
    }

    public String getFestival_subdist() {
        return festival_subdist;
    }

    public void setFestival_subdist(String festival_subdist) {
        this.festival_subdist = festival_subdist;
    }

    public String getFestival_location() {
        return festival_location;
    }

    public void setFestival_location(String festival_location) {
        this.festival_location = festival_location;
    }

    public String getFestival_start() {
        return festival_start;
    }

    public void setFestival_start(String festival_start) {
        this.festival_start = festival_start;
    }

    public String getFestival_end() {
        return festival_end;
    }

    public void setFestival_end(String festival_end) {
        this.festival_end = festival_end;
    }

    public String getOpen_time() {
        return open_time;
    }

    public void setOpen_time(String open_time) {
        this.open_time = open_time;
    }

    public String getFestival_post_date() {
        return festival_post_date;
    }

    public void setFestival_post_date(String festival_post_date) {
        this.festival_post_date = festival_post_date;
    }

    public String getFestival_attachment() {
        return festival_attachment;
    }

    public void setFestival_attachment(String festival_attachment) {
        this.festival_attachment = festival_attachment;
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