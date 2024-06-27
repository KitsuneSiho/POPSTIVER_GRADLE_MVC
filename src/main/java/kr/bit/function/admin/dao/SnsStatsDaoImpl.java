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
public class SnsStatsDaoImpl implements SnsStatsDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
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
}
