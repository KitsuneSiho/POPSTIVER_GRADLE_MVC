package kr.bit.function.page.pageDao;

import kr.bit.function.page.pageEntity.FestivalUpcomingEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class FestivalUpcomingDAO {
    private JdbcTemplate jdbcTemplate;


    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public List<FestivalUpcomingEntity> getUpcomingFestivals() {
        String sql = "SELECT festival_no, festival_title, festival_attachment FROM festival WHERE festival_start > CURDATE() ORDER BY festival_start ASC LIMIT 30";
        List<FestivalUpcomingEntity> results = jdbcTemplate.query(sql, new FestivalUpcomingDAO.FestivalUpcomingRowMapper());
        return results;
    }

    private static final class FestivalUpcomingRowMapper implements RowMapper<FestivalUpcomingEntity> {
        public FestivalUpcomingEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            FestivalUpcomingEntity festival = new FestivalUpcomingEntity();
            festival.setFestivalNo(rs.getInt("festival_no"));
            festival.setFestivalTitle(rs.getString("festival_title"));
            festival.setFestivalAttachment(rs.getString("festival_attachment"));
            return festival;
        }
    }
}
