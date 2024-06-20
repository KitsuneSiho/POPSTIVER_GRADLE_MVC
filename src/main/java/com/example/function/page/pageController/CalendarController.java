package com.example.function.page.pageController;


import com.example.function.page.pageEntity.CalendarEntity;
import com.example.function.page.pageService.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/calendarapp")

public class CalendarController {

    @Autowired
    private CalendarService calendarService;

    // FullCalendar 뷰 페이지로 이동하는 핸들러
    @GetMapping
    public String calendar(Model model) {
        // 데이터베이스에서 일정 정보를 가져옴
        List<CalendarEntity> events = calendarService.getEventsFromDB();

        // FullCalendar에 필요한 JSON 데이터 형식으로 변환하지 않고 바로 전달
        model.addAttribute("events", events);

        return "calendar";
    }
    // FullCalendar에서 이벤트 데이터를 JSON 형식으로 제공하는 핸들러
    @GetMapping("/events")
    @ResponseBody
    public List<CalendarEntity> getEvents() {
        return calendarService.getEventsFromDB();
    }
    }
