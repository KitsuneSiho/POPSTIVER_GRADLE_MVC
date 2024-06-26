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
