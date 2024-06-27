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
public class GenderStatsDaoImpl implements GenderStatsDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
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
}
