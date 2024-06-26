package kr.bit.function.board.boardDTO;

import lombok.Data;

@Data
public class PopupDTO {

    private int popup_no; // 보드넘버
    private String popup_title; // 제목
    private String popup_content; // 본문
    private String host;// 주최자
    private String popup_location;
    private String popup_dist; // ex) 서울시
    private String popup_subdist; // ex) 관악구
    private String popup_start;
    private String popup_end;
    private String open_time;
    private String popup_post_date;
    private String popup_attachment;
    private int event_type;
    private int like_that;
    private int views;
    private String brand_link;
    private String brand_sns;

    // 기본 생성자
    public PopupDTO() {
        super();
    }

    // 모든 필드를 초기화하는 생성자
    public PopupDTO(int popup_no, String popup_title, String popup_content, String host, String popup_location,
                       String popup_dist, String popup_subdist,
                       String popup_start, String popup_end, String open_time, String popup_post_date,
                       String popup_attachment, int event_type, int like_that, int views, String brand_link, String brand_sns) {
        super();
        this.popup_no = popup_no;
        this.popup_title = popup_title;
        this.popup_content = popup_content;
        this.host = host;
        this.popup_location = popup_location;
        this.popup_dist = popup_dist;
        this.popup_subdist = popup_subdist;
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

}