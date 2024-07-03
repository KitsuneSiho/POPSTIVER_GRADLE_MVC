package kr.bit.function.like;

import lombok.Data;

@Data
public class BookmarkDTO {
    private int event_no;
    private String title;
    private String startDate;
    private String endDate;
    private String location;
    private String attachment;
    private String dist;
    private String subdist;
    private int event_type;
}
