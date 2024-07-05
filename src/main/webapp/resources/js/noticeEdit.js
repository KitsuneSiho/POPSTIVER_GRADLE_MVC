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
    var noticeTitle = $("input[name='notice_title']").val();
    var noticeContent = $("textarea[name='notice_content']").val();
    var noticeNo = $("input[name='notice_no']").val();

    $.ajax({
        method: "put",
        url: "/contact/updateEdit",
        contentType: 'application/json;charset=utf-8',
// StudentAndInfo 객체를 JSON 문자열로 변환하여 전송
        data: JSON.stringify({
            "notice_title": noticeTitle,
            "notice_content": noticeContent,
            "notice_no": noticeNo
        }),

        success: function (response) {
// 업데이트 성공 시 처리할 코드
            alert("공지 수정이 완료되었습니다!");
            window.location.href = "/contact";
// 필요한 경우 추가적인 UI 업데이트 등을 수행할 수 있음
        },
        error: function (xhr, status, error) {
// 실패 시 처리할 코드
            alert("수정에 실패하였습니다.");
            window.location.href = "/contact";
        }
    });


}