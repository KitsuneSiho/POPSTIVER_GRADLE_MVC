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
    var reportTitle = $("input[name='report_title']").val();
    var reportEventType = $("input[name='event_type']:checked").val();
    var reportContent = $("textarea[name='report_content']").val();
    var reportLocation = $("input[name='report_location']").val();
    var reportStart = $("input[name='report_start']").val();
    var reportEnd = $("input[name='report_end']").val();
    var reportHost = $("input[name='report_host']").val();
    var reportBrandLink = $("input[name='brand_link']").val();
    var reportBrandSns = $("input[name='brand_sns']").val();
    var userId = $("input[name='user_id']").val();
    var userName = $("input[name='user_name']").val();

    $.ajax({
        method: "put",
        url: "report/reportWrite",
        contentType: 'application/json;charset=utf-8',
// StudentAndInfo 객체를 JSON 문자열로 변환하여 전송
        data: JSON.stringify({
            "report_title": reportTitle,
            "event_type": reportEventType,
            "report_content": reportContent,
            "report_location": reportLocation,
            "report_start" : reportStart,
            "report_end" : reportEnd,
            "report_host" : reportHost,
            "brand_link" : reportBrandLink,
            "brand_sns" : reportBrandSns,
            "user_id": userId,
            "user_name":userName
        }),

        success: function (response) {
// 업데이트 성공 시 처리할 코드
            alert("제보가 완료되었습니다!");
            window.location.href = "/reportList";
// 필요한 경우 추가적인 UI 업데이트 등을 수행할 수 있음
        },
        error: function (xhr, status, error) {
// 실패 시 처리할 코드
            alert("제보에 실패하였습니다.");
            window.location.href = "/reportList";
        }
    });


}