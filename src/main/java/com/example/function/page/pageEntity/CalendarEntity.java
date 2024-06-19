package com.example.function.page.pageEntity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class CalendarEntity {

    @Id
    private String title;
    private Date startDate;
    private Date endDate;
    private String type; // 'festival' 또는 'popup' 구분
}
