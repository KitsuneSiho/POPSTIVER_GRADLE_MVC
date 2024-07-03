package kr.bit.function.chat.controller;

import kr.bit.function.chat.model.ChatMessage;
import kr.bit.function.chat.service.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatMessageController {

    @Autowired
    private ChatMessageService chatMessageService;

    @PostMapping("/message")
    public String addChatMessage(@RequestBody ChatMessage chatMessage) {
        chatMessageService.addChatMessage(chatMessage);
        return "Chat message added successfully";
    }

    @GetMapping("/messages/{user}")
    public List<ChatMessage> getChatMessages(@PathVariable String user) {
        try {
            return chatMessageService.getChatMessages(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving messages", e);
        }
    }

    @GetMapping("/messages/admin")
    public List<ChatMessage> getAdminChatMessages() {
        try {
            return chatMessageService.getAdminChatMessages();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving messages", e);
        }
    }

    @DeleteMapping("/clear")
    public String clearChatMessages() {
        chatMessageService.clearChatMessages();
        return "Chat messages cleared successfully";
    }
}