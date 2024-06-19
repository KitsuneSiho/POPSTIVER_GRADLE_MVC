document.addEventListener('DOMContentLoaded', function() { //DOM이 모두 로드되면 할 일
    var calendarEl = document.getElementById('calender');
    let popup = document.querySelector('dialog'); //일정 클릭시 팝업
    var calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        titleFormat: function (date) {
            year = date.date.year;
            month = date.date.month + 1;

            return year + "년 " + month + "월";
        },
        googleCalendarApiKey: 'AIzaSyADh5T820X8HLvwpgo7gNI0lHNXlxKZ2jA',
        events: {
            googleCalendarId: 'b0628612029f22e6211617b44b91eb7e6844ec0b6cb45c9579cfa9d2ffca1723@group.calendar.google.com'
        },
        eventClick: function(info) {
            info.jsEvent.preventDefault(); // don't let the browser navigate
            popup.querySelector('div').innerHTML = `
          <h3>${info.event.title}</h3>
          <div>${info.event.extendedProps.description}</div>
          `;//팝업시 뜰 내용(일정 제목과 상세 정보)
            popup.setAttribute('open','open');

        }
    });
    calendar.render();
    popup.querySelector('.close-button').addEventListener('click',()=>{
        popup.removeAttribute('open');
    });
});

