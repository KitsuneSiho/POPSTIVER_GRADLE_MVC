package kr.bit.function.page.pageEntity;

import lombok.Data;

import java.util.Date;

@Data
public class PopupUpcoming {
    private int popup_no;
    private String popup_title;
    private String popup_content;
    private String host;
    private String popup_location;
    private Date popup_start;
    private Date popup_end;
    private String open_time;
    private Date popup_post_date;
    private String popup_attachment;
    private String event_type;
    private int like_that;
    private int views;
    private String brand_link;
    private String brand_sns;
    private String popup_dist;
    private String popup_subdist;
    private String popup_tag1;
    private String popup_tag2;
    private String popup_tag3;
    private String popup_tag4;
    private String popup_tag5;

}
