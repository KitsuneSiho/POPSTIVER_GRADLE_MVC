package kr.bit.function.member.dao;

import kr.bit.function.member.memberEntity.memberEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class memberDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public memberDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveUser(memberEntity user) {
        String sql = "INSERT INTO users (user_email) VALUES (?)";
        jdbcTemplate.update(sql, user.getUser_email());
    }

}
