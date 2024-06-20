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
        chatMessageRepository.saveMessage(chatMessage);
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(ChatMessage chatMessage) {
        chatMessage.setContent(chatMessage.getSender() + " joined!");
        chatMessageRepository.saveMessage(chatMessage);
        return chatMessage;
    }

    // 채팅 기록을 반환하는 메서드 추가
    @GetMapping("/chat/history")
    public List<ChatMessage> getChatHistory() {
        return chatMessageRepository.findAllMessages();
    }
}
