package kr.bit.function.member.repository;

import kr.bit.function.member.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public UserEntity findByUserId(String userId) {
        String sql = "SELECT * FROM user WHERE user_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{userId}, new UserEntityRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void saveUser(UserEntity user) {
        String sql = "INSERT INTO user (user_type, user_id, user_pw, user_name, user_email, user_birth, user_gender) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                user.getUserType(),
                user.getUserId(),
                user.getUserPw(),
                user.getUserName(),
                user.getUserEmail(),
                user.getUserBirth(),
                user.getUserGender());
    }

    public void updateUser(UserEntity user) {
        String sql = "UPDATE user SET user_type = ?, user_pw = ?, user_name = ?, user_email = ?, user_birth = ?, user_gender = ? WHERE user_id = ?";
        jdbcTemplate.update(sql,
                user.getUserType(),
                user.getUserPw(),
                user.getUserName(),
                user.getUserEmail(),
                user.getUserBirth(),
                user.getUserGender(),
                user.getUserId());
    }

    public void saveOrUpdateUser(UserEntity user) {
        UserEntity existingUser = findByUserId(user.getUserId());
        if (existingUser == null) {
            saveUser(user);
        } else {
            updateUser(user);
        }
    }

    private static class UserEntityRowMapper implements RowMapper<UserEntity> {
        @Override
        public UserEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserEntity user = new UserEntity();
            user.setUserNo(rs.getInt("user_no"));
            user.setUserType(rs.getInt("user_type"));
            user.setUserId(rs.getString("user_id"));
            user.setUserPw(rs.getString("user_pw"));
            user.setUserName(rs.getString("user_name"));
            user.setUserEmail(rs.getString("user_email"));
            user.setUserBirth(rs.getDate("user_birth"));
            user.setUserGender(rs.getString("user_gender"));
            return user;
        }
    }
}
