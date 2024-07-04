document.addEventListener('DOMContentLoaded', function() { //DOM이 모두 로드되면 할 일
    var calendarEl = document.getElementById('calendar');
    let popup = document.querySelector('dialog'); //일정 클릭시 팝업

    // 서버에서 이벤트 데이터를 가져오는 함수
    function fetchEvents() {
        return fetch('/calendarapp/events')
            .then(response => {
                if (!response.ok) {
                    throw new Error('네트워크 응답 오류');
                }
                return response.json();
            })
            .then(data => data.map(event => ({
                title: event.title,
                start: new Date(event.start_date),
                url: generateEventUrl(event.event_type, event.event_no), // URL 필드 생성
                className: `event-type-${event.event_type}` // 이벤트 타입에 따른 클래스 추가

            })))
            .catch(error => {
                console.error('Error fetching events:', error);
                // if (confirm('로그인 후 이용해주세요.')) {
                    window.location.href = '/login'; // 로그인 화면으로 매핑
                // }
            });
    }
    function generateEventUrl(eventType, eventNo) {
        if (eventType === 1 || eventType === 2) {
            return '/festival_Details/' + eventNo;
        } else if (eventType === 3) {
            return '/popup_Details/' + eventNo;
        } else {
            return '#'; // 예외 처리: 다른 event_type 값에 대한 처리가 필요하면 여기에 추가
        }
    }


    fetchEvents().then(events => {
        var calendar = new FullCalendar.Calendar(calendarEl, {
            initialView: 'dayGridMonth',
            displayEventTime: false,
            events: events,
            titleFormat: { year: 'numeric', month: 'long' },
            dayMaxEventRows: 6, // 날짜당 최대 6개의 이벤트 표시
            moreLinkContent:function(args) {
                return '더보기'
            }, //6개이상 행사 더보기
            eventClick: function (info) {
                info.jsEvent.preventDefault();
                window.location.href = info.event.url; // 클릭 시 URL로 이동
            },
            eventDidMount: function(info) {
                if (info.event.title.length > 18) { // 제목이 18자 이상일 경우
                    info.el.querySelector('.fc-event-title').innerHTML = info.event.title.substring(0, 10) + '...';
                }
            }

        });
        calendar.render();
    });

    popup.querySelector('.close-button').addEventListener('click',()=>{
        popup.removeAttribute('open');
    });
});
