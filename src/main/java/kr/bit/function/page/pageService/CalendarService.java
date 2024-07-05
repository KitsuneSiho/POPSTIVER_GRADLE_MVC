package kr.bit.function.page.pageService;

import kr.bit.function.page.pageEntity.CalendarEntity;
import kr.bit.function.page.pageMapper.CalendarMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class CalendarService {

    @Autowired
    private CalendarMapper calendarMapper;

    public List<CalendarEntity> getEventsFromDB() {
        return calendarMapper.findAll();
    }
}