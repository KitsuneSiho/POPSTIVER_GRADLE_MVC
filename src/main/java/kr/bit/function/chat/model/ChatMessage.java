package kr.bit.function.chat.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChatMessage {

    private String sender;
    private String receiver;
    private String content;
    private String type;
    private LocalDateTime timestamp; // 추가된 필드


}
