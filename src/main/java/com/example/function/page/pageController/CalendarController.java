package com.example.function.page.pageController;


import com.example.function.page.pageService.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Controller
//이 컨트롤러의 모든 메서드가 /calendar 경로에 매핑

public class CalendarController {

    @Autowired
    private CalendarService calendarService;

    @GetMapping("/calendarapp")
    public String syncEventWitGoogleCalendar() { //DB에서 가져온 데이터를 구글 캘린더에 동기화
        try {
            calendarService.addEventsFromDBToGoogleCalendar();
            return "redirect:/calendar";
        } catch (IOException | GeneralSecurityException e) {
            e.printStackTrace();
            return "redirect:/main";
        }

    }
}
