package com.example.function.page.pageService;

import com.example.function.page.pageMapper.CalendarMapper;
import com.example.function.page.pageEntity.CalendarEntity;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
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
    private static final String CREDENTIALS_FILE_PATH = "/API/credentials.json"; // 클라이언트 비밀 파일 경로
    // "/src/main/resources/credentials.json"
    private static final List<String> SCOPES = Collections.singletonList("https://www.googleapis.com/auth/calendar");


    @Autowired
    private CalendarMapper calendarMapper;

    //*********************추가된 부분
    //OAuth 2.0 인증을 처리하는 getCredentials 메서드
    private Credential getCredentials(final HttpTransport HTTP_TRANSPORT) throws IOException {
        // Load client secrets.
        InputStream in = CalendarService.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder( //사용자 인증 처리
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))//인증 정보 저장
                .setAccessType("offline")
                .build();

        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        //임시 로컬서버에서 API와 통신하는 인증코드를 받아온다.
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }
    //******************************************

    //DB에서 일정 정보를 가져와서 구글 캘린더에 추가
    public void addEventsFromDBToGoogleCalendar() throws IOException, GeneralSecurityException {
        List<CalendarEntity> events = calendarMapper.findAll();

        //Google API 클라이언트 초기화
        final HttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport(); //****NetHttpTransPort -> HttpTransPort로 수정
        //구글에서 제공하는 HTTP 트랜스포트 생성 / HTTPS 연결을 설정하는 데 사용되는 객체
        Credential credential = getCredentials(HTTP_TRANSPORT); //추가된 부분
        Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                //.Builder로 Calendar 객체 생성 / 매개변수: API와 통신에 사용/JSON데이터 처리에 사용/인증처리에 사용되나 이 코드에선 사용X
                .setApplicationName("Popstiver_Calendar")
                .build(); //객체 최종 생성

        // DB에서 가져온 일정 정보를 반복하며 구글 캘린더에 추가
        for (CalendarEntity event : events) {
            Event googleEvent = new Event()
                    .setSummary(event.getTitle())
                    .setStart(new EventDateTime().setDateTime(new DateTime(event.getStartDate())))
                    .setEnd(new EventDateTime().setDateTime(new DateTime(event.getEndDate())))
                    .setDescription(event.getType());

            // 구글 캘린더에 이벤트 추가 요청
            service.events().insert("primary", googleEvent).execute();
        }

    }
}
