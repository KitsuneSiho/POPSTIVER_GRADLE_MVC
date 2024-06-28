package kr.bit.function.admin.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class VisitorLogDaoImpl implements VisitorLogDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insertVisitorLog(String ipAddress, String userAgent, String pageVisited) {
        String sql = "INSERT INTO visitor_log (ip_address, user_agent, page_visited) VALUES (?, ?, ?)";
//        System.out.println("Executing SQL: " + sql + " with IP: " + ipAddress + ", User-Agent: " + userAgent + ", Page: " + pageVisited);
        jdbcTemplate.update(sql, ipAddress, userAgent, pageVisited);
    }
}
