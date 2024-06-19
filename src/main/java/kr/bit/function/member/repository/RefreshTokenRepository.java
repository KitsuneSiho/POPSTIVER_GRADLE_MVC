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

    // principal_name 기반으로 모든 Refresh 토큰 삭제
    public void deleteRefreshTokensByUsername(String username) {
        String sql = "DELETE FROM oauth2_authorized_client WHERE principal_name = ?";
        jdbcTemplate.update(sql, username);
    }

    // refresh_token_value 기반으로 개별 Refresh 토큰 삭제
    public void deleteRefreshToken(String refreshToken) {
        String sql = "DELETE FROM oauth2_authorized_client WHERE refresh_token_value = ?";
        jdbcTemplate.update(sql, refreshToken);
    }
}
