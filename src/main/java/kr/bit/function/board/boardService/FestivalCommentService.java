package kr.bit.function.board.boardService;

import kr.bit.function.board.boardDTO.CommunityDTO;
import kr.bit.function.board.boardDTO.FestivalCommentDTO;
import kr.bit.function.member.entity.MemberEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class FestivalCommentService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FestivalCommentService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    // 가입 시 사용자 정보 저장
    public void insertComment(FestivalCommentDTO festivalCommentDTO) {
        String sql = "INSERT INTO festival_comment (event_type, comment_writer, visit_date, comment_content, festival_no, star_rate) VALUES (?, ?, ?, ?, ?, ?)";
        // board_view 값은 일단 하드코딩으로 1로 지정
        jdbcTemplate.update(sql, festivalCommentDTO.getEvent_type(),
                festivalCommentDTO.getComment_writer(),
                festivalCommentDTO.getVisit_date(),
                festivalCommentDTO.getComment_content(),
                festivalCommentDTO.getFestival_no(),
                festivalCommentDTO.getStar_rate()
        );
    }
}