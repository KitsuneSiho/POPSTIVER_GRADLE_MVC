package kr.bit.function.member.repository;

import kr.bit.function.member.entity.TagEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TagRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public TagRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<TagEntity> findAllTags() {
        String sql = "SELECT * FROM tag";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRowToTag(rs));
    }

    private TagEntity mapRowToTag(ResultSet rs) throws SQLException {
        TagEntity tag = new TagEntity();
        tag.setTag_no(rs.getInt("tag_no"));
        tag.setTag_name(rs.getString("tag_name"));
        return tag;
    }
}
