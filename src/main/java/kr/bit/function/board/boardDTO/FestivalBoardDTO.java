package kr.bit.function.board.boardDTO;

import lombok.Data;

@Data
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
    private String festival_tag1;
    private String festival_tag2;
    private String festival_tag3;
    private String festival_tag4;
    private String festival_tag5;

    // Getters and Setters
    // 생성자와 getter, setter 메서드를 확인합니다.
    // 예시:
    public FestivalBoardDTO(int festival_no, String festival_title, String festival_content, String host,
                            String festival_dist, String festival_subdist, String festival_location,  String festival_start, String festival_end,
                            String open_time, String festival_post_date, String festival_attachment,
                            int event_type, int like_that, int views, String brand_link, String brand_sns,
                            String festival_tag1, String festival_tag2, String festival_tag3, String festival_tag4, String festival_tag5) {
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
        this.festival_tag1 = festival_tag1;
        this.festival_tag2 = festival_tag2;
        this.festival_tag3 = festival_tag3;
        this.festival_tag4 = festival_tag4;
        this.festival_tag5 = festival_tag5;
    }
}
