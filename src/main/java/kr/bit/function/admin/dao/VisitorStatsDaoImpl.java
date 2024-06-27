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
public class VisitorStatsDaoImpl implements VisitorStatsDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> getMonthlyVisitorStats() {
        String sql = "SELECT DATE_FORMAT(visit_time, '%Y-%m') AS month, COUNT(*) AS total_visits FROM visitor_log GROUP BY month";
        return jdbcTemplate.query(sql, new RowMapper<Map<String, Object>>() {
            @Override
            public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
                Map<String, Object> map = new HashMap<>();
                map.put("month", rs.getString("month"));
                map.put("total_visits", rs.getInt("total_visits"));
                return map;
            }
        });
    }
}
