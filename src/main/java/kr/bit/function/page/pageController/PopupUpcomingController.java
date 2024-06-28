package kr.bit.function.page.pageController;

import kr.bit.function.page.pageEntity.Popup;
import kr.bit.function.page.pageService.PopupUpcomingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
public class PopupUpcomingController {

    @Autowired
    private PopupUpcomingService popupUpcomingService;

    @RequestMapping("/mainPopup")
    public String mainPopup(Model model) {
        List<Popup> upcomingPopups = popupUpcomingService.getUpcomingPopups();

        // 로그 추가
        for (Popup popup : upcomingPopups) {
            System.out.println(popup.getPopupTitle() + " - " + popup.getPopupStart());
        }

        model.addAttribute("upcomingPopups", upcomingPopups);
        return "mainPopup";
    }
}
