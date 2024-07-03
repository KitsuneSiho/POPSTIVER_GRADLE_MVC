$(document).ready(function() {
    let contextPath = 'http://localhost:8080';
    let stompClient = null;
    let currentRecipient = sessionStorage.getItem('currentRecipient');
    let userList = JSON.parse(sessionStorage.getItem('userList')) || [];
    let messageHistory = JSON.parse(sessionStorage.getItem('messageHistory')) || {};
    let lastSender = '';
    let lastMessageDate = null;

    function connect() {
        let socket = new SockJS(contextPath + '/chat-websocket');
        stompClient = Stomp.over(socket);

        stompClient.connect({}, function(frame) {
            console.log('Connected: ' + frame);

            // 웹소켓 연결 시 현재 날짜 표시
            displayCurrentDate();

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

            // 연결 후 즉시 메시지 로드
            loadMessages();
        });
    }

    function displayCurrentDate() {
        let currentDate = new Date();
        let formattedDate = formatDate(currentDate);
        $("#adminChatBox").append(`<div class='date-divider'><span>${formattedDate}</span></div>`);
        lastMessageDate = formattedDate;
    }

    function formatDate(date) {
        const options = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' };
        return date.toLocaleDateString('ko-KR', options);
    }

    function storeMessage(message) {
        let user = message.sender === 'admin' ? message.receiver : message.sender;
        if (!messageHistory[user]) {
            messageHistory[user] = { messages: [], unreadCount: 0 };
        }
        messageHistory[user].messages.push(message);
        sessionStorage.setItem('messageHistory', JSON.stringify(messageHistory));
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
            let $userItem = $("<li></li>").html(
                `<span>${user}</span>` +
                (unreadCount > 0 ? `<span class="badge">${unreadCount}</span>` : "")
            );
            $userItem.click(function() {
                $userList.find('li').removeClass('active');
                $(this).addClass('active');
                currentRecipient = user;
                sessionStorage.setItem('currentRecipient', user);
                $("#currentUser").text(user);
                displayChatHistory(user);
                messageHistory[user].unreadCount = 0;
                updateUserList();
            });
            $userList.append($userItem);
        });
        sessionStorage.setItem('userList', JSON.stringify(userList));
    }

    function displayChatHistory(user) {
        $("#adminChatBox").empty();
        lastSender = '';
        lastMessageDate = null;  // 채팅 기록을 표시할 때 날짜 초기화
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
                type: 'CHAT',
                timestamp: new Date().toISOString()
            };

            stompClient.send("/app/chat.message", {}, JSON.stringify(chatMessage));
            $.ajax({
                type: "POST",
                url: contextPath + "/api/chat/message",
                contentType: "application/json",
                data: JSON.stringify(chatMessage),
                success: function(response) {
                    console.log("Message saved:", response);
                },
                error: function(error) {
                    console.error("Error saving message:", error);
                }
            });

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
        hours = hours ? hours : 12;
        minutes = minutes < 10 ? '0' + minutes : minutes;
        let strTime = ampm + ' ' + hours + ':' + minutes;
        return strTime;
    }

    function showMessage(message) {
        let timestamp = new Date(message.timestamp);
        let formattedDate = formatDate(timestamp);

        // 날짜가 바뀌었을 때 새로운 날짜 표시
        if (formattedDate !== lastMessageDate) {
            $("#adminChatBox").append(`<div class='date-divider'><span>${formattedDate}</span></div>`);
            lastMessageDate = formattedDate;
        }

        let messageContainer = $("<div class='message-container'></div>");
        let messageElement = $("<div class='message'></div>").text(message.content);
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

        if (lastSender === message.sender) {
            $("#adminChatBox .message-container:last .timestamp").remove();
        }

        messageContainer.append(timestampElement);
        $("#adminChatBox").append(messageContainer);

        $("#adminChatBox").scrollTop($("#adminChatBox")[0].scrollHeight);

        lastSender = message.sender;
    }

    function loadMessages() {
        $.ajax({
            type: "GET",
            url: contextPath + "/api/chat/messages/admin",
            success: function(messages) {
                messages.forEach(function(message) {
                    storeMessage(message);
                    if (message.sender !== 'admin') {
                        addUserToList(message.sender);
                    }
                });
                updateUserList();
                if (currentRecipient) {
                    displayChatHistory(currentRecipient);
                }
            },
            error: function(error) {
                console.error("Error loading messages:", error);
            }
        });
    }

    $("#adminSendButton").click(sendMessage);
    $("#adminChatInput").keypress(function(e) {
        if(e.which == 13) sendMessage();
    });

    // 페이지 로드 시 즉시 연결 및 메시지 로드
    connect();
});