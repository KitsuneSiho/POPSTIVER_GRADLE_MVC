$(document).ready(function () {
    getUserInfoAndSetUserId();

    // 별점 클릭 이벤트 핸들러
    $('.star').on('click', function () {
        var selectedStarValue = $(this).data('value');
        $('#star_rate').val(selectedStarValue);

        // 모든 별의 색상 초기화
        $('.star').removeClass('selected');

        // 선택된 별과 그 이전 별의 색상 변경
        $(this).addClass('selected');
        $(this).prevAll('.star').addClass('selected');
    });

    // 마우스를 올렸을 때 이벤트 핸들러
    $('.star').hover(function () {
        // 모든 별의 색상 초기화
        $('.star').removeClass('hovered');

        // 마우스를 올린 별과 그 이전 별의 색상 변경
        $(this).addClass('hovered');
        $(this).prevAll('.star').addClass('hovered');
    }, function () {
        // 마우스를 뗐을 때 모든 별의 hover 클래스 제거
        $('.star').removeClass('hovered');
    });
});

function getUserInfoAndSetUserId() {
    $.ajax({
        type: "GET",
        url: "/member/getUserInfo",
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
    var festivalNo = $("input[name='festival_no']").val();
    var eventType = $("input[name='event_type']").val();
    var userId = $("input[name='user_id']").val();
    var userName = $("input[name='user_name']").val();
    var commentContent = $("input[name='comment_content']").val();
    var visitDate = $("input[name='visit_date']").val();
    var starRate = $("#star_rate").val();

    console.log(festivalNo);
    console.log(eventType);
    console.log(userId);
    console.log(userName);
    console.log(commentContent);
    console.log(visitDate);
    console.log(starRate);

    $.ajax({
        method: "put",
        url: "/comment/festivalInsert",
        contentType: 'application/json;charset=utf-8',
// StudentAndInfo 객체를 JSON 문자열로 변환하여 전송
        data: JSON.stringify({
            "festival_no": festivalNo,
            "event_type": eventType,
            "comment_writer": userName,
            "comment_content": commentContent,
            "visit_date": visitDate,
            "star_rate": starRate
        }),

        success: function (response) {
// 업데이트 성공 시 처리할 코드
            alert("저장이 완료되었습니다!");
// 필요한 경우 추가적인 UI 업데이트 등을 수행할 수 있음
        },
        error: function (xhr, status, error) {
// 실패 시 처리할 코드
            alert("게시글 저장 중 오류가 발생했습니다.");
        }
    });


}

function deleteComment(comment_no) {
    if (confirm("정말로 이 댓글을 삭제하시겠습니까?")) {
        $.ajax({
            method: "delete",
            url: "/comment/festival/" + comment_no,
            success: function(response) {
                console.log(comment_no);
                // If deletion is successful, you might want to update the UI accordingly
                alert('댓글이 성공적으로 삭제되었습니다.');
                // Refresh the comment section or remove the deleted comment row
                // Example: $(`tr[data-comment-no='${commentNo}']`).remove();
                location.reload(); // Refresh the page (or update the UI as per your requirement)
            },
            error: function(xhr, status, error) {
                console.log(comment_no);
                alert('댓글 삭제에 실패했습니다.');
                console.error('Failed to delete comment:', error);
            }
        });
    }
}
