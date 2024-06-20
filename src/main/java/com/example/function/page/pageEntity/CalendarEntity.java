package com.example.function.page.pageEntity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
public class CalendarEntity {

    @Id
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private String type;
}
