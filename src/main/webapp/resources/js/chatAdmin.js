let adminStompClient = null;
const adminUsername = '관리자';
let currentRecipient = null;
const userMessages = {};

function connectAdmin() {
    const root = 'http://localhost:8080';
    const socket = new SockJS(root + '/chat-websocket');
    adminStompClient = Stomp.over(socket);
    adminStompClient.connect({}, function (frame) {
        console.log('관리자 연결됨: ' + frame);
        console.log('Admin Username: ' + adminUsername);  // 로그 추가

        adminStompClient.subscribe('/topic/public', function (message) {
            const msg = JSON.parse(message.body);
            handleIncomingMessage(msg);
        });

        adminStompClient.subscribe('/user/queue/private', function (message) {
            const msg = JSON.parse(message.body);
            handleIncomingMessage(msg);
        });
        // 관리자 연결 시 사용자 이름 설정
        adminStompClient.send("/app/chat.addUser",
            {},
            JSON.stringify({sender: adminUsername, type: 'JOIN'})
        );
    }, function (error) {
        console.error('WebSocket connection error: ' + error);
    });
}

function handleIncomingMessage(msg) {
    console.log('Message received: ', msg);
    if (msg.sender !== adminUsername) {
        if (!userMessages[msg.sender]) {
            userMessages[msg.sender] = [];
            updateUserList(msg.sender);
        }
        userMessages[msg.sender].push(msg);
        if (msg.sender === currentRecipient) {
            showAdminMessage(msg);
        }
    }
}

function showAdminMessage(message) {
    const chatBox = document.querySelector('.chatBox');
    const messageElement = document.createElement('div');
    messageElement.innerHTML = `<b>${message.sender}:</b> ${message.content}`;
    chatBox.appendChild(messageElement);
    chatBox.scrollTop = chatBox.scrollHeight;
}

function sendAdminMessage() {
    const messageContent = document.getElementById('adminChatInput').value.trim();
    if (messageContent && adminStompClient && currentRecipient) {
        const chatMessage = {
            sender: adminUsername,
            content: messageContent,
            receiver: currentRecipient,
            type: 'CHAT'
        };

        adminStompClient.send("/app/chat.private", {}, JSON.stringify(chatMessage));
        userMessages[currentRecipient].push(chatMessage);
        showAdminMessage(chatMessage);
        document.getElementById('adminChatInput').value = '';
    } else {
        alert('메시지를 보낼 사용자를 선택하세요.');
    }
}

function updateUserList(username) {
    const userList = document.getElementById('userList');
    if (!userList.querySelector(`li[data-username='${username}']`)) {
        const userElement = document.createElement('li');
        userElement.textContent = username;
        userElement.dataset.username = username;
        userElement.addEventListener('click', () => {
            currentRecipient = username;
            document.getElementById('currentUser').textContent = username;
            showUserMessages(username);
        });
        userList.appendChild(userElement);
    }
}

function showUserMessages(username) {
    const chatBox = document.querySelector('.chatBox');
    chatBox.innerHTML = '';
    const messages = userMessages[username] || [];
    messages.forEach(msg => {
        const messageElement = document.createElement('div');
        messageElement.innerHTML = `<b>${msg.sender}:</b> ${msg.content}`;
        chatBox.appendChild(messageElement);
    });
    chatBox.scrollTop = chatBox.scrollHeight;
}

document.addEventListener('DOMContentLoaded', function() {
    connectAdmin();
    document.getElementById('adminSendButton').addEventListener('click', sendAdminMessage);
});