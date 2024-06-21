document.addEventListener('DOMContentLoaded', function() {
    const chatButton = document.getElementById('chatButton');
    const chatModal = document.getElementById('chatModal');
    const closeChatModal = document.querySelector('.closeChatModal');
    const sendChatButton = document.getElementById('sendChatButton');
    const chatInput = document.getElementById('chatInput');
    const chatBox = document.querySelector('.chatBox');
    const chatModalContent = document.querySelector('.chatModalContent');

    // 채팅 모달 열기
    chatButton.addEventListener('click', function() {
        chatModal.style.display = 'block';
    });

    // 채팅 모달 닫기
    closeChatModal.addEventListener('click', function() {
        chatModal.style.display = 'none';
    });

    // 채팅 메시지 보내기
    sendChatButton.addEventListener('click', function() {
        const message = "나 : " + chatInput.value;
        if (chatInput.value.trim()) {
            const messageElement = document.createElement('div');
            messageElement.textContent = message;
            chatBox.appendChild(messageElement);
            chatInput.value = '';
            chatBox.scrollTop = chatBox.scrollHeight;
        }
    });

    // 모달 외부 클릭 시 모달 닫기
    window.addEventListener('click', function(event) {
        if (event.target === chatModal) {
            chatModal.style.display = 'none';
        }
    });

    // 모달 내부 클릭 시 이벤트 버블링 방지
    chatModalContent.addEventListener('click', function(event) {
        event.stopPropagation();
    });
});