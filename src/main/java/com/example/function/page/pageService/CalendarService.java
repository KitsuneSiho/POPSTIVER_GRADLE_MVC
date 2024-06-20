package com.example.function.page.pageService;

import com.example.function.page.pageMapper.CalendarMapper;
import com.example.function.page.pageEntity.CalendarEntity;

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
