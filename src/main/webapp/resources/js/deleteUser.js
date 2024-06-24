$(document).ready(function() {
    $("#deleteUserForm").on("submit", function(event) {
        event.preventDefault(); // 기본 폼 제출 방지

        $.ajax({
            type: "DELETE",
            url: "/member/deleteUser",
            success: function(response) {
                showCustomAlert("회원 탈퇴가 완료되었습니다.");
                // 메인 페이지로의 리다이렉트를 여기서 제거
            },
            error: function(xhr, status, error) {
                showCustomAlert("회원 탈퇴 중 오류가 발생했습니다.");
            }
        });
    });

    // 모달 확인 버튼 클릭 시 처리
    $(".custom-alert-close").on("click", function() {
        closeCustomAlert();
        window.location.href = "/main"; // 모달 닫은 후 메인 페이지로 이동
    });
});

function showCustomAlert(message) {
    $("#customAlertMessage").text(message);
    $("#customAlertModal").css("display", "flex");
}

function closeCustomAlert() {
    $("#customAlertModal").css("display", "none");
}