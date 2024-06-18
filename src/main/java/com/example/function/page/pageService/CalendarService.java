package com.example.function.page.pageService;

import com.example.function.page.pageMapper.CalendarMapper;
import com.example.function.page.pageEntity.CalendarEntity;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;



@Service
public class CalendarService {

    private static final String APPLICATION_NAME = "Popstiver_Calendar";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    //JSON파서(JsonFactory): Google API 클라이언트 라이브러리에서 JSON 데이터를 처리하는 데 사용/JSON 형식의 데이터를 파싱하고 생성하는 데 사용
    //->JacksonFactory는 Google API 클라이언트 라이브러리에서 JSON 데이터를 처리하는 데 필요
    private static final String TOKENS_DIRECTORY_PATH = "tokens";
    //토큰 디렉토리(tokens directory) = 인증 토큰(authentication token)을 저장하는 디렉토리
    //->한 번 사용자의 동의를 받은 후, 발급받은 인증 토큰을 저장해두고 재사용 가능

    @Autowired
    private CalendarMapper calendarMapper;


    //DB에서 일정 정보를 가져와서 구글 캘린더에 추가
    public void addEventsFromDBToGoogleCalendar() throws IOException, GeneralSecurityException {
        List<CalendarEntity> events = calendarMapper.findAll();

        //Google API 클라이언트 초기화
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        //구글에서 제공하는 HTTP 트랜스포트 생성 / HTTPS 연결을 설정하는 데 사용되는 객체
        Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, null)
                //.Builder로 Calendar 객체 생성 / 매개변수: API와 통신에 사용/JSON데이터 처리에 사용/인증처리에 사용되나 이 코드에선 사용X
                .setApplicationName("Popstiver_Calendar")
                .build(); //객체 최종 생성

        // DB에서 가져온 일정 정보를 반복하며 구글 캘린더에 추가
        for (CalendarEntity event : events) {
            Event googleEvent = new Event()
                    .setSummary(event.getTitle())
                    .setStart(new EventDateTime().setDateTime(new DateTime(event.getStartDate())))
                    .setEnd(new EventDateTime().setDateTime(new DateTime(event.getEndDate())))
                    .setDescription(event.getType()); // 예시로 'festival' 또는 'popup'을 설명에 넣었습니다.

            // 구글 캘린더에 이벤트 추가 요청
            service.events().insert("primary", googleEvent).execute();
        }

    }
}
