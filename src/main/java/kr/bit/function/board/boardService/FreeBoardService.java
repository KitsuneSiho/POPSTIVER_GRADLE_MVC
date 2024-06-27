package kr.bit.function.board.boardService;

import kr.bit.function.board.boardDTO.CommunityDTO;
import kr.bit.function.member.entity.MemberEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class FreeBoardService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FreeBoardService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    // 가입 시 사용자 정보 저장
    public void insertWrite(CommunityDTO communityDTO) {
        String sql = "INSERT INTO community (board_title, board_content, user_id, user_name, board_views, board_attachment) VALUES (?, ?, ?, ?, ?, ?)";
        // board_view 값은 일단 하드코딩으로 1로 지정
        jdbcTemplate.update(sql, communityDTO.getBoard_title(),
                communityDTO.getBoard_content(),
                communityDTO.getUser_id(),
                communityDTO.getUser_name(),
                communityDTO.getBoard_views(),
                communityDTO.getBoard_attachment()
        );
    }
}