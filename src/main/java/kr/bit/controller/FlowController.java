package kr.bit.controller;

import kr.bit.function.like.BookmarkDTO;
import kr.bit.function.like.LikeService;
import kr.bit.function.page.pageEntity.FestivalUpcomingEntity;
import kr.bit.function.page.pageEntity.PopupUpcomingEntity;
import kr.bit.function.page.pageService.FestivalUpcomingService;
import kr.bit.function.page.pageService.PopupUpcomingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class FlowController {

    @Autowired
    private PopupUpcomingService popupUpcomingService;
    @Autowired
    private FestivalUpcomingService festivalUpcomingService;
    @Autowired
    private LikeService likeService;

    @GetMapping("/")//"/" 경로로 들어가면 일단 메인페이지로 리다이렉트 시킨다.
    public String home() {
        return "page/main/main";
    }

    @GetMapping("/login")//로그인페이지
    public String login_page() { return "page/myPage/login"; }

    @GetMapping("/myPage")//마이페이지
    public String my_page(@AuthenticationPrincipal OAuth2User principal, Model model) {
        return "forward:/member/myPage";
    }

    @GetMapping("calendar")//행사일정페이지
    public String calendar_page() {
        return "page/etc/calendar";
    }

    @GetMapping("deleteUser")//회원탈퇴페이지
    public String deleteUser_page() {
        return "page/myPage/deleteUser";
    }

    @GetMapping("money")//비즈니스문의페이지
    public String money_page() {
        return "page/board/money";
    }

    @GetMapping("freeWrite")//자유게시판글작성페이지
    public String freeWrite_page() {
        return "page/board/freeWrite";
    }

    @GetMapping("reportWrite")//제보게시판글작성페이지
    public String reportWrite_page() {
        return "page/board/reportWrite";
    }

    @GetMapping("togetherWrite")//동행게시판글작성페이지
    public String togetherWrite_page() {
        return "page/board/togetherWrite";
    }

    @GetMapping("noticeWrite")
    public String noticeWrite_page() {return "page/board/noticeWrite"; }

    @GetMapping("popularAdd")//인기 페스티벌 정보 페이지(메인페이지에서 인기 더보기 눌렀을때)
    public String popularAdd_page() {
        return "page/searchResult/popularAdd";
    }

    @GetMapping("openAdd")//오픈예정 페스티벌 정보 페이지(메인페이지에서 오픈예정 더보기 눌렀을때)
    public String openAdd_page() {
        return "page/searchResult/openAdd";
    }

    @GetMapping("mainFestival")//페스티벌메인페이지(메인에서 페스티벌 눌렀을때)
    public String mainFestival_page(Model model) {
        List<FestivalUpcomingEntity> upcomingFestivals = festivalUpcomingService.getUpcomingFestivals();
        List<BookmarkDTO> popularFestivals = likeService.getPopularFestivalEvents(10); // 인기 페스티벌 10개 가져오기
        model.addAttribute("upcomingFestivals", upcomingFestivals);
        model.addAttribute("popularFestivals", popularFestivals);
        return "page/main/mainFestival";
    }

    @GetMapping("popularAddFestival")//인기 페스티벌 정보 페이지(페스티벌페이지에서 인기 더보기 눌렀을때)
    public String popularAddFestival_page() {
        return "page/searchResult/popularAddFestival";
    }

    @GetMapping("openAddFestival")//오픈예정 페스티벌 정보 페이지(페스티벌페이지에서 오픈예정 더보기 눌렀을때)
    public String openAddFestival_page() {
        return "page/searchResult/openAddFestival";
    }

    @GetMapping("mainPopup")
    public String mainPopup_page(Model model) {
        List<PopupUpcomingEntity> upcomingPopups = popupUpcomingService.getUpcomingPopups();
        List<BookmarkDTO> popularPopups = likeService.getPopularPopupEvents(10); // 인기 팝업 10개 가져오기
        model.addAttribute("upcomingPopups", upcomingPopups);
        model.addAttribute("popularPopups", popularPopups);
        return "page/main/mainPopup";
    }

    @GetMapping("popularAddPopup")//인기 팝업 정보 페이지(팝업페이지에서 인기 더보기 눌렀을때)
    public String popularAddPopup_page() {
        return "page/searchResult/popularAddPopup";
    }

    @GetMapping("openAddPopup")//오픈예정 팝업 정보 페이지(팝업페이지에서 오픈예정 더보기 눌렀을때)
    public String openAddPopup_page() {
        return "page/searchResult/openAddPopup";
    }

    @GetMapping("posterInfo")//행사세부정보페이지
    public String posterInfo_page() {
        return "page/searchResult/posterInfo";
    }
}
