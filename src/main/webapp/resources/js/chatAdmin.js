$(document).ready(function() {
    let contextPath = 'http://localhost:8080';
    let stompClient = null;
    let currentRecipient = null;
    let userList = [];
    let messageHistory = {}; // 사용자별 메시지 히스토리를 저장할 객체

    function connect() {
        let socket = new SockJS(contextPath + '/chat-websocket');
        stompClient = Stomp.over(socket);

        stompClient.connect({}, function(frame) {
            console.log('Connected: ' + frame);

            stompClient.subscribe('/topic/messages', function(messageOutput) {
                let message = JSON.parse(messageOutput.body);
                console.log("Received message:", message);

                if (message.type === 'JOIN') {
                    addUserToList(message.sender);
                } else if (message.receiver === 'admin' || message.sender === 'admin') {
                    storeMessage(message);
                    if (currentRecipient === message.sender || message.sender === 'admin') {
                        showMessage(message);
                    }
                }
            });

            stompClient.send("/app/chat.addUser", {}, JSON.stringify({sender: 'admin', type: 'JOIN'}));
        });
    }

    function storeMessage(message) {
        let user = message.sender === 'admin' ? message.receiver : message.sender;
        if (!messageHistory[user]) {
            messageHistory[user] = [];
        }
        messageHistory[user].push(message);
    }

    function addUserToList(username) {
        if (username !== 'admin' && !userList.includes(username)) {
            userList.push(username);
            updateUserList();
        }
    }

    function updateUserList() {
        let $userList = $("#userList");
        $userList.empty();
        userList.forEach(function(user) {
            let $userItem = $("<li></li>").text(user);
            $userItem.click(function() {
                currentRecipient = user;
                $("#currentUser").text(user);
                displayChatHistory(user);
            });
            $userList.append($userItem);
        });
    }

    function displayChatHistory(user) {
        $("#adminChatBox").empty();
        if (messageHistory[user]) {
            messageHistory[user].forEach(showMessage);
        }
    }

    function sendMessage() {
        let messageContent = $("#adminChatInput").val().trim();
        if (messageContent && stompClient && currentRecipient) {
            let chatMessage = {
                sender: 'admin',
                receiver: currentRecipient,
                content: messageContent,
                type: 'CHAT'
            };

            stompClient.send("/app/chat.message", {}, JSON.stringify(chatMessage));
            $("#adminChatInput").val("");
            console.log("Admin sent message:", chatMessage);
        } else if (!currentRecipient) {
            alert("채팅할 사용자를 선택해주세요.");
        }
    }

    function formatAMPM(date) {
        let hours = date.getHours();
        let minutes = date.getMinutes();
        let ampm = hours >= 12 ? '오후' : '오전';
        hours = hours % 12;
        hours = hours ? hours : 12; // 0시를 12시로 변환
        minutes = minutes < 10 ? '0' + minutes : minutes;
        let strTime = ampm + ' ' + hours + ':' + minutes;
        return strTime;
    }

    function showMessage(message) {
        let messageElement = $("<div class='message'></div>");
        let timestamp = new Date(message.timestamp);
        messageElement.text(`[${formatAMPM(timestamp)}] ${message.sender}: ${message.content}`);
        if (message.sender === 'admin') {
            messageElement.addClass('sent');
        } else {
            messageElement.addClass('received');
        }
        $("#adminChatBox").append(messageElement);
        $("#adminChatBox").scrollTop($("#adminChatBox")[0].scrollHeight);
    }

    $("#adminSendButton").click(sendMessage);
    $("#adminChatInput").keypress(function(e) {
        if(e.which == 13) sendMessage();
    });

    connect();
});
