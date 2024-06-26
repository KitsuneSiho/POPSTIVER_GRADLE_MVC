package kr.bit.function.board.boardEntity;

import lombok.Data;

@Data
public class FestivalEntity {
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
    public FestivalEntity() {
        super();
    }

    // 모든 필드를 초기화하는 생성자
    public FestivalEntity(int festival_no, String festival_title, String festival_content, String host, String festival_dist, String festival_subdist, String festival_location,
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

}