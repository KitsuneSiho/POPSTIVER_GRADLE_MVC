package kr.bit.function.board.boardEntity;
//REPORT 에서 작성한내용을 관리자가 승인해주기 이전에 대기하는 테이블.
public class Temporary_post {
    private int temp_no;
    private String temp_title;
    private String temp_content;
    private String temp_host;
    private String temp_dist;
    private String temp_subdist;
    private String temp_location;
    private String temp_start;
    private String temp_end;
    private String open_time;
    private String temp_attachment;
    private int event_type;
    private String brand_link;
    private String brand_sns;

    public Temporary_post() {
        super();
    }

    public Temporary_post(int temp_no, String temp_title,
                          String temp_content,
                          String temp_host,
                          String temp_dist,
                          String temp_subdist,
                          String temp_location,
                          String temp_start,
                          String temp_end,
                          String open_time,
                          String temp_attachment,
                          int event_type,
                          String brand_link,
                          String brand_sns) {
        super();
        this.temp_no = temp_no;
        this.temp_title = temp_title;
        this.temp_content = temp_content;
        this.temp_host = temp_host;
        this.temp_dist = temp_dist;
        this.temp_subdist = temp_subdist;
        this.temp_location = temp_location;
        this.temp_start = temp_start;
        this.temp_end = temp_end;
        this.open_time = open_time;
        this.temp_attachment = temp_attachment;
        this.event_type = event_type;
        this.brand_link = brand_link;
        this.brand_sns = brand_sns;

    }

    public int getTemp_no() {
        return temp_no;
    }

    public void setTemp_no(int temp_no) {
        this.temp_no = temp_no;
    }

    public String getTemp_title() {
        return temp_title;
    }

    public void setTemp_title(String temp_title) {
        this.temp_title = temp_title;
    }

    public String getTemp_content() {
        return temp_content;
    }

    public void setTemp_content(String temp_content) {
        this.temp_content = temp_content;
    }

    public String getTemp_host() {
        return temp_host;
    }

    public void setTemp_host(String temp_host) {
        this.temp_host = temp_host;
    }

    public String getTemp_dist() {
        return temp_dist;
    }

    public void setTemp_dist(String temp_dist) {
        this.temp_dist = temp_dist;
    }

    public String getTemp_subdist() {
        return temp_subdist;
    }

    public void setTemp_subdist(String temp_subdist) {
        this.temp_subdist = temp_subdist;
    }

    public String getTemp_location() {
        return temp_location;
    }

    public void setTemp_location(String temp_location) {
        this.temp_location = temp_location;
    }

    public String getTemp_start() {
        return temp_start;
    }

    public void setTemp_start(String temp_start) {
        this.temp_start = temp_start;
    }

    public String getTemp_end() {
        return temp_end;
    }

    public void setTemp_end(String temp_end) {
        this.temp_end = temp_end;
    }

    public String getOpen_time() {
        return open_time;
    }

    public void setOpen_time(String open_time) {
        this.open_time = open_time;
    }

    public String getTemp_attachment() {
        return temp_attachment;
    }

    public void setTemp_attachment(String temp_attachment) {
        this.temp_attachment = temp_attachment;
    }

    public int getEvent_type() {
        return event_type;
    }

    public void setEvent_type(int event_type) {
        this.event_type = event_type;
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
