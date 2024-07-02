$(document).ready(function() {
    let contextPath = 'http://localhost:8080';
    let stompClient = null;
    let currentRecipient = null;
    let userList = [];
    let messageHistory = {}; // 사용자별 메시지 히스토리를 저장할 객체
    let lastSender = '';

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
                    } else {
                        incrementUnreadCount(message.sender);
                    }
                }
            });

            stompClient.send("/app/chat.addUser", {}, JSON.stringify({sender: 'admin', type: 'JOIN'}));
        });
    }

    function storeMessage(message) {
        let user = message.sender === 'admin' ? message.receiver : message.sender;
        if (!messageHistory[user]) {
            messageHistory[user] = { messages: [], unreadCount: 0 };
        }
        messageHistory[user].messages.push(message);
    }

    function incrementUnreadCount(user) {
        if (messageHistory[user]) {
            messageHistory[user].unreadCount += 1;
        } else {
            messageHistory[user] = { messages: [], unreadCount: 1 };
        }
        updateUserList();
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
            let unreadCount = messageHistory[user] ? messageHistory[user].unreadCount : 0;
            let $userItem = $("<li></li>").html(user + (unreadCount > 0 ? ` <span class="badge badge-pill badge-primary">${unreadCount}</span>` : ""));
            $userItem.click(function() {
                currentRecipient = user;
                $("#currentUser").text(user);
                displayChatHistory(user);
                messageHistory[user].unreadCount = 0; // 안 읽은 메시지 수 초기화
                updateUserList();
            });
            $userList.append($userItem);
        });
    }

    function displayChatHistory(user) {
        $("#adminChatBox").empty();
        lastSender = '';
        if (messageHistory[user]) {
            messageHistory[user].messages.forEach(showMessage);
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
        let messageContainer = $("<div class='message-container'></div>");
        let messageElement = $("<div class='message'></div>").text(message.content);
        let timestamp = new Date(message.timestamp);
        let timestampElement = $("<div class='timestamp'></div>").text(formatAMPM(timestamp));

        if (message.sender === 'admin') {
            messageContainer.addClass('sent');
            messageElement.addClass('sent');
            messageContainer.append(messageElement);
            timestampElement.addClass('left');
        } else {
            messageContainer.addClass('received');
            messageElement.addClass('received');
            messageContainer.append(messageElement);
            timestampElement.addClass('right');

            if (lastSender !== message.sender) {
                let senderElement = $("<div class='message-sender'></div>").text(message.sender);
                messageContainer.prepend(senderElement);
            }
        }

        // 마지막 메시지에 타임스탬프 추가
        if (lastSender === message.sender) {
            $("#adminChatBox .message-container:last .timestamp").remove();
        }

        messageContainer.append(timestampElement);
        $("#adminChatBox").append(messageContainer);
        $("#adminChatBox").scrollTop($("#adminChatBox")[0].scrollHeight);

        lastSender = message.sender;
    }

    $("#adminSendButton").click(sendMessage);
    $("#adminChatInput").keypress(function(e) {
        if(e.which == 13) sendMessage();
    });

    connect();
});
