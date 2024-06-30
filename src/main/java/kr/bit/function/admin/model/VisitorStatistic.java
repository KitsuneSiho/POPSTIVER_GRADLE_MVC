package kr.bit.function.admin.model;

import lombok.Data;

import java.sql.Date;

@Data
public class VisitorStatistic {
    private Date visitDate;
    private int visitCount;


}