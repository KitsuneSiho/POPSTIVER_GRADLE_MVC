package kr.bit.function.page.pageEntity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class CalendarEntity {

    @Id
    private String title;
    private String start_date;
    private String end_date;
    private String url; // 일정 제목 클릭시 이동할 url
    private int event_type;
}
