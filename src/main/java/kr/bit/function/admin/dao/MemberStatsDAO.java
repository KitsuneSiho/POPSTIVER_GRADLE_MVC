package kr.bit.function.admin.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MemberStatsDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getGenderStats() {
        String sql = "SELECT user_gender, COUNT(*) AS count FROM user GROUP BY user_gender";
        return jdbcTemplate.query(sql, new RowMapper<Map<String, Object>>() {
            @Override
            public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
                Map<String, Object> map = new HashMap<>();
                map.put("user_gender", rs.getString("user_gender"));
                map.put("count", rs.getInt("count"));
                return map;
            }
        });
    }

    public List<Map<String, Object>> getSnsStats() {
        String sql = "SELECT " +
                "CASE " +
                "    WHEN user_id LIKE 'google%' THEN 'google' " +
                "    WHEN user_id LIKE 'naver%' THEN 'naver' " +
                "    WHEN user_id LIKE 'kakao%' THEN 'kakao' " +
                "    ELSE 'other' " +
                "END AS user_sns, " +
                "COUNT(*) AS count " +
                "FROM user " +
                "GROUP BY user_sns";
        return jdbcTemplate.query(sql, new RowMapper<Map<String, Object>>() {
            @Override
            public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
                Map<String, Object> map = new HashMap<>();
                map.put("user_sns", rs.getString("user_sns"));
                map.put("count", rs.getInt("count"));
                return map;
            }
        });
    }

    public List<Map<String, Object>> getAgeGroupStats() {
        String sql = "SELECT CASE " +
                "    WHEN TIMESTAMPDIFF(YEAR, STR_TO_DATE(user_birth, '%Y%m%d'), CURDATE()) < 20 THEN '10대 이하' " +
                "    WHEN TIMESTAMPDIFF(YEAR, STR_TO_DATE(user_birth, '%Y%m%d'), CURDATE()) BETWEEN 20 AND 29 THEN '20대' " +
                "    WHEN TIMESTAMPDIFF(YEAR, STR_TO_DATE(user_birth, '%Y%m%d'), CURDATE()) BETWEEN 30 AND 39 THEN '30대' " +
                "    WHEN TIMESTAMPDIFF(YEAR, STR_TO_DATE(user_birth, '%Y%m%d'), CURDATE()) BETWEEN 40 AND 49 THEN '40대' " +
                "    WHEN TIMESTAMPDIFF(YEAR, STR_TO_DATE(user_birth, '%Y%m%d'), CURDATE()) BETWEEN 50 AND 59 THEN '50대' " +
                "    ELSE '60대 이상' " +
                "END AS age_group, COUNT(*) AS count " +
                "FROM user " +
                "GROUP BY age_group " +
                "ORDER BY FIELD(age_group, '10대 이하', '20대', '30대', '40대', '50대', '60대 이상')";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Map<String, Object> map = new HashMap<>();
            map.put("age_group", rs.getString("age_group"));
            map.put("count", rs.getInt("count"));
            return map;
        });
    }

    public List<Map<String, Object>> getPopularTags() {
        String sql = "SELECT t.tag_name, COUNT(ut.tag_no) AS count " +
                "FROM user_tag ut " +
                "JOIN tag t ON ut.tag_no = t.tag_no " +
                "GROUP BY t.tag_name " +
                "ORDER BY count DESC " +
                "LIMIT 10";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Map<String, Object> map = new HashMap<>();
            map.put("tag_name", rs.getString("tag_name"));
            map.put("count", rs.getInt("count"));
            return map;
        });
    }
}