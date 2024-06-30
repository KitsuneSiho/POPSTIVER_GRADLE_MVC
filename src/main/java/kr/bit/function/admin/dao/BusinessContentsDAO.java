package kr.bit.function.admin.dao;

import kr.bit.function.admin.model.businessContents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BusinessContentsDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<businessContents> getAllBusinessContents() {
        String sql = "SELECT * FROM temporary_post";
        return jdbcTemplate.query(sql, new BusinessContentsRowMapper());
    }

    private static final class BusinessContentsRowMapper implements RowMapper<businessContents> {
        public businessContents mapRow(ResultSet rs, int rowNum) throws SQLException {
            businessContents post = new businessContents();
            post.setTempNo(rs.getInt("temp_no"));
            post.setTempTitle(rs.getString("temp_title"));
            post.setTempContent(rs.getString("temp_content"));
            post.setTempHost(rs.getString("temp_host"));
            post.setTempDist(rs.getString("temp_dist"));
            post.setTempSubdist(rs.getString("temp_subdist"));
            post.setTempLocation(rs.getString("temp_location"));
            post.setTempStart(rs.getDate("temp_start"));
            post.setTempEnd(rs.getDate("temp_end"));
            post.setOpenTime(rs.getString("open_time"));
            post.setTempAttachment(rs.getString("temp_attachment"));
            post.setEventType(rs.getInt("event_type"));
            post.setBrandLink(rs.getString("brand_link"));
            post.setBrandSns(rs.getString("brand_sns"));
            return post;
        }
    }
}
