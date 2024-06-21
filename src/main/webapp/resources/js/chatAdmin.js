// chatAdmin.js

let adminStompClient = null;

function connectAdmin() {
    const socket = new SockJS(root + '/ws/chat');
    adminStompClient = Stomp.over(socket);
    adminStompClient.connect({}, function (frame) {
        console.log('관리자 연결됨: ' + frame);

        adminStompClient.subscribe('/user/queue/private', function (message) {
            showAdminMessage(JSON.parse(message.body));
        });
    }, function (error) {
        console.error('WebSocket connection error: ' + error);
    });
}

function showAdminMessage(message) {
    console.log('Received message: ', message);
    const chatBox = document.querySelector('.chatBox');
    const messageElement = document.createElement('div');
    messageElement.innerHTML = `<b>${message.sender}:</b> ${message.content}`;
    chatBox.appendChild(messageElement);
    chatBox.scrollTop = chatBox.scrollHeight;
}

// 페이지가 로드되면 WebSocket 연결을 설정합니다.
document.addEventListener('DOMContentLoaded', function() {
    connectAdmin();
});
