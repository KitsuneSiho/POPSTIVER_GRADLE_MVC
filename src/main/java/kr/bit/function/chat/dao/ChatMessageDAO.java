package kr.bit.function.chat.dao;

import kr.bit.function.chat.model.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.List;

@Repository
public class ChatMessageDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_CHAT_MESSAGE_SQL = "INSERT INTO chat_messages (sender, receiver, content, type, timestamp) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_CHAT_MESSAGES_SQL = "SELECT * FROM chat_messages WHERE receiver = ? OR sender = ?";
    private static final String SELECT_ADMIN_CHAT_MESSAGES_SQL = "SELECT * FROM chat_messages WHERE receiver = 'admin' OR sender = 'admin'";
    private static final String DELETE_ALL_CHAT_MESSAGES_SQL = "DELETE FROM chat_messages";

    public void addChatMessage(ChatMessage chatMessage) {
        jdbcTemplate.update(INSERT_CHAT_MESSAGE_SQL, chatMessage.getSender(), chatMessage.getReceiver(), chatMessage.getContent(), chatMessage.getType(), chatMessage.getTimestamp());
    }

    public List<ChatMessage> getChatMessages(String user) {
        return jdbcTemplate.query(SELECT_ALL_CHAT_MESSAGES_SQL, new ChatMessageRowMapper(), user, user);
    }

    public List<ChatMessage> getAdminChatMessages() {
        return jdbcTemplate.query(SELECT_ADMIN_CHAT_MESSAGES_SQL, new ChatMessageRowMapper());
    }

    public void clearChatMessages() {
        jdbcTemplate.update(DELETE_ALL_CHAT_MESSAGES_SQL);
    }

    private static class ChatMessageRowMapper implements RowMapper<ChatMessage> {
        @Override
        public ChatMessage mapRow(ResultSet rs, int rowNum) throws SQLException {
            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setId(rs.getInt("id"));
            chatMessage.setSender(rs.getString("sender"));
            chatMessage.setReceiver(rs.getString("receiver"));
            chatMessage.setContent(rs.getString("content"));
            chatMessage.setType(rs.getString("type"));
            chatMessage.setTimestamp(rs.getTimestamp("timestamp").toInstant());
            return chatMessage;
        }
    }
}