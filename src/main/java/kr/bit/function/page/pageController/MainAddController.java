package kr.bit.function.page.pageController;

import kr.bit.function.board.boardEntity.PopupEntity;
import kr.bit.function.board.boardEntity.FestivalEntity;
import kr.bit.function.page.pageService.MainAddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class MainAddController {

    @Autowired
    private MainAddService mainAddService;

    @GetMapping("/popularAddPopup")
    public String showPopularAddPopup(Model model) {
        List<PopupEntity> popups = mainAddService.getAllPopups();
        model.addAttribute("popups", popups);
        return "page/searchResult/popularAddPopup";
    }

    @GetMapping("/popularAddFestival")
    public String showPopularAddFestival(Model model) {
        List<FestivalEntity> festivals = mainAddService.getAllFestivals();
        model.addAttribute("festivals", festivals);
        return "page/searchResult/popularAddFestival";
    }

    @GetMapping("/openAddPopup")
    public String showAddPopup(Model model) {
        List<PopupEntity> popups = mainAddService.getOpenPopups();
        model.addAttribute("popups", popups);
        return "page/searchResult/openAddPopup";
    }

    @GetMapping("/openAddFestival")
    public String showAddFestival(Model model) {
        List<FestivalEntity> festivals = mainAddService.getOpenFestivals();
        model.addAttribute("festivals", festivals);
        return "page/searchResult/openAddFestival";
    }
}
