package com.example.function.page.pageService;

import com.example.function.page.pageEntity.CalendarEntity;
import com.example.function.page.pageRepository.CalendarRepository;
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
    private CalendarRepository calendarRepository;

    //DB에서 일정 정보를 가져와서 구글 캘린더에 추가
    public void addEventsFromDBToGoogleCalendar() throws IOException, GeneralSecurityException {
        List<CalendarEntity> events = calendarRepository.findAll();

        //Google API 클라이언트 초기화
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        //구글에서 제공하는 HTTP 트랜스포트 생성 / HTTPS 연결을 설정하는 데 사용되는 객체
        Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, null)
                //.Builder로 Calendar 객체 생성 / 매개변수: API와 통신에 사용/JSON데이터 처리에 사용/인증처리에 사용되나 이 코드에선 사용X
                .setApplicationName("Popstiver_Calendar")
                .build(); //객체 최종 생성

        //가져온 일정 정보를 반복하여 구글 캘린더에 추가
        for (CalendarEntity eventEntity : events) {
            Event event = new Event()
                    .setSummary(eventEntity.getTitle()) //제목 설정
                    .setDescription(eventEntity.getDescription()); // 설명 설정

            DateTime start = new DateTime(eventEntity.getStartDateTime());
            EventDateTime startEventDateTime = new EventDateTime()
                    .setDateTime(start) //시작 일시
                    .setTimeZone("UTC"); //시간대 설정
            event.setStart(startEventDateTime);

            DateTime end = new DateTime(eventEntity.getEndDateTime());
            EventDateTime endEventDateTime = new EventDateTime()
                    .setDateTime(end) //종료 일시
                    .setTimeZone("UTC");
            event.setEnd(endEventDateTime);

            // URL을 설정하여 클릭 시 리다이렉트
            if (eventEntity.getUrl() != null) {
                Event.Source source = new Event.Source();
                source.setTitle("Event Link");
                source.setUrl(eventEntity.getUrl());
                event.setSource(source);

                //달력상에서는 일정 제목만 보이나, 일단은 정보가 필요할지도 모르니 코드는 남겨놓음

                //설정한 이벤트 Google 캘린더에 추가
                String calendarId = "primary";
                event = service.events().insert(calendarId, event).execute();
                //추가된 이벤트 링크 출력
                System.out.printf("Event created: %s\n", event.getHtmlLink());
            }
        }
    }
}
