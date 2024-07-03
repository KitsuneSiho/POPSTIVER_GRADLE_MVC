$(document).ready(function () {
    getUserInfoAndSetUserId();
});

function getUserInfoAndSetUserId() {
    $.ajax({
        type: "GET",
        url: "member/getUserInfo",
        success: function (response) {
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
    var boardTitle = $("input[name='board_title']").val();
    var boardContent = $("textarea[name='board_content']").val();
    var userId = $("input[name='user_id']").val();
    var userName = $("input[name='user_name']").val();
    var boardAttachment = $("input[name='board_attachment']")[0].files[0];
    var boardViews = $("input[name='board_views']").val();
    var boardNo = $("input[name='board_no']").val();
    console.log(boardTitle);
    console.log(boardContent);
    console.log(userId);
    console.log(userName);
    console.log(boardAttachment);
    console.log(boardViews);
    console.log(boardNo);

    var formData = new FormData();
    formData.append("board_title", boardTitle);
    formData.append("board_content", boardContent);
    formData.append("user_id", userId);
    formData.append("user_name", userName);
    formData.append("board_views", boardViews);
    formData.append("board_no", boardNo);
    if (boardAttachment) {
        formData.append("file", boardAttachment);
    }

    $.ajax({
        type: "PUT",
        url: "/freeBoard/updateEdit",
        processData:false,
        contentType:false,
        data: formData,
        success: function (response) {
            // 업데이트 성공 시 처리할 코드
            alert("수정이 완료되었습니다!");
            window.location.href = "/free";
        },
        error: function (xhr, status, error) {
            // 실패 시 처리할 코드
            alert("수정중 오류가 발생했습니다.");
            window.location.href = "/free";

        }
    });


}