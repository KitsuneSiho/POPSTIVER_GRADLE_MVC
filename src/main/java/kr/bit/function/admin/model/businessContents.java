package kr.bit.function.admin.model;

import lombok.Data;

import java.util.Date;

@Data
public class businessContents {
    private int tempNo;
    private String tempTitle;
    private String tempContent;
    private String tempHost;
    private String tempDist;
    private String tempSubdist;
    private String tempLocation;
    private Date tempStart;
    private Date tempEnd;
    private String openTime;
    private String tempAttachment;
    private int eventType;
    private String brandLink;
    private String brandSns;
}
