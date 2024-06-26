$(document).ready(function() {
    let contextPath = 'http://localhost:8080';
    let stompClient = null;
    let currentRecipient = null;
    let userList = [];

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
                } else {
                    showMessage(message);
                }
            });

            // 관리자로 등록
            stompClient.send("/app/chat.addUser", {}, JSON.stringify({sender: 'admin', type: 'JOIN'}));
        });
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
                $("#adminChatBox").empty();
            });
            $userList.append($userItem);
        });
        console.log("Updated user list:", userList);
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


    function showMessage(message) {
        let messageElement = $("<div class='message'></div>");
        messageElement.text(message.sender + ": " + message.content);
        $("#adminChatBox").append(messageElement);
        $("#adminChatBox").scrollTop($("#adminChatBox")[0].scrollHeight);
    }

    $("#adminSendButton").click(sendMessage);
    $("#adminChatInput").keypress(function(e) {
        if(e.which == 13) sendMessage();
    });

    connect();
});