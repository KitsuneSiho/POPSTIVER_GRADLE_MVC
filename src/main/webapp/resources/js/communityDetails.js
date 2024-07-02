$(document).ready(function() {
    getUserInfoAndCompare();
});

function getUserInfoAndCompare() {
    $.ajax({
        type: "GET",
        url: "${root}/member/getUserInfo",
        success: function(response) {
            if (response && response.user_id) {
                compareUserIds(response.user_id);
            } else {
                console.error("사용자 정보를 가져오는 데 실패했습니다.");
            }
        },
        error: function (xhr, status, error) {
            console.error("사용자 정보를 가져오는 중 오류 발생: " + error);
        }
    });
}

function compareUserIds(sessionUserId) {
    var postUserId = "${community.user_id}";
    if (sessionUserId === postUserId) {
        var editButton = $('<button>').text('수정하기').click(function() {
            // 수정 페이지로 이동하는 로직
            window.location.href = "${root}/editPost/${community.board_id}";
        });
        $('#editButtonContainer').append(editButton);
    }
}