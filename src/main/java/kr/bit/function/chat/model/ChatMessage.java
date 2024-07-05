package kr.bit.function.chat.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.Instant;

@Data
public class ChatMessage {
    private int id;
    private String sender;
    private String receiver;
    private String content;
    private String type;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX", timezone = "UTC")
    private Instant timestamp = Instant.now(); // 메시지 생성 시 현재 시간 설정
}
