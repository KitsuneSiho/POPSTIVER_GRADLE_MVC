package kr.bit.function.chat.controller;

import kr.bit.function.chat.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class ChatController {

    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);
    private final SimpMessagingTemplate messagingTemplate;

    public ChatController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/chat.message")
    public void handleMessage(@Payload ChatMessage chatMessage) {
        logger.info("Received message: {}", chatMessage);

        // 모든 메시지를 /topic/messages로 브로드캐스트
        messagingTemplate.convertAndSend("/topic/messages", chatMessage);
        logger.info("Message broadcasted to /topic/messages");
    }

    @MessageMapping("/chat.addUser")
    public void addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        String username = chatMessage.getSender();
        headerAccessor.getSessionAttributes().put("username", username);
        logger.info("User added to chat: {}", username);

        messagingTemplate.convertAndSend("/topic/messages", chatMessage);
    }
}