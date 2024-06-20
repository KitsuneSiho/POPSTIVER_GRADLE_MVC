package kr.bit.function.member.service;

import kr.bit.function.member.entity.MemberEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MemberService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MemberService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 가입 시 사용자 정보 저장
    public void saveUser(MemberEntity user) {
        String sql = "INSERT INTO user (user_type, user_id, user_name, user_email, user_birth, user_gender) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getUser_type(), user.getUser_id(), user.getUser_name(), user.getUser_email(), user.getUser_birth(), user.getUser_gender());
    }

    // 사용자 정보 수정
    public void updateUserInfo(String userId, String userEmail) {

        String sql = "UPDATE user SET user_email = ? WHERE user_id = ?";
        jdbcTemplate.update(sql, userEmail, userId);
        System.out.println("회원 정보 업데이트 완료: " + userId);
    }

    // 아이디를 기준으로 사용자 정보 받아오기
    public MemberEntity findById(String userId) {
        String sql = "SELECT * FROM user WHERE user_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{userId}, new BeanPropertyRowMapper<>(MemberEntity.class));
    }

    // 아이디를 기준으로 사용자 정보 삭제
    public void deleteUserByEmail(String userId) {
        String sql = "DELETE FROM user WHERE user_id = ?";
        jdbcTemplate.update(sql, userId);
    }

    // 아이디를 기준으로 사용자 존재 여부 확인
    public boolean existsById(String userId) {
        String sql = "SELECT COUNT(*) FROM user WHERE user_id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{userId}, Integer.class);
        return count != null && count > 0;
    }

}
