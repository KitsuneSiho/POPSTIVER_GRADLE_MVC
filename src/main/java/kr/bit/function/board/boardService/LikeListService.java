package kr.bit.function.board.boardService;

import kr.bit.function.board.boardDTO.LikeListDTO;
import kr.bit.function.board.boardDTO.PopupCommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LikeListService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LikeListService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 좋아요 정보 저장
    public void insertLike(LikeListDTO likeListDTO) {
        String sql = "INSERT INTO like_list (event_type, event_no, user_id, user_name) VALUES (?, ?, ?, ?)";
        // board_view 값은 일단 하드코딩으로 1로 지정
        jdbcTemplate.update(sql, likeListDTO.getEvent_type(),
                likeListDTO.getEvent_no(),
                likeListDTO.getUser_id(),
                likeListDTO.getUser_name()
        );
    }

    // 좋아요 정보 삭제
    public void deleteLike(int eventNo, int eventType, String userName) {
        String sql = "DELETE FROM like_list WHERE event_no = ? and event_type = ? and user_name = ?";
        jdbcTemplate.update(sql, eventNo, eventType, userName);
    }
}
