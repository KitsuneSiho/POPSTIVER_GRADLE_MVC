package kr.bit.function.board.boardService;

import kr.bit.function.board.boardDTO.FestivalCommentDTO;
import kr.bit.function.board.boardDTO.PopupCommentDTO;
import kr.bit.function.board.boardDTO.TotalCommentDTO;
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

    // 축제 댓글 삽입
    public void insertFestivalComment(FestivalCommentDTO festivalCommentDTO) {
        String sql = "INSERT INTO festival_comment (event_type, comment_writer, visit_date, comment_content, festival_no, star_rate) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, festivalCommentDTO.getEvent_type(),
                festivalCommentDTO.getComment_writer(),
                festivalCommentDTO.getVisit_date(),
                festivalCommentDTO.getComment_content(),
                festivalCommentDTO.getFestival_no(),
                festivalCommentDTO.getStar_rate());
    }

    // 팝업 댓글 삽입
    public void insertPopupComment(PopupCommentDTO popupCommentDTO) {
        String sql = "INSERT INTO popup_comment (event_type, comment_writer, visit_date, comment_content, popup_no, star_rate) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, popupCommentDTO.getEvent_type(),
                popupCommentDTO.getComment_writer(),
                popupCommentDTO.getVisit_date(),
                popupCommentDTO.getComment_content(),
                popupCommentDTO.getPopup_no(),
                popupCommentDTO.getStar_rate());
    }

    // 축제 댓글 삭제
    public void deleteFestivalComment(int comment_no) {
        String sql = "DELETE FROM festival_comment WHERE comment_no = ?";
        jdbcTemplate.update(sql, comment_no);
    }

    // 팝업 댓글 삭제
    public void deletePopupComment(int comment_no) {
        String sql = "DELETE FROM popup_comment WHERE comment_no = ?";
        jdbcTemplate.update(sql, comment_no);
    }

    // 축제 댓글 목록 조회
    public List<FestivalCommentDTO> getFestivalComments() {
        String sql = "SELECT * FROM festival_comment ORDER BY comment_date DESC";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(FestivalCommentDTO.class));
    }

    // 팝업 댓글 목록 조회
    public List<PopupCommentDTO> getPopupComments() {
        String sql = "SELECT * FROM popup_comment ORDER BY comment_date DESC";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(PopupCommentDTO.class));
    }

    // 최근 댓글 5개 가져오기
    public List<TotalCommentDTO> getRecentComments(int limit) {
        String sql = "(SELECT 'festival' AS type, TRIM(comment_writer) AS comment_writer, comment_content, comment_date FROM festival_comment) " +
                "UNION " +
                "(SELECT 'popup' AS type, TRIM(comment_writer) AS comment_writer, comment_content, comment_date FROM popup_comment) " +
                "ORDER BY comment_date DESC LIMIT ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TotalCommentDTO.class), limit);
    }

    // 축제 댓글 목록 조회 및 평균 star_rate 계산
    public double getFestivalStarAvg(int festivalNo) {
        String sql = "SELECT AVG(star_rate) FROM festival_comment WHERE festival_no = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{festivalNo}, Double.class);
    }

    // 축제 댓글 목록 조회 및 평균 star_rate 계산
    public double getPopupStarAvg(int popupNo) {
        String sql = "SELECT AVG(star_rate) FROM popup_comment WHERE popup_no = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{popupNo}, Double.class);
    }

}
