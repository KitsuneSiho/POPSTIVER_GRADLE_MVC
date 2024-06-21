// chat.js

let stompClient = null;

function connect() {
    const socket = new SockJS('/ws/chat');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);

        stompClient.subscribe('/topic/messages', function (message) {
            showMessage(JSON.parse(message.body));
        });

        stompClient.subscribe('/user/queue/private', function (message) {
            showPrivateMessage(JSON.parse(message.body));
        });
    }, function (error) {
        console.error('WebSocket connection error: ' + error);
    });

    socket.onopen = function() {
        console.log('WebSocket connection opened.');
    };
    socket.onclose = function() {
        console.log('WebSocket connection closed.');
    };
}

function sendMessage() {
    const content = document.getElementById('chatInput').value;
    if (content.trim() !== "") {
        const message = {
            'sender': loggedInNickname,
            'content': content,
            'receiver': '관리자'
        };

        console.log('Sending message: ', message);

        // 메시지를 서버로 전송
        stompClient.send("/app/chat.send", {}, JSON.stringify(message));
        stompClient.send("/app/chat.private", {}, JSON.stringify(message));

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
        if (chatInput.value.trim()) {
            // 전송할 메시지를 화면에 "나" 대신 실제 닉네임으로 표시
            const messageElement = document.createElement('div');
            messageElement.textContent = `${loggedInNickname} : ${chatInput.value}`;
            chatBox.appendChild(messageElement);
            chatInput.value = '';
            chatBox.scrollTop = chatBox.scrollHeight;

            // 실제 메시지를 서버로 전송
            sendMessage();
        }
    });

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