let sessionID = '';

$(document).ready(function() {
    loginID();
});

function loginID() {
    console.log("JSP에서 전달된 communityUserId: ", boardUserId); // 로그 추가
    $.ajax({
        type: "GET",
        url: root + "/member/getUserInfo",
        success: function(response) {
            // 응답에 user_id이 있으면 전역 변수에 설정
            if (response && response.user_id) {
                // 닉네임을 전역 변수에 저장
                sessionID = response.user_id;

                // 닉네임을 세션 스토리지에 저장
                sessionStorage.setItem('sessionID', sessionID);

                // 닉네임을 session_id에 표시
                $('#sessionIdSpan').text(sessionID);
                if(response.user_id === boardUserId) {
                    console.log("삭제 버튼 조건 충족"); // 로그 추가
                    $('#deleteButton').html('<button class="deleteButton" onclick="window.location.href=\'/deleteTogether/'+ boardNo +'\'">삭제하기</button>');
                    $('#editButton').html('<button class="editButton" onclick="window.location.href=\'/editTogether/' + boardNo + '\'">수정하기</button>');
                } else {
                    console.log("삭제 버튼 조건 불충족"); // 로그 추가
                }

            } else {
                console.error("사용자 닉네임을 가져오는 데 실패했습니다.");
            }
        },
        error: function(xhr, status, error) {
            console.error("사용자 정보를 가져오는 중 오류 발생: " + error);
        }
    });
}