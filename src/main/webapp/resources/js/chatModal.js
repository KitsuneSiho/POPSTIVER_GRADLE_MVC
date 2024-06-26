$(document).ready(function() {
    let contextPath = 'http://localhost:8080';
    let username = sessionStorage.getItem('loggedInNickname');
    let stompClient = null;

    function connect() {
        let socket = new SockJS(contextPath + '/chat-websocket');
        stompClient = Stomp.over(socket);

        stompClient.connect({}, function(frame) {
            console.log('Connected: ' + frame);

            stompClient.send("/app/chat.addUser", {}, JSON.stringify({sender: username, type: 'JOIN'}));

            stompClient.subscribe('/topic/messages', function(messageOutput) {
                let message = JSON.parse(messageOutput.body);
                console.log("Received message:", message);
                if (message.receiver === username || (message.sender === username && message.receiver === 'admin')) {
                    showMessage(message);
                }
            });
        });
    }

    function sendMessage() {
        let messageContent = $("#chatInput").val().trim();
        if (messageContent && stompClient) {
            let chatMessage = {
                sender: username,
                receiver: 'admin',
                content: messageContent,
                type: 'CHAT'
            };

            stompClient.send("/app/chat.message", {}, JSON.stringify(chatMessage));
            $("#chatInput").val("");
            console.log("User sent message:", chatMessage);
        }
    }

    function showMessage(message) {
        let messageElement = $("<div class='message'></div>");
        messageElement.text(message.sender + ": " + message.content);
        $(".chatBox").append(messageElement);
        $(".chatBox").scrollTop($(".chatBox")[0].scrollHeight);
    }

    $("#sendChatButton").click(sendMessage);
    $("#chatInput").keypress(function(e) {
        if(e.which == 13) sendMessage();
    });

    $("#chatButton").click(function() {
        $("#chatModal").show();
        if (!stompClient) {
            connect();
        }
    });

    $(".closeChatModal").click(function() {
        $("#chatModal").hide();
    });

    connect();
});