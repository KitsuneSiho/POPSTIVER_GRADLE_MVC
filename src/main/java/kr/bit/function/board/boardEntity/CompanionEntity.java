package kr.bit.function.board.boardEntity;

public class CompanionEntity {
    private int comp_no;
    private String comp_title;
    private String comp_content;
    private String user_name;
    private String user_id;
    private String comp_post_date;
    private String comp_views;
    public CompanionEntity() {
        super();
    }
    public CompanionEntity(int comp_no, String comp_title,
                           String comp_content,
                           String user_name,
                           String user_id,
                           String comp_post_date,
                           String comp_views) {
        super();
        this.comp_no = comp_no;
        this.comp_title = comp_title;
        this.comp_content = comp_content;
        this.user_name = user_name;
        this.user_id = user_id;
        this.comp_post_date = comp_post_date;
        this.comp_views = comp_views;

    }

    public int getComp_no() {
        return comp_no;
    }

    public void setComp_no(int comp_no) {
        this.comp_no = comp_no;
    }

    public String getComp_title() {
        return comp_title;
    }

    public void setComp_title(String comp_title) {
        this.comp_title = comp_title;
    }

    public String getComp_content() {
        return comp_content;
    }

    public void setComp_content(String comp_content) {
        this.comp_content = comp_content;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getComp_post_date() {
        return comp_post_date;
    }

    public void setComp_post_date(String comp_post_date) {
        this.comp_post_date = comp_post_date;
    }

    public String getComp_views() {
        return comp_views;
    }

    public void setComp_views(String comp_views) {
        this.comp_views = comp_views;
    }
}
