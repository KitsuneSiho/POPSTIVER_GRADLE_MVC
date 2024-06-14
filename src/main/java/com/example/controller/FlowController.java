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
        return "page/calender";
    }
    @GetMapping("deleteUser")//회원탈퇴페이지
    public String deleteUser_page() {
        return "page/deleteUser";
    }
    @GetMapping("mainFestival")//페스티벌메인페이지(메인에서 페스티벌 눌렀을때)
    public String mainFestival_page() {
        return "page/mainFestival";
    }
    @GetMapping("mainPopup")//팝업메인페이지(메인에서 팝업 눌렀을때)
    public String mainPopup_page() {
        return "page/mainPopup";
    }
    @GetMapping("posterInfo")//행사세부정보페이지
    public String posterInfo_page() {
        return "page/posterInfo";
    }
    @GetMapping("searchResult")//검색결과페이지
    public String searchResult_page() {
        return "page/searchResult";
    }



}
