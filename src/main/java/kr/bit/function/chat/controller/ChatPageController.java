package kr.bit.function.chat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatPageController {

    @GetMapping("/chat")
    public String getChatPage() {
        return "page/chat"; // chat.jsp 페이지로 이동
    }
}
