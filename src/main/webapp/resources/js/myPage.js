$(document).ready(function() {
    // 문서 로드 시 사용자 정보 로드
    loadUserInfo();
});

function loadUserInfo() {
    $.ajax({
        type: "GET",
        url: "member/getUserInfo",
        success: function(response) {
            // 사용자 정보가 성공적으로 로드되면 폼에 데이터 설정
            var user = response;
            $("input[name='user_name']").val(user.user_name);
            $("input[name='user_email']").val(user.user_email);
            $("input[name='user_birth']").val(user.user_birth);
            $("input[name='user_nickName']").val(user.user_nickname);
            $("input[name='user_gender'][value='" + user.user_gender + "']").prop('checked', true);
            $("input[name='user_type'][value='" + user.user_type + "']").prop('checked', true);
        },
        error: function(xhr, status, error) {
            console.error("사용자 정보를 가져오는 중 오류 발생: " + error);
        }
    });
}

// 수정하기 버튼을 클릭할 시
function enableEdit() {
    $("#editButton").css("display", "none"); // 수정 버튼은 안 보이게
    $("#saveButton").css("display", "block"); // 저장 버튼은 보이게

    // 입력 필드들의 readonly 속성 해제
    $("input[name='user_nickName']").prop("readonly", false);

    // 라디오 버튼들의 disabled 속성 해제
    $("input[type='radio']").prop("disabled", false);
}

// 저장하기 버튼을 누르면
function submitForm(event) {
    // 폼 데이터 변수로 가져오기
    var userEmail = $("input[name='user_email']").val();
    var userNickname = $("input[name='user_nickName']").val();
    var userType = $("input[name='user_type']:checked").val();

    event.preventDefault(); // 폼 제출 방지

    $.ajax({
        method: "PUT",
        url: "member/updateUser",
        contentType: 'application/json;charset=utf-8',
        data: JSON.stringify({
            "user_email": userEmail,
            "user_nickname": userNickname,
            "user_type": userType
        }),
        success: function(response) {
            // 업데이트 성공 시 처리할 코드
            showCustomAlert("회원 정보가 업데이트되었습니다!");
            // 필요한 경우 추가적인 UI 업데이트 등을 수행할 수 있음
            $("#editButton").css("display", "block"); // 수정 버튼은 보이게
            $("#saveButton").css("display", "none"); // 저장 버튼은 안 보이게
            $("input[name='user_nickName']").prop("readonly", true);
            $("input[type='radio']").prop("disabled", true);
        },
        error: function(xhr, status, error) {
            // 실패 시 처리할 코드
            showCustomAlert("회원 정보 업데이트 중 오류가 발생했습니다.");
        }
    });
}

function showCustomAlert(message) {
    $("#customAlertMessage").text(message);
    $("#customAlertModal").css("display", "flex");
}

function closeCustomAlert() {
    $("#customAlertModal").css("display", "none");
}

