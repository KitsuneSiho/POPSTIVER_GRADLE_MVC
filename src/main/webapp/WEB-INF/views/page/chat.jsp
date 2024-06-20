<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Real-Time Chat</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.5.0/sockjs.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <style>
        /* 채팅 UI 스타일 정의 */
        #chat-container {
            width: 400px;
            height: 500px;
            border: 1px solid #ccc;
            overflow: auto;
            padding: 10px;
        }
        #messageArea {
            list-style-type: none;
            padding: 0;
            margin: 0;
        }
        #messageArea li {
            margin: 5px 0;
            padding: 5px;
            border-bottom: 1px solid #ddd;
        }
        #messageInput {
            width: 80%;
            padding: 10px;
        }
        #sendButton {
            padding: 10px;
        }
        .timestamp {
            font-size: 0.8em;
            color: gray;
        }
    </style>
</head>
<body>
<div id="chat-container">
    <ul id="messageArea">
        <!-- 채팅 메시지가 표시될 영역 -->
    </ul>
    <input type="text" id="messageInput" placeholder="Type a message..." />
    <button id="sendButton">Send</button>
</div>

<script>
    var stompClient = null;

    function connect() {
        var socket = new SockJS('/chat');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/public', function (message) {
                showMessage(JSON.parse(message.body));
            });
        });
    }

    function sendMessage() {
        var messageContent = $('#messageInput').val().trim();
        if (messageContent && stompClient) {
            var chatMessage = {
                content: messageContent,
                sender: 'User',  // 실제 사용자 이름을 설정해야 합니다.
                type: 'CHAT'
            };
            stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));
            $('#messageInput').val('');
        }
    }

    function showMessage(message) {
        var messageElement = $('<li>');
        var timestamp = new Date().toLocaleTimeString();
        messageElement.text(message.sender + ': ' + message.content);
        messageElement.append('<span class="timestamp"> (' + timestamp + ')</span>');
        $('#messageArea').append(messageElement);
    }

    $(document).ready(function() {
        connect();
        $('#sendButton').click(function() {
            sendMessage();
        });
    });
</script>
</body>
</html>
