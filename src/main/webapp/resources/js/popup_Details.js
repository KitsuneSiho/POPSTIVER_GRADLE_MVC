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

                var userName = response.user_nickname;
                var userType = response.user_type;

                console.log(userType);
                // 삭제 버튼 표시 여부 업데이트
                updateDeleteButtonVisibility(userName, userType);
                updateEditButtonVisibility(userName);

            } else {
                console.error("사용자 정보를 가져오는 데 실패했습니다.");
            }
        },
        error: function (xhr, status, error) {
            console.error("사용자 정보를 가져오는 중 오류 발생: " + error);
        }
    });
}

function updateDeleteButtonVisibility(userName, userType) {
    $('.delete').each(function () {
        var commentWriter = $(this).data('comment-writer'); // 댓글 작성자 가져오기

        // 댓글 작성자와 현재 사용자 이름 비교하여 삭제 버튼 표시 여부 결정
        if (commentWriter === userName || userType === 'ROLE_ADMIN') {
            $(this).show(); // 삭제 버튼 표시
        } else {
            $(this).hide(); // 삭제 버튼 숨김
        }
    });
}

function updateEditButtonVisibility(userName) {
    $('.edit-button').each(function () {
        var commentWriter = $(this).data('comment-writer'); // 댓글 작성자 가져오기

        console.log(commentWriter);
        // 댓글 작성자와 현재 사용자 이름 비교하여 삭제 버튼 표시 여부 결정
        if (commentWriter === userName) {
            $(this).show(); // 삭제 버튼 표시
        } else {
            $(this).hide(); // 삭제 버튼 숨김
        }
    });
}

function openCustomAlert(message) {
    // 설정한 메시지를 모달에 표시
    $('#customAlertMessage').text(message);

    // 모달 보이기
    $('.custom-alert-modal').css('display', 'block');
}
// 저장하기 버튼을 누르면
function submitForm(event) {
// 폼 데이터 변수로 가져오기

    event.preventDefault(); // 폼 제출 방지

// 폼 데이터 변수로 가져오기
    var popupNo = $("input[name='popup_no']").val();
    var eventType = $("input[name='event_type']").val();
    var userId = $("input[name='user_id']").val();
    var userName = $("input[name='user_name']").val();
    var commentContent = $("input[name='comment_content']").val();
    var visitDate = $("input[name='visit_date']").val();
    var starRate = $("#star_rate").val();

    console.log(popupNo);
    console.log(eventType);
    console.log(userId);
    console.log(userName);
    console.log(commentContent);
    console.log(visitDate);
    console.log(starRate);

    $.ajax({
        method: "put",
        url: "/comment/popupInsert",
        contentType: 'application/json;charset=utf-8',
// StudentAndInfo 객체를 JSON 문자열로 변환하여 전송
        data: JSON.stringify({
            "popup_no": popupNo,
            "event_type": eventType,
            "comment_writer": userName,
            "comment_content": commentContent,
            "visit_date": visitDate,
            "star_rate": starRate
        }),

        success: function (response) {
// 업데이트 성공 시 처리할 코드
            var message = "후기 등록이 완료되었습니다!";
            openCustomAlert(message);
            location.reload();
// 필요한 경우 추가적인 UI 업데이트 등을 수행할 수 있음
        },
        error: function (xhr, status, error) {
// 실패 시 처리할 코드
            var errorMessage = "후기 저장 중 오류가 발생했습니다.";
            openCustomAlert(errorMessage);
            location.reload();
        }
    });


}

function deleteComment(comment_no) {
    if (confirm("정말로 이 후기를 삭제하시겠습니까?")) {
        $.ajax({
            method: "delete",
            url: "/comment/popup/" + comment_no,
            success: function (response) {
                console.log(comment_no);
                // If deletion is successful, you might want to update the UI accordingly
                alert('후기가 성공적으로 삭제되었습니다.');
                // Refresh the comment section or remove the deleted comment row
                // Example: $(`tr[data-comment-no='${commentNo}']`).remove();
                location.reload(); // Refresh the page (or update the UI as per your requirement)
            },
            error: function (xhr, status, error) {
                console.log(comment_no);
                alert('후기 삭제에 실패했습니다.');
                location.reload();
            }
        });
    }
}

var year = '';
var month = '';
var day = '';

// 현재 날짜를 yyyy-mm-dd 형식으로 포맷팅하는 함수
function getCurrentDate() {
    const today = new Date();
    year = today.getFullYear();
    month = String(today.getMonth() + 1).padStart(2, '0'); // 월은 0부터 시작하므로 +1
    day = String(today.getDate()).padStart(2, '0');
    return year + "-" + month + "-" + day;
}

// 페이지가 로드될 때 현재 날짜를 max 속성에 설정
document.addEventListener('DOMContentLoaded', () => {
    const dateInput = document.querySelector('.commentDate');
    dateInput.max = getCurrentDate();

    if (typeof loadUserLikes === 'function') {
        loadUserLikes();
    }
});

// 후기 수정을 위한 함수
function editComment(commentNo, commentContent, starRate, visitDate) {
    // 수정할 폼의 값을 설정
    document.querySelector('input[name="comment_content"]').value = commentContent;
    document.querySelector('input[name="star_rate"]').value = starRate;
    document.querySelector('input[name="visit_date"]').value = visitDate;

    // commentNo를 hidden으로 전달하여 서버에서 식별할 수 있도록 함
    var commentNoInput = document.createElement('input');
    commentNoInput.type = 'hidden';
    commentNoInput.name = 'comment_no';
    commentNoInput.value = commentNo;
    document.getElementById('commentForm').appendChild(commentNoInput);

    // 기존 별점 표시
    document.querySelectorAll('.new-star').forEach(star => {
        if (star.dataset.value <= starRate) {
            star.classList.add('selected');
        }
    });

    var submitButton = document.querySelector('#commentForm button[type="submit"]');
    submitButton.textContent = '수정 완료';

    // 방문일을 readonly로 설정
    $("input[name='visit_date']").prop('readonly', true);

    // 스크롤을 폼 위치로 이동
    document.getElementById('commentForm').scrollIntoView();

    // 수정 완료 버튼 클릭 시 처리할 이벤트 추가
    submitButton.onclick = function(event) {
        event.preventDefault();

        var commentContent = $("input[name='comment_content']").val();
        var starRate = $("#star_rate").val();
        var commentNo = $("input[name='comment_no']").val();

        $.ajax({
            method: "put",
            url: "/comment/updatePopup", // 수정용 URL로 변경
            contentType: 'application/json;charset=utf-8',
            data: JSON.stringify({
                "comment_no": commentNo, // 수정할 댓글 번호
                "comment_content": commentContent, // 수정할 댓글 내용
                "star_rate": starRate // 수정할 별점
            }),
            success: function (response) {
                var message = "후기 수정이 완료되었습니다!";
                openCustomAlert(message);
                location.reload();
            },
            error: function (xhr, status, error) {
                var errorMessage = "후기 수정 중 오류가 발생했습니다.";
                openCustomAlert(errorMessage);
                location.reload();
            }
        });

    };
}