package com.example.function.page.pageEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;



@Entity
@Data
public class CommBoardEntity {

    @Id
    private int board_no;
    private String board_title;
    private String board_content;
    private String user_name;
    private String board_post_date;
    private int views;
}