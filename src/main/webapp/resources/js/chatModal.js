// 채팅 버튼 클릭 시 모달 열기
$("#chatButton").click(function() {
    $("#chatModal").show();
});

// 채팅 모달 닫기
$(".closeChatModal").click(function() {
    $("#chatModal").hide();
});

// WebSocket 연결 설정
var socket = new SockJS(contextPath + '/chat-websocket'); // JSP에서 전달된 contextPath 사용
var stompClient = Stomp.over(socket);

stompClient.connect({}, function(frame) {
    console.log('Connected: ' + frame);

    // 메시지 구독
    stompClient.subscribe('/queue/messages', function(messageOutput) {
        showMessage(JSON.parse(messageOutput.body));
    });
});

// 메시지 전송
$("#sendChatButton").click(function() {
    var messageContent = $("#chatInput").val().trim();
    if (messageContent && stompClient) {
        var chatMessage = {
            sender: loggedInNickname, // 여기에 로그인된 사용자의 닉네임을 넣어야 합니다.
            content: messageContent,
            timestamp: new Date().toLocaleTimeString()
        };

        // 서버로 메시지 전송
        stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));

        // 메시지를 화면에 즉시 표시
        showMessage(chatMessage);

        // 입력 창 초기화
        $("#chatInput").val("");
    }
});

// 메시지를 화면에 표시하는 함수
function showMessage(message) {
    var messageElement = $("<div class='message'></div>");
    messageElement.text(message.sender + ": " + message.content);
    $(".chatBox").append(messageElement);
    $(".chatBox").scrollTop($(".chatBox")[0].scrollHeight); // 새로운 메시지가 들어올 때 자동 스크롤
}
