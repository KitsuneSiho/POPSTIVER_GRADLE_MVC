// 채팅 버튼 클릭 시 모달 열기
$("#chatButton").click(function() {
    $("#chatModal").show();
});

// 채팅 모달 닫기
$(".closeChatModal").click(function() {
    $("#chatModal").hide();
});

// WebSocket 연결 설정
var contextPath = 'http://localhost:8080';
var socket = new SockJS(contextPath + '/chat-websocket');
var stompClient = Stomp.over(socket);

var username = '카카오 사용자'; // 실제 구현에서는 로그인한 사용자의 이름으로 설정해야 합니다.

stompClient.connect({}, function(frame) {
    console.log('Connected: ' + frame);

    // 연결 시 사용자 이름 설정
    stompClient.send("/app/chat.addUser",
        {},
        JSON.stringify({sender: username, type: 'JOIN'})
    );

    // 공개 메시지 구독
    stompClient.subscribe('/topic/public', function(messageOutput) {
        showMessage(JSON.parse(messageOutput.body));
    });

    // 개인 메시지 구독
    stompClient.subscribe('/user/queue/private', function(messageOutput) {
        showMessage(JSON.parse(messageOutput.body));
    });
});

// 메시지 전송
$("#sendChatButton").click(function() {
    sendMessage();
});

function sendMessage() {
    var messageContent = $("#chatInput").val().trim();
    if (messageContent && stompClient) {
        var chatMessage = {
            sender: username,
            content: messageContent,
            type: 'CHAT'
        };

        // 공개 메시지 전송
        stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));

        // 관리자에게 개인 메시지 전송
        chatMessage.receiver = 'admin'; // 관리자 사용자 이름
        stompClient.send("/app/chat.private", {}, JSON.stringify(chatMessage));

        $("#chatInput").val("");
    }
}

function showMessage(message) {
    var messageElement = $("<div class='message'></div>");
    messageElement.text(message.sender + ": " + message.content);
    $(".chatBox").append(messageElement);
    $(".chatBox").scrollTop($(".chatBox")[0].scrollHeight);
}