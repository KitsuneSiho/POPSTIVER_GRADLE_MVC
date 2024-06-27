document.addEventListener('DOMContentLoaded', function() {
    // Chart.js 설정
    let visitorCtx = document.getElementById('visitorChart').getContext('2d');
    let genderCtx = document.getElementById('genderChart').getContext('2d');
    let likesCtx = document.getElementById('likesChart').getContext('2d');
    let viewsCtx = document.getElementById('viewsChart').getContext('2d');
    let snsCtx = document.getElementById('snsChart').getContext('2d');

    // 예제 데이터 설정
    let visitorData = { /* 데이터 로드 */ };
    let genderData = { /* 데이터 로드 */ };
    let likesData = { /* 데이터 로드 */ };
    let viewsData = { /* 데이터 로드 */ };
    let snsData = { /* 데이터 로드 */ };

    // Chart 생성
    new Chart(visitorCtx, { type: 'line', data: visitorData });
    new Chart(genderCtx, { type: 'pie', data: genderData });
    new Chart(likesCtx, { type: 'bar', data: likesData });
    new Chart(viewsCtx, { type: 'bar', data: viewsData });
    new Chart(snsCtx, { type: 'doughnut', data: snsData });

    // 회원 데이터 로드
    $.ajax({
        url: '/fetchUsers',
        method: 'GET',
        success: function(data) {
            let userTableBody = document.getElementById('userTableBody');
            data.forEach(function(user) {
                let row = `<tr>
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.email}</td>
                    <td>${user.joinDate}</td>
                    <td><button class="btn btn-danger" onclick="deleteUser(${user.id})">삭제</button></td>
                </tr>`;
                userTableBody.innerHTML += row;
            });
        }
    });

    // // 1:1 채팅
    // let chatMessages = document.getElementById('chatMessages');
    // document.getElementById('sendMessageBtn').addEventListener('click', function() {
    //     let message = document.getElementById('chatInput').value;
    //     // 채팅 메시지 서버로 전송 로직 추가
    //     chatMessages.innerHTML += `<div class="message">${message}</div>`;
    //     document.getElementById('chatInput').value = '';
    // });
});

function deleteUser(userId) {
    $.ajax({
        url: '/deleteUser',
        method: 'POST',
        data: { id: userId },
        success: function() {
            // 사용자 삭제 후 테이블 업데이트 로직 추가
            alert('사용자가 삭제되었습니다.');
            location.reload();
        }
    });
}
