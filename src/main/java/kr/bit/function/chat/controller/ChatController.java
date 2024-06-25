package kr.bit.function.chat.controller;

import kr.bit.function.chat.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
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

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        String username = (String) headerAccessor.getSessionAttributes().get("username");
        if (username != null) {
            chatMessage.setSender(username);
        }
        logger.info("Received message from {}: {}", chatMessage.getSender(), chatMessage.getContent());
        return chatMessage;
    }

    @MessageMapping("/chat.private")
    public void sendPrivateMessage(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        String senderUsername = chatMessage.getSender(); // 메시지에서 직접 발신자 이름을 가져옵니다.

        if (senderUsername == null || senderUsername.isEmpty()) {
            senderUsername = (String) headerAccessor.getSessionAttributes().get("username");
            if (senderUsername == null || senderUsername.isEmpty()) {
                logger.error("Sender username is null or empty");
                return; // 유효한 발신자 이름이 없으면 메시지 전송을 중단합니다.
            }
        }

        chatMessage.setSender(senderUsername);

        String recipientUsername = chatMessage.getReceiver();
        logger.info("Received private message: {}", chatMessage);
        logger.info("Sending private message from {} to {}: {}", senderUsername, recipientUsername, chatMessage.getContent());

        messagingTemplate.convertAndSendToUser(
                recipientUsername,
                "/queue/private",
                chatMessage
        );

        // 발신자에게도 메시지를 보냅니다 (채팅 창에 표시하기 위해)
        if (!senderUsername.equals(recipientUsername)) {
            messagingTemplate.convertAndSendToUser(
                    senderUsername,
                    "/queue/private",
                    chatMessage
            );
        }
    }

    @MessageMapping("/chat.addUser")
    public void addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        String username = chatMessage.getSender();
        headerAccessor.getSessionAttributes().put("username", username);
        logger.info("User added to chat: {}", username);
    }
}