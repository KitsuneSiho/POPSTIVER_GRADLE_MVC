package kr.bit.function.board.boardEntity;

public class FestivalBoardEntity {
    //--------------------FESTIVAL ENTITY---------------------//
    private int festival_no; // 페스티벌보드넘버. 자동증가이기에 '0'으로 주입할것
    private String festival_title; // 페스티벌 제목
    private String festival_content; // 페스티벌 본문
    private String festival_dist; // 페스티벌 위치(시)
    private String festival_subdist; //페스티벌 위치(군구)
    private String festival_location; // 페스티벌 위치
    private String festival_start; //페스티벌 시작 날짜. 형식: YY-MM-dd
    private String festival_end; //페스티벌 끝나는 날짜. 형식: YY-MM-dd
    private String festival_post_date; //페스티벌 등록일자. 이건 자동이기에 '0'으로 주입할것.
    private String festival_attachment; //페스티벌 첨부파일 위치. 리소스폴더에 저장될 경로명? 파일명을 설정한다.
    //------------------FESTIVAL & POPUP 공통 엔티티-------------//
    private String open_time; //운영시간
    private String host; // 주최자
    private int event_type; //이벤트형식. 1=페스티벌 2=팝업스토어
    private int like_that; //좋아요
    private int views; //조회수
    private String brand_link; //공식사이트링크
    private String brand_sns; //공식SNS링크

    // 기본 생성자
    public FestivalBoardEntity() {
        super();
    }

    // 모든 필드를 초기화하는 생성자
    public FestivalBoardEntity(int festival_no, String festival_title, String festival_content, String host, String festival_dist, String festival_subdist, String festival_location,
                               String festival_start, String festival_end, String open_time, String festival_post_date,
                               String festival_attachment, int event_type, int like_that, int views, String brand_link, String brand_sns) {
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

    public String getOpen_time() {
        return open_time;
    }

    public void setOpen_time(String open_time) {
        this.open_time = open_time;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
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
