package kr.bit.function.board.boardController;

import kr.bit.function.board.boardDTO.FestivalCommentDTO;
import kr.bit.function.board.boardDTO.PopupCommentDTO;
import kr.bit.function.board.boardService.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}