package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FlowController {

    // 메인페이지에서 각 페이지들로 이동시키는 페이지.

    @GetMapping("/")//"/" 경로로 들어가면 일단 메인페이지로 리다이렉트 시킨다.
    public String home() {
        return "redirect:/main";
    }

    @GetMapping("/main")//메인페이지
    public String main_page() {
        return "page/main";
    }

    @GetMapping("/login")//로그인페이지
    public String login_page() {
        return "page/login";
    }

    @GetMapping("/contact")//공지사항페이지(게시판메인페이지)
    public String contact_page() {
        return "page/contact";
    }

    @GetMapping("/myPage")//마이페이지
    public String my_page() {
        return "page/myPage";
    }

    @GetMapping("map")//지도페이지
    public String map_page() {
        return "page/map";
    }

    @GetMapping("bookmark")//관심행사페이지
    public String bookmark_page() {
        return "page/bookmark";
    }

    @GetMapping("calender")//행사일정페이지
    public String calender_page() {
        return "calendar";
    }

    @GetMapping("deleteUser")//회원탈퇴페이지
    public String deleteUser_page() {
        return "page/deleteUser";
    }

    @GetMapping("money")//비즈니스문의페이지
    public String money_page() {
        return "page/money";
    }

    @GetMapping("report")//제보하기게시판페이지
    public String report_page() {
        return "page/report";
    }

    @GetMapping("reportWrite")//제보하기게시판글작성페이지
    public String reportWrite_page() {
        return "page/reportWrite";
    }

    @GetMapping("together")//동행구하기게시판페이지
    public String together_page() {
        return "page/together";
    }

    @GetMapping("togetherWrite")//동행구하기게시판글작성페이지
    public String togetherWrite_page() {
        return "page/togetherWrite";
    }

    @GetMapping("free")//자유게시판페이지
    public String free_page() {
        return "page/free";
    }

    @GetMapping("freeWrite")//자유게시판글작성페이지
    public String freeWrite_page() {
        return "page/freeWrite";
    }

    @GetMapping("popularAdd")//인기 페스티벌 정보 페이지(메인페이지에서 인기 더보기 눌렀을때)
    public String popularAdd_page() {
        return "page/popularAdd";
    }

    @GetMapping("openAdd")//오픈예정 페스티벌 정보 페이지(메인페이지에서 오픈예정 더보기 눌렀을때)
    public String openAdd_page() {
        return "page/openAdd";
    }



    @GetMapping("mainFestival")//페스티벌메인페이지(메인에서 페스티벌 눌렀을때)
    public String mainFestival_page() {
        return "page/mainFestival";
    }

    @GetMapping("popularAddFestival")//인기 페스티벌 정보 페이지(페스티벌페이지에서 인기 더보기 눌렀을때)
    public String popularAddFestival_page() {
        return "page/popularAddFestival";
    }

    @GetMapping("openAddFestival")//오픈예정 페스티벌 정보 페이지(페스티벌페이지에서 오픈예정 더보기 눌렀을때)
    public String openAddFestival_page() {
        return "page/openAddFestival";
    }

    @GetMapping("mainPopup")//팝업메인페이지(메인에서 팝업 눌렀을때)
    public String mainPopup_page() {
        return "page/mainPopup";
    }

    @GetMapping("popularAddPopup")//인기 팝업 정보 페이지(팝업페이지에서 인기 더보기 눌렀을때)
    public String popularAddPopup_page() {
        return "page/popularAddPopup";
    }

    @GetMapping("openAddPopup")//오픈예정 팝업 정보 페이지(팝업페이지에서 오픈예정 더보기 눌렀을때)
    public String openAddPopup_page() {
        return "page/openAddPopup";
    }

    @GetMapping("posterInfo")//행사세부정보페이지
    public String posterInfo_page() {
        return "page/posterInfo";
    }

    @GetMapping("searchResult")//검색결과페이지
    public String searchResult_page() {
        return "page/searchResult";
    }

    @GetMapping("/calendar")//캘린더
    public String calendar_page() {return "page/calendar";}

}
