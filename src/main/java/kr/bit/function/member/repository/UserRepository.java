package kr.bit.function.member.repository;

import kr.bit.function.member.entity.MemberEntity;
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

    public MemberEntity findByUserId(String userId) {
        String sql = "SELECT * FROM user WHERE user_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{userId}, new UserEntityRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private static class UserEntityRowMapper implements RowMapper<MemberEntity> {
        @Override
        public MemberEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            MemberEntity user = new MemberEntity();
            user.setUser_no(rs.getInt("user_no"));
            user.setUser_type(rs.getString("user_type"));
            user.setUser_id(rs.getString("user_id"));
            user.setUser_name(rs.getString("user_name"));
            user.setUser_email(rs.getString("user_email"));
            user.setUser_birth(rs.getString("user_birth"));
            user.setUser_gender(rs.getString("user_gender"));
            return user;
        }
    }
}
