package kr.bit.function.page.pageEntity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class CalendarEntity {

    @Id
    private String title;
    private LocalDate start_date;
    private LocalDate end_date;
}
