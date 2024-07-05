window.onload = function() {
    const urlParams = new URLSearchParams(window.location.search);
    if (urlParams.has('accessDenied')) {
        alert('접근 권한이 없습니다. 사용자 유형을 변경하거나 관리자에게 문의하세요.');
    }
}
