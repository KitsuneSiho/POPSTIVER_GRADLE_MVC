package kr.bit.function.board.boardController;

import kr.bit.function.board.boardDTO.FestivalBoardDTO;

import kr.bit.function.board.boardService.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class MapController {
    @Autowired
    private BoardService boardService;

    //로그객체 선언하기.
    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

    @RequestMapping(value = "/map", method = RequestMethod.GET)
    public String festivalDetails(Model model) {
        try {
            // 모든 축제 정보
            List<FestivalBoardDTO> allFestivals = boardService.selectAll();
            model.addAttribute("allFestivals", allFestivals);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "page/etc/map";
    }
}