package kr.bit.function.page.pageEntity;

import lombok.Data;

import java.util.Date;

@Data
public class Popup {
    private int popupNo;
    private String popupTitle;
    private String popupContent;
    private String host;
    private String popupLocation;
    private Date popupStart;
    private Date popupEnd;
    private String openTime;
    private Date popupPostDate;
    private String popupAttachment;
    private String eventType;
    private int likeThat;
    private int views;
    private String brandLink;
    private String brandSns;
    private String popupDist;
    private String popupSubdist;
    private String popupTag1;
    private String popupTag2;
    private String popupTag3;
    private String popupTag4;
    private String popupTag5;

    // Getters and Setters
}
