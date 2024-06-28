package kr.bit.function.member.service;

import kr.bit.function.member.entity.MemberEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MemberService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveUser(MemberEntity user) {
        String sql = "INSERT INTO user (user_type, user_id, user_name, user_email, user_birth, user_gender, user_nickname) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getUser_type(), user.getUser_id(), user.getUser_name(), user.getUser_email(), user.getUser_birth(), user.getUser_gender(), user.getUser_nickname());
    }

    public void updateUserInfo(MemberEntity user) {
        String sql = "UPDATE user SET user_type = ?, user_nickname = ? WHERE user_id = ?";
        jdbcTemplate.update(sql, user.getUser_type(), user.getUser_nickname(), user.getUser_id());
    }

    public MemberEntity findById(String userId) {
        String trimmedUserId = userId.replaceAll("\\s+", "");
        String sql = "SELECT * FROM user WHERE user_id = ?";
        try {
            MemberEntity user = jdbcTemplate.queryForObject(sql, new Object[]{trimmedUserId}, new BeanPropertyRowMapper<>(MemberEntity.class));
            System.out.println("User fetched from DB: " + user);
            return user;
        } catch (EmptyResultDataAccessException e) {
            System.out.println("User not found: " + e.getMessage());
            return null;
        }
    }

    public void deleteUserByEmail(String userId) {
        String sql = "DELETE FROM user WHERE user_id = ?";
        jdbcTemplate.update(sql, userId);
    }

    public boolean existsById(String userId) {
        String sql = "SELECT COUNT(*) FROM user WHERE user_id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{userId}, Integer.class);
        return count != null && count > 0;
    }

    // 닉네임 중복 확인 메서드 추가
    public boolean existsByNickname(String nickname) {
        String sql = "SELECT COUNT(*) FROM user WHERE user_nickname = ?";
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{nickname}, Integer.class);
        return count != null && count > 0;
    }
}
