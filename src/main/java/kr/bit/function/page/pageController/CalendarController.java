package kr.bit.function.page.pageController;

import kr.bit.function.page.pageEntity.CalendarEntity;
import kr.bit.function.page.pageService.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    // Festival Details 매핑
    @GetMapping("/festival_Details/{eventNo}")
    public String festivalDetails(@PathVariable("eventNo") int eventNo, Model model) {
        // 이벤트 번호에 맞는 축제 상세 정보 조회 로직
        // 예시로 생성된 메시지를 모델에 추가
        model.addAttribute("message", "축제 상세 정보 - Event No: " + eventNo);
        return "festival_Details"; // festival_details.jsp 뷰로 이동
    }

    // Popup Details 매핑
    @GetMapping("/popup_Details/{eventNo}")
    public String popupDetails(@PathVariable("eventNo") int eventNo, Model model) {
        // 이벤트 번호에 맞는 팝업 상세 정보 조회 로직
        // 예시로 생성된 메시지를 모델에 추가
        model.addAttribute("message", "팝업 상세 정보 - Event No: " + eventNo);
        return "popup_Details"; // popup_details.jsp 뷰로 이동
    }
}

