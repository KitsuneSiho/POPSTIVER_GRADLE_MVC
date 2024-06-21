package kr.bit.function.chat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatPageController {

    // 관리자를 위한 채팅 페이지 경로 추가
    @GetMapping("/admin/chat")
    public String getAdminChatPage() {
        return "page/admin/adminChat"; // adminChat.jsp 페이지로 이동
    }
}
