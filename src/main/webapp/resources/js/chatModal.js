// chat.js

let stompClient = null;

function connect() {
    const socket = new SockJS('/ws/chat'); // WebSocket 엔드포인트를 /ws/chat으로 변경
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);

        stompClient.subscribe('/topic/messages', function (message) {
            showMessage(JSON.parse(message.body));
        });

        stompClient.subscribe('/user/queue/private', function (message) {
            showPrivateMessage(JSON.parse(message.body));
        });
    });
}

function sendMessage() {
    const content = document.getElementById('chatInput').value;
    if (content.trim() !== "") {
        stompClient.send("/app/chat.send", {}, JSON.stringify({'sender': '사용자', 'content': content}));
        document.getElementById('chatInput').value = '';
    }
}

function showMessage(message) {
    const chatBox = document.querySelector('.chatBox');
    const messageElement = document.createElement('div');
    messageElement.innerHTML = `<b>${message.sender}:</b> ${message.content}`;
    chatBox.appendChild(messageElement);
    chatBox.scrollTop = chatBox.scrollHeight;
}

function showPrivateMessage(message) {
    const chatBox = document.querySelector('.chatBox');
    const messageElement = document.createElement('div');
    messageElement.innerHTML = `<b>${message.sender}:</b> ${message.content}`;
    chatBox.appendChild(messageElement);
    chatBox.scrollTop = chatBox.scrollHeight;
}

document.addEventListener('DOMContentLoaded', function() {
    connect();

    const chatButton = document.getElementById('chatButton');
    const chatModal = document.getElementById('chatModal');
    const closeChatModal = document.querySelector('.closeChatModal');
    const sendChatButton = document.getElementById('sendChatButton');
    const chatInput = document.getElementById('chatInput');
    const chatBox = document.querySelector('.chatBox');
    const chatModalContent = document.querySelector('.chatModalContent');

    chatButton.addEventListener('click', function() {
        chatModal.style.display = 'block';
    });

    closeChatModal.addEventListener('click', function() {
        chatModal.style.display = 'none';
    });

    // 채팅 메시지 보내기
    sendChatButton.addEventListener('click', function() {
        const message = "나 : " + chatInput.value;
        if (chatInput.value.trim()) {
            const messageElement = document.createElement('div');
            messageElement.textContent = message;
            chatBox.appendChild(messageElement);
            chatInput.value = '';
            chatBox.scrollTop = chatBox.scrollHeight;
        }
    });
    sendChatButton.addEventListener('click', sendMessage);

    // 모달 외부 클릭 시 모달 닫기
    window.addEventListener('click', function(event) {
        if (event.target === chatModal) {
            chatModal.style.display = 'none';
        }
    });

    // 모달 내부 클릭 시 이벤트 버블링 방지
    chatModalContent.addEventListener('click', function(event) {
        event.stopPropagation();
    });
});
