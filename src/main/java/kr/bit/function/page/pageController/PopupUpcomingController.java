package kr.bit.function.page.pageController;

import kr.bit.function.page.pageEntity.PopupUpcoming;
import kr.bit.function.page.pageService.PopupUpcomingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PopupUpcomingController {

    private final PopupUpcomingService popupService;

    @Autowired
    public PopupUpcomingController(PopupUpcomingService popupService) {
        this.popupService = popupService;
    }

    @GetMapping("/upcomingPopups")
    public String getUpcomingPopups(Model model) {
        List<PopupUpcoming> upcomingPopups = popupService.getUpcomingPopups();
        model.addAttribute("upcomingPopups", upcomingPopups);
        return "mainPopup";
    }
}