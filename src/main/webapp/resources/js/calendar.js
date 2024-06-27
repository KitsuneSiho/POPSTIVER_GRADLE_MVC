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
                url: event.url // URL 필드 추가

            })))
            .catch(error => {
                console.error('Error fetching events:', error);
                alert('Error fetching events. Please try again later.');
            });
    }

    fetchEvents().then(events => {
        var calendar = new FullCalendar.Calendar(calendarEl, {
            initialView: 'dayGridMonth',
            displayEventTime: false,
            events: events,
            titleFormat: { year: 'numeric', month: 'long' },
            dayMaxEventRows: 6, // 날짜당 최대 6개의 이벤트 표시
            moreLinkContent:function(args) {
                return '+' + args.num + ' 개 더보기'
            }, //6개이상 행사 더보기
            eventClick: function (info) {
                info.jsEvent.preventDefault();
                window.location.href = info.event.url; // 클릭 시 URL로 이동
            },

        });
        calendar.render();
    });

    popup.querySelector('.close-button').addEventListener('click',()=>{
        popup.removeAttribute('open');
    });
});
