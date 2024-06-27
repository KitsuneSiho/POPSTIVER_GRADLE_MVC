package kr.bit.function.admin.dao;

import kr.bit.function.member.entity.MemberEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserListDaoImpl implements UserListDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<MemberEntity> getAllUsers() {
        String sql = "SELECT * FROM user";
        return jdbcTemplate.query(sql, new RowMapper<MemberEntity>() {
            @Override
            public MemberEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
                MemberEntity user = new MemberEntity();
                user.setUser_no(rs.getInt("user_no"));
                user.setUser_type(rs.getString("user_type"));
                user.setUser_id(rs.getString("user_id"));
                user.setUser_name(rs.getString("user_name"));
                user.setUser_email(rs.getString("user_email"));
                user.setUser_birth(rs.getString("user_birth"));
                user.setUser_gender(rs.getString("user_gender"));
                user.setUser_nickname(rs.getString("user_nickname"));
                return user;
            }
        });
    }
}
