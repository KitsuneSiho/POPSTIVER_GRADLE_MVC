package kr.bit.function.chat.repository;

import kr.bit.function.chat.model.ChatMessage;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ChatMessageRepository {

    private final JdbcTemplate jdbcTemplate;

    public ChatMessageRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 메시지 저장
    public void saveMessage(ChatMessage chatMessage) {
        String sql = "INSERT INTO chat_messages (content, sender, type) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, chatMessage.getContent(), chatMessage.getSender(), chatMessage.getType().toString());
    }

    // 모든 메시지 조회
    public List<ChatMessage> findAllMessages() {
        String sql = "SELECT * FROM chat_messages";
        return jdbcTemplate.query(sql, new ChatMessageRowMapper());
    }

    // 특정 사용자로부터의 메시지 조회
    public List<ChatMessage> findMessagesBySender(String sender) {
        String sql = "SELECT * FROM chat_messages WHERE sender = ?";
        return jdbcTemplate.query(sql, new ChatMessageRowMapper(), sender);
    }

    // 메시지 맵핑
    private static class ChatMessageRowMapper implements RowMapper<ChatMessage> {
        @Override
        public ChatMessage mapRow(ResultSet rs, int rowNum) throws SQLException {
            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setContent(rs.getString("content"));
            chatMessage.setSender(rs.getString("sender"));
            chatMessage.setType(ChatMessage.MessageType.valueOf(rs.getString("type")));
            return chatMessage;
        }
    }
}
