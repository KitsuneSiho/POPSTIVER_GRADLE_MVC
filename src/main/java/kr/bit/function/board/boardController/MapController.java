package kr.bit.function.board.boardController;

import kr.bit.function.board.boardDTO.FestivalBoardDTO;
import kr.bit.function.board.boardDTO.PopupBoardDTO;
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

    @RequestMapping(value = "/map", method = RequestMethod.GET)
    public String festivalDetails(Model model) {
        try {

            List<FestivalBoardDTO> allFestivals = boardService.selectFestivalAll();
            List<PopupBoardDTO> allPopups = boardService.selectPopupAll();
            model.addAttribute("allFestivals", allFestivals);
            model.addAttribute("allPopups", allPopups);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "page/etc/map";
    }
}