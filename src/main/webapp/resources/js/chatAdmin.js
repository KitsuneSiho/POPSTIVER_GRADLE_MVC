let adminStompClient = null;
const adminUsername = '관리자'; // 실제 구현에서는 관리자의 실제 사용자 이름으로 설정해야 합니다.

function connectAdmin() {
    const root = 'http://localhost:8080';
    const socket = new SockJS(root + '/chat-websocket');
    adminStompClient = Stomp.over(socket);
    adminStompClient.connect({}, function (frame) {
        console.log('관리자 연결됨: ' + frame);

        // 연결 시 관리자 이름 설정
        adminStompClient.send("/app/chat.addUser",
            {},
            JSON.stringify({sender: adminUsername, type: 'JOIN'})
        );

        // 공개 채널 구독
        adminStompClient.subscribe('/topic/public', function (message) {
            showAdminMessage(JSON.parse(message.body));
        });

        // 관리자 전용 개인 메시지 채널 구독
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

function sendAdminMessage() {
    const messageContent = document.getElementById('adminChatInput').value.trim();
    if (messageContent && adminStompClient) {
        const chatMessage = {
            sender: adminUsername,
            content: messageContent,
            type: 'CHAT'
        };

        // 공개 메시지 전송
        adminStompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));

        // 특정 사용자에게 개인 메시지 전송 (예: 마지막으로 메시지를 보낸 사용자에게)
        chatMessage.receiver = 'lastMessageSender'; // 실제 구현에서는 동적으로 설정해야 합니다.
        adminStompClient.send("/app/chat.private", {}, JSON.stringify(chatMessage));

        document.getElementById('adminChatInput').value = '';
    }
}

// 페이지가 로드되면 WebSocket 연결을 설정합니다.
document.addEventListener('DOMContentLoaded', function() {
    connectAdmin();
    document.getElementById('adminSendButton').addEventListener('click', sendAdminMessage);
});