package kr.bit.function.page.pageEntity;

import lombok.Data;

@Data
public class SearchResult {
    private String type; // 'popup' or 'festival'
    private String title;
    private String content;
    private String location;
    private String attachment; // 포스터 이미지
    private String startDate; // 시작일
    private String endDate; // 종료일
}
