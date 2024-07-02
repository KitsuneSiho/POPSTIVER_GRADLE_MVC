$(document).ready(function() {
    let contextPath = 'http://localhost:8080';
    let username = sessionStorage.getItem('loggedInNickname');
    let stompClient = null;
    let lastSender = '';
    let lastMessageDate = null;
    let lastMessageTime = null;

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

    function formatDate(date) {
        const options = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' };
        return date.toLocaleDateString('ko-KR', options);
    }

    function showMessage(message) {
        let timestamp = new Date(message.timestamp);
        let formattedTime = formatAMPM(timestamp);
        let formattedDate = formatDate(timestamp);

        // 날짜가 바뀌었을 때 날짜 표시
        if (!lastMessageDate || lastMessageDate !== formattedDate) {
            $(".chatBox").append(`<div class='dateDisplay'>${formattedDate}</div>`);
            lastMessageDate = formattedDate;
        }

        let messageElement = $("<div class='message'></div>");
        if (message.sender === username) {
            messageElement.addClass('userMessage');
            messageElement.html(`<span class='messageText'>${message.content}</span>`);
        } else {
            if (lastSender !== message.sender) {
                $(".chatBox").append(`<div class='sender'>${message.sender}</div>`);
            }
            messageElement.addClass('adminMessage');
            messageElement.html(`<span class='messageText'>${message.content}</span>`);
        }

        // 발신자가 바뀌거나 마지막 메시지로부터 5분이 지났을 때 시간 표시
        if (lastSender !== message.sender ||
            !lastMessageTime ||
            (timestamp - lastMessageTime) / (1000 * 60) >= 5) {
            messageElement.append(`<span class='timestamp-${message.sender === username ? 'user' : 'admin'}'>${formattedTime}</span>`);
            lastMessageTime = timestamp;
        }

        $(".chatBox").append(messageElement);
        $(".chatBox").scrollTop($(".chatBox")[0].scrollHeight);

        lastSender = message.sender;
    }

    $("#sendChatButton").click(sendMessage);
    $("#chatInput").keypress(function(e) {
        if (e.which == 13) sendMessage();
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