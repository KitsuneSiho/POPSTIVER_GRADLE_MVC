package kr.bit.function.chat.controller;

import kr.bit.function.chat.model.ChatMessage;
import kr.bit.function.chat.repository.ChatMessageRepository;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ChatController {

    private final ChatMessageRepository chatMessageRepository;

    public ChatController(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(ChatMessage chatMessage) {
        chatMessageRepository.saveMessage(chatMessage); // 메시지를 데이터베이스에 저장
        return chatMessage; // 메시지를 브로드캐스트
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(ChatMessage chatMessage) {
        chatMessage.setContent(chatMessage.getSender() + " joined!"); // 유저가 채팅에 참여했음을 알림
        chatMessageRepository.saveMessage(chatMessage);
        return chatMessage;
    }

    // 채팅 기록을 반환하는 메서드
    @GetMapping("/chat/history")
    public List<ChatMessage> getChatHistory() {
        return chatMessageRepository.findAllMessages();
    }
}
