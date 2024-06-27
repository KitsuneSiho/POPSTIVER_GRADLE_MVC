package kr.bit.function.page.pageEntity;

import lombok.Data;

@Data
public class SearchResult {
    private int event_type; // 'popup' or 'festival'
    private String title;
    private String content;
    private String location;
    private String attachment; // 포스터 이미지
    private String start_date; // 시작일
    private String end_date; // 종료일
}
