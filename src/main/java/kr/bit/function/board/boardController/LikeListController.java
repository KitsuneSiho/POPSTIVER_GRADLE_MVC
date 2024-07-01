package kr.bit.function.board.boardController;

import kr.bit.function.board.boardDTO.LikeListDTO;
import kr.bit.function.board.boardService.LikeListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/like")
public class LikeListController {

    private final LikeListService likeListService;

    @Autowired
    public LikeListController(LikeListService likeListService) {
        this.likeListService = likeListService;
    }

    // 좋아요 추가 요청 처리
    @PutMapping("/add")
    @ResponseBody
    public String addLike(@RequestBody LikeListDTO likeListDTO) {

        System.out.println("이벤트타입:"+likeListDTO.getEvent_type());
        System.out.println("이벤트번호:"+likeListDTO.getEvent_no());
        System.out.println("사용자아이디:"+likeListDTO.getUser_id());
        System.out.println("사용자이름:"+likeListDTO.getUser_name());


        try {
            likeListService.insertLike(likeListDTO);
            return "liked";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @DeleteMapping("/remove/{eventNo}/{userName}/{eventType}")
    @ResponseBody
    public String removeLike(@PathVariable int eventNo, @PathVariable String userName, @PathVariable int eventType) {

        System.out.println("이벤트타입:"+eventType);
        System.out.println("이벤트번호:"+eventNo);
        System.out.println("사용자아이디:"+userName);
        try {
            likeListService.deleteLike(eventNo, eventType, userName);
            return "unliked";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
}
