package com.example.function.page.pageEntity;

import jakarta.persistence.*;
import lombok.Data;



@Entity
@Table(name = "events")
//엔터티가 매핑될 데이터베이스 테이블의 이름을 지정
@Data
public class CalendarEntity {

    @Id
    //엔터티의 주키(primary key) 필드
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //주키 값의 생성 전략을 지정, 여기서는 DB에서 자동으로 생성되는 식별자를 사용하는 것을 나타냄
    private Long id;
    private String title;
    private String description;
    private String startDateTime;
    private String endDateTime;
    private String url; //일정을 클릭했을 때 이동할 url
}
