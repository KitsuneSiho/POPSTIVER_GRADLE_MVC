package kr.bit.function.member.repository;

import kr.bit.function.member.entity.TagEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import kr.bit.function.member.entity.UserTagEntity;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserTagRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserTagRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 사용자 태그 저장 메소드
    public void saveUserTags(String userId, List<Integer> tagList) {
        String sql = "INSERT INTO user_tag (user_id, tag_no) VALUES (?, ?)";

        // 태그 목록을 순회하며 각각의 태그를 저장
        for (Integer tagNo : tagList) {
            jdbcTemplate.update(sql, userId, tagNo);
        }
    }

    // 사용자 태그 삭제 메소드
    public void deleteByUserId(String userId) {
        String sql = "DELETE FROM user_tag WHERE user_id = ?";
        jdbcTemplate.update(sql, userId);
    }

    // 사용자 태그 조회 메소드
    public List<UserTagEntity> findByUserId(String userId) {
        String sql = "SELECT * FROM user_tag WHERE user_id = ?";
        return jdbcTemplate.query(sql, new Object[]{userId}, (rs, rowNum) ->
                new UserTagEntity(rs.getString("user_id"), rs.getInt("tag_no"))
        );
    }
}
