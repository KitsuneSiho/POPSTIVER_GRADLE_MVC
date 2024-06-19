package com.example.function.page.pageEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;



@Entity
@Data
public class SearchResultEntity {

    @Id// 엔티티 클래스의 주요 키(primary key) 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@GeneratedValue: 주요 키 값을 자동으로 생성하는 방법 지정
    //strategy: 생성 방법
    //GenerationType.IDENTITY: JPA가 자동으로 주요 키값을 할당
    private Long title;
    private String content;
    private String location;
    private String tag;
}


