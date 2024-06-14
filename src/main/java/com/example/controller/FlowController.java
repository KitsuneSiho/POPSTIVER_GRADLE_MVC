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
    @GetMapping("/contact")//
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



}
