package kr.bit.function.page.pageDao;

import kr.bit.function.page.pageEntity.PopupUpcomingEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PopupUpcomingDAO {
    private JdbcTemplate jdbcTemplate;


    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public List<PopupUpcomingEntity> getUpcomingPopups() {
        String sql = "SELECT popup_no, popup_title, popup_attachment FROM popup WHERE popup_start > CURDATE() ORDER BY popup_start ASC LIMIT 10";
        List<PopupUpcomingEntity> results = jdbcTemplate.query(sql, new PopupUpcomingRowMapper());
        return results;
    }

    private static final class PopupUpcomingRowMapper implements RowMapper<PopupUpcomingEntity> {
        public PopupUpcomingEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            PopupUpcomingEntity popup = new PopupUpcomingEntity();
            popup.setPopupNo(rs.getInt("popup_no"));
            popup.setPopupTitle(rs.getString("popup_title"));
            popup.setPopupAttachment(rs.getString("popup_attachment"));
            return popup;
        }
    }
}