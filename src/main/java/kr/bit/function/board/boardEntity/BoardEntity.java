package kr.bit.function.board.boardEntity;

import lombok.Data;

@Data
public class BoardEntity {

    private int festival_no; // 보드넘버
    private String festival_title; // 제목
    private String festival_content; // 본문
    private String host;// 주최자
    private String festival_location;
    private String festival_dist; // ex) 서울시
    private String festival_subdist; // ex) 관악구
    private String festival_start;
    private String festival_end;
    private String open_time;
    private String festival_post_date;
    private String festival_attachment;
    private int event_type;
    private int like_that;
    private int views;
    private String brand_link;
    private String brand_sns;

    // 기본 생성자
    public BoardEntity() {
        super();
    }

    // 모든 필드를 초기화하는 생성자
    public BoardEntity(int festival_no, String festival_title, String festival_content, String host, String festival_location,
                       String festival_dist, String festival_subdist,
                       String festival_start, String festival_end, String open_time, String festival_post_date,
                       String festival_attachment, int event_type, int like_that, int views, String brand_link, String brand_sns) {
        super();
        this.festival_no = festival_no;
        this.festival_title = festival_title;
        this.festival_content = festival_content;
        this.host = host;
        this.festival_location = festival_location;
        this.festival_dist = festival_dist;
        this.festival_subdist = festival_start;
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