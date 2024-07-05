package kr.bit.function.chat.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Notification {
    private int id;
    private String sender;
    private String message;
    private LocalDateTime timestamp;
}