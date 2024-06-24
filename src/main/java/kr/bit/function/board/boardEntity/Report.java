package kr.bit.function.board.boardEntity;

public class Report {
    private int report_no;
    private String report_title;
    private String report_content;
    private String report_host;
    private String report_dist;
    private String report_subdist;
    private String report_location;
    private String report_start;
    private String report_end;
    private String open_time;
    private String report_attachment;
    private int event_type;
    private String brand_link;
    private String brand_sns;
    private String report_date;
    private String user_id;
    private String user_name;

    public Report() {
        super();
    }

    public Report(int report_no,
                  String report_title,
                  String report_content,
                  String report_host,
                  String report_dist,
                  String report_subdist,
                  String report_location,
                  String report_start,
                  String report_end,
                  String open_time,
                  String report_attachment,
                  int event_type,
                  String brand_link,
                  String brand_sns,
                  String user_id,
                  String user_name) {
        super();
        this.report_no = report_no;
        this.report_title = report_title;
        this.report_content = report_content;
        this.report_host = report_host;
        this.report_dist = report_dist;
        this.report_subdist = report_subdist;
        this.report_location = report_location;
        this.report_start = report_start;
        this.report_end = report_end;
        this.open_time = open_time;
        this.report_attachment = report_attachment;
        this.event_type = event_type;
        this.brand_link = brand_link;
        this.brand_sns = brand_sns;
        this.user_id = user_id;
        this.user_name = user_name;
    }

    public int getReport_no() {
        return report_no;
    }

    public void setReport_no(int report_no) {
        this.report_no = report_no;
    }

    public String getReport_title() {
        return report_title;
    }

    public void setReport_title(String report_title) {
        this.report_title = report_title;
    }

    public String getReport_content() {
        return report_content;
    }

    public void setReport_content(String report_content) {
        this.report_content = report_content;
    }

    public String getReport_host() {
        return report_host;
    }

    public void setReport_host(String report_host) {
        this.report_host = report_host;
    }

    public String getReport_dist() {
        return report_dist;
    }

    public void setReport_dist(String report_dist) {
        this.report_dist = report_dist;
    }

    public String getReport_subdist() {
        return report_subdist;
    }

    public void setReport_subdist(String report_subdist) {
        this.report_subdist = report_subdist;
    }

    public String getReport_location() {
        return report_location;
    }

    public void setReport_location(String report_location) {
        this.report_location = report_location;
    }

    public String getReport_start() {
        return report_start;
    }

    public void setReport_start(String report_start) {
        this.report_start = report_start;
    }

    public String getReport_end() {
        return report_end;
    }

    public void setReport_end(String report_end) {
        this.report_end = report_end;
    }

    public String getOpen_time() {
        return open_time;
    }

    public void setOpen_time(String open_time) {
        this.open_time = open_time;
    }

    public String getReport_attachment() {
        return report_attachment;
    }

    public void setReport_attachment(String report_attachment) {
        this.report_attachment = report_attachment;
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

    public String getReport_date() {
        return report_date;
    }

    public void setReport_date(String report_date) {
        this.report_date = report_date;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}
