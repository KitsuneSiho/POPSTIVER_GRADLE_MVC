$(document).ready(function() {
    $("#deleteUserForm").on("submit", function(event) {
        event.preventDefault(); // 기본 폼 제출 방지

        $.ajax({
            type: "DELETE",
            url: "/member/deleteUser",
            success: function(response) {
                alert("회원 탈퇴가 완료되었습니다.");
                window.location.href = "/main"; // 탈퇴 후 메인 페이지로 이동
            },
            error: function(xhr, status, error) {
                alert("회원 탈퇴 중 오류가 발생했습니다.");
            }
        });
    });
});