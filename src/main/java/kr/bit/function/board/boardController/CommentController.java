package kr.bit.function.board.boardController;

import kr.bit.function.board.boardDTO.FestivalCommentDTO;
import kr.bit.function.board.boardDTO.PopupCommentDTO;
import kr.bit.function.board.boardService.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/comment")
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;


    // 축제 코멘트 입력
    @PutMapping("/festivalInsert")
    @ResponseBody
    public void insertFreeWrite(@RequestBody FestivalCommentDTO festivalCommentDTO) {

        try {
            System.out.println("축제번호:"+festivalCommentDTO.getFestival_no());
            System.out.println("내용:"+festivalCommentDTO.getComment_content());
            System.out.println("작성자:"+festivalCommentDTO.getComment_writer());
            System.out.println("이벤트타입:"+festivalCommentDTO.getEvent_type());
            System.out.println("방문일자:"+festivalCommentDTO.getVisit_date());
            System.out.println("별점:"+festivalCommentDTO.getStar_rate());
            commentService.insertFestivalComment(festivalCommentDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 축제 코멘트 입력
    @PutMapping("/popupInsert")
    @ResponseBody
    public void insertFreeWrite(@RequestBody PopupCommentDTO popupCommentDTO) {

        try {
            System.out.println("축제번호:"+popupCommentDTO.getPopup_no());
            System.out.println("내용:"+popupCommentDTO.getComment_content());
            System.out.println("작성자:"+popupCommentDTO.getComment_writer());
            System.out.println("이벤트타입:"+popupCommentDTO.getEvent_type());
            System.out.println("방문일자:"+popupCommentDTO.getVisit_date());
            System.out.println("별점:"+popupCommentDTO.getStar_rate());
            commentService.insertPopupComment(popupCommentDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 댓글 삭제
    @DeleteMapping("/festival/{comment_no}")
    public ResponseEntity<String> deleteFestivalComment(@PathVariable("comment_no") int comment_no) {
        try {
            commentService.deleteFestivalComment(comment_no); // 서비스 레이어를 호출하여 댓글 삭제
            return ResponseEntity.ok("댓글이 성공적으로 삭제되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("댓글 삭제에 실패했습니다.");
        }
    }

    // 댓글 삭제
    @DeleteMapping("/popup/{comment_no}")
    public ResponseEntity<String> deletePopupComment(@PathVariable("comment_no") int comment_no) {
        try {
            commentService.deletePopupComment(comment_no); // 서비스 레이어를 호출하여 댓글 삭제
            return ResponseEntity.ok("댓글이 성공적으로 삭제되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("댓글 삭제에 실패했습니다.");
        }
    }
}

