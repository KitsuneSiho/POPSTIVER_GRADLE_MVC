let loggedInNickname = '';

$(document).ready(function() {
    loginName();
});

function loginName() {
    $.ajax({
        type: "GET",
        url: root + "/member/getUserInfo",
        success: function(response) {
            // 응답에 user_nickname이 있으면 전역 변수에 설정
            if (response && response.user_nickname) {
                // 닉네임을 전역 변수에 저장
                loggedInNickname = response.user_nickname;

                // 닉네임을 세션 스토리지에 저장
                sessionStorage.setItem('loggedInNickname', loggedInNickname);

                // 닉네임을 UI에 표시
                $(".mainTopButton").prepend('<span class="userName">' +
                    response.user_nickname + '님 ' + '환영합니다 😊 |  </span>');
            } else {
                console.error("사용자 닉네임을 가져오는 데 실패했습니다.");
            }
        },
        error: function(xhr, status, error) {
            console.error("사용자 정보를 가져오는 중 오류 발생: " + error);
        }
    });
}
