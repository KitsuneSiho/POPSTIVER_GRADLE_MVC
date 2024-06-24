$(document).ready(function() {
    loginName();
});

function loginName() {
    $.ajax({
        type: "GET",
        url: root + "/member/getUserInfo",
        success: function(response) {
            if (response && response.user_nickname) {
                $(".mainTopButton").prepend('<span class="userName">' +
                    response.user_nickname + '님 ' + '환영합니다 😊 |  </span>');
            } else {
                console.error("사용자 이름을 가져오는 데 실패했습니다.");
            }
        },
        error: function(xhr, status, error) {
            console.error("사용자 정보를 가져오는 중 오류 발생: " + error);
        }
    });
}