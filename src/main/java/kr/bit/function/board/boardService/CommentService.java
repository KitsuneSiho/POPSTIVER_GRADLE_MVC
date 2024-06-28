package kr.bit.function.board.boardService;

import kr.bit.function.board.boardDTO.FestivalCommentDTO;
import kr.bit.function.board.boardDTO.PopupCommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class CommentService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CommentService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    // 가입 시 사용자 정보 저장
    public void insertFestivalComment(FestivalCommentDTO festivalCommentDTO) {
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

    // 가입 시 사용자 정보 저장
    public void insertPopupComment(PopupCommentDTO popupCommentDTO) {
        String sql = "INSERT INTO popup_comment (event_type, comment_writer, visit_date, comment_content, popup_no, star_rate) VALUES (?, ?, ?, ?, ?, ?)";
        // board_view 값은 일단 하드코딩으로 1로 지정
        jdbcTemplate.update(sql, popupCommentDTO.getEvent_type(),
                popupCommentDTO.getComment_writer(),
                popupCommentDTO.getVisit_date(),
                popupCommentDTO.getComment_content(),
                popupCommentDTO.getPopup_no(),
                popupCommentDTO.getStar_rate()
        );
    }

    // 댓글 삭제
    public void deleteFestivalComment(int comment_no) {
        String sql = "DELETE FROM festival_comment WHERE comment_no = ?";
        jdbcTemplate.update(sql, comment_no);
    }

    // 댓글 삭제
    public void deletePopupComment(int comment_no) {
        String sql = "DELETE FROM popup_comment WHERE comment_no = ?";
        jdbcTemplate.update(sql, comment_no);
    }

}