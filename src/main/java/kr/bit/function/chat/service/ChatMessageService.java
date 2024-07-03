package kr.bit.function.chat.service;

import kr.bit.function.chat.dao.ChatMessageDAO;
import kr.bit.function.chat.model.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatMessageService {

    @Autowired
    private ChatMessageDAO chatMessageDAO;

    public void addChatMessage(ChatMessage chatMessage) {
        chatMessageDAO.addChatMessage(chatMessage);
    }

    public List<ChatMessage> getChatMessages(String user) {
        return chatMessageDAO.getChatMessages(user);
    }

    public List<ChatMessage> getAdminChatMessages() {
        return chatMessageDAO.getAdminChatMessages();
    }

    public void clearChatMessages() {
        chatMessageDAO.clearChatMessages();
    }
}