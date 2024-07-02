package kr.bit.function.page.pageController;

import kr.bit.function.board.boardEntity.PopupEntity;
import kr.bit.function.board.boardEntity.FestivalEntity;
import kr.bit.function.page.pageService.PopularAddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class PopularAddController {

    @Autowired
    private PopularAddService popularAddService;

    @GetMapping("/popularAddPopup")
    public String showPopularAddPopup(Model model) {
        List<PopupEntity> popups = popularAddService.getAllPopups();
        model.addAttribute("popups", popups);
        return "page/searchResult/popularAddPopup";
    }

    @GetMapping("/popularAddFestival")
    public String showPopularAddFestival(Model model) {
        List<FestivalEntity> festivals = popularAddService.getAllFestivals();
        model.addAttribute("festivals", festivals);
        return "page/searchResult/popularAddFestival";
    }
}
