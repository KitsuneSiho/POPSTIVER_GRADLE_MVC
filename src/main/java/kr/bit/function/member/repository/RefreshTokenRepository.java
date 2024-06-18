package kr.bit.function.member.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RefreshTokenRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RefreshTokenRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 사용자 이름 기반으로 모든 Refresh 토큰 삭제
    public void deleteRefreshTokensByUsername(String username) {
        String sql = "DELETE FROM refresh_tokens WHERE username = ?";
        jdbcTemplate.update(sql, username);
    }

    // Refresh 토큰 자체로 삭제 (개별 토큰 삭제)
    public void deleteRefreshToken(String refreshToken) {
        String sql = "DELETE FROM refresh_tokens WHERE token = ?";
        jdbcTemplate.update(sql, refreshToken);
    }
}
