$(document).ready(function() {
    getUserInfoAndSetUserId();
});

function getUserInfoAndSetUserId() {
    $.ajax({
        type: "GET",
        url: "member/getUserInfo",
        success: function(response) {
            if (response && response.user_id && response.user_nickname) {
// Set the user_id and user_nickname in the hidden input fields
                $("#user_id").val(response.user_id);
                $("#user_name").val(response.user_nickname);
            } else {
                console.error("사용자 정보를 가져오는 데 실패했습니다.");
            }
        },
        error: function (xhr, status, error) {
            console.error("사용자 정보를 가져오는 중 오류 발생: " + error);
        }
    });
}

// 저장하기 버튼을 누르면
function submitForm(event) {
// 폼 데이터 변수로 가져오기

    event.preventDefault(); // 폼 제출 방지


// 폼 데이터 변수로 가져오기
    var tempTitle = $("input[name='temp_title']").val();
    var tempType = $("input[name='event_type']:checked").val();
    var tempContent = $("textarea[name='temp_content']").val();
    var tempLocation = $("input[name='temp_location']").val();
    var tempStart = $("input[name='temp_start']").val();
    var tempEnd = $("input[name='temp_end']").val();
    var tempHost = $("input[name='temp_host']").val();

    $.ajax({
        method: "put",
        url: "money/register",
        contentType: 'application/json;charset=utf-8',
// StudentAndInfo 객체를 JSON 문자열로 변환하여 전송
        data: JSON.stringify({
            "temp_title": tempTitle,
            "event_type": tempType,
            "temp_content": tempContent,
            "temp_location": tempLocation,
            "temp_start" : tempStart,
            "temp_end" : tempEnd,
            "temp_host" : tempHost
        }),

        success: function (response) {
// 업데이트 성공 시 처리할 코드
            alert("등록이 요청되었습니다!");
            window.location.href = "/contact";
// 필요한 경우 추가적인 UI 업데이트 등을 수행할 수 있음
        },
        error: function (xhr, status, error) {
// 실패 시 처리할 코드
            alert("등록에 실패하였습니다.");
            window.location.href = "/contact";
        }
    });


}