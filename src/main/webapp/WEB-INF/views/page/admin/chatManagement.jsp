<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>1:1 채팅 관리</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="<c:url value='/resources/css/adminCss/admin.css' />">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1.5.1/dist/sockjs.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/stompjs@2.3.3/lib/stomp.min.js"></script>

    <style>
        :root {
            --primary-color: #4a90e2;
            --secondary-color: #f0f4f8;
            --text-color: #333;
            --light-text-color: #777;
            --border-color: #e1e4e8;
            --success-color: #28a745;
            --hover-color: #3a7bd5;
        }

        body {
            font-family: 'Noto Sans KR', sans-serif;
            background-color: var(--secondary-color);
            color: var(--text-color);
            margin: 0;
            padding: 0;
        }

        .chat-container {
            width: 100%;
            max-width: 1200px;
            height: 80vh;
            background: #fff;
            box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1);
            border-radius: 20px;
            overflow: hidden;
            display: flex;
            margin: 40px auto;
        }

        .user-list {
            width: 300px;
            border-right: 1px solid var(--border-color);
            background: #fff;
            overflow-y: auto;
        }

        .user-list ul {
            list-style: none;
            padding: 0;
            margin: 0;
        }

        .user-list ul li {
            padding: 15px 20px;
            border-bottom: 1px solid var(--border-color);
            cursor: pointer;
            display: flex;
            align-items: center;
            justify-content: space-between;
            transition: all 0.3s ease;
        }

        .user-list ul li:hover {
            background-color: var(--secondary-color);
            transform: translateY(-2px);
        }

        .user-list ul li .badge {
            background-color: var(--primary-color);
            color: #fff;
            padding: 5px 10px;
            border-radius: 50px;
            font-size: 0.8em;
        }

        .chat-section {
            flex-grow: 1;
            display: flex;
            flex-direction: column;
            background: #fff;
        }

        .chat-header {
            background: var(--primary-color);
            color: white;
            padding: 20px;
            text-align: center;
            font-size: 1.2em;
            font-weight: bold;
        }

        .chatBox {
            flex-grow: 1;
            padding: 20px;
            overflow-y: auto;
            display: flex;
            flex-direction: column;
        }

        .message-container {
            display: flex;
            flex-direction: column;
            margin-bottom: 15px;
            animation: fadeIn 0.5s ease;
        }

        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(20px); }
            to { opacity: 1; transform: translateY(0); }
        }

        .message-container.sent {
            align-items: flex-end;
        }

        .message-container.received {
            align-items: flex-start;
        }

        .message-sender {
            font-weight: bold;
            margin-bottom: 5px;
            color: var(--light-text-color);
        }

        .message {
            padding: 12px 18px;
            border-radius: 20px;
            max-width: 70%;
            word-wrap: break-word;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            position: relative;
        }

        .message .timestamp {
            font-size: 0.7em;
            color: var(--light-text-color);
            margin-top: 5px;
        }

        .message.received {
            background: var(--secondary-color);
            border-bottom-left-radius: 0;
        }

        .message.sent {
            background: var(--primary-color);
            color: white;
            border-bottom-right-radius: 0;
        }

        .chat-input-container {
            display: flex;
            padding: 20px;
            background: #fff;
            border-top: 1px solid var(--border-color);
        }

        #adminChatInput {
            flex: 1;
            padding: 15px;
            border: 1px solid var(--border-color);
            border-radius: 30px;
            margin-right: 15px;
            font-size: 1em;
            transition: all 0.3s ease;
        }

        #adminChatInput:focus {
            border-color: var(--primary-color);
            box-shadow: 0 0 0 2px rgba(74, 144, 226, 0.2);
        }

        #adminSendButton {
            padding: 12px 25px;
            border: none;
            border-radius: 30px;
            background: var(--primary-color);
            color: white;
            font-size: 1em;
            cursor: pointer;
            transition: all 0.3s ease;
        }

        #adminSendButton:hover {
            background: var(--hover-color);
            transform: translateY(-2px);
        }

        .current-user-title {
            padding: 15px 20px;
            font-size: 1.1em;
            border-bottom: 1px solid var(--border-color);
            background: var(--secondary-color);
        }

        .current-user-title span {
            font-weight: bold;
            color: var(--primary-color);
        }

        @media (max-width: 768px) {
            .chat-container {
                flex-direction: column;
                height: 90vh;
            }

            .user-list {
                width: 100%;
                max-height: 30%;
            }

            .chat-section {
                height: 70%;
            }
        }
    </style>
</head>
<body>
<header>
    <jsp:include page="/WEB-INF/views/page/admin/layout/header.jsp"/>
</header>
<div class="container-fluid">
    <div class="row">
        <!-- 사이드바 -->
        <nav class="col-md-2 d-none d-md-block bg-light sidebar">
            <jsp:include page="/WEB-INF/views/page/admin/layout/sidebar.jsp"/>
        </nav>

        <!-- 메인 콘텐츠 -->
        <main role="main" class="col-md-10 ml-sm-auto col-lg-10 px-4 main-content">
            <h2>1:1 채팅 관리</h2>
            <!-- 1:1 채팅 관리 내용 -->
            <div class="chat-container">
                <div class="user-list">
                    <ul id="userList"></ul>
                </div>
                <div class="chat-section">
                    <div class="chat-header">관리자 채팅 화면</div>
                    <div class="current-user-title">채팅 대상: <span id="currentUser"></span></div>
                    <div class="chatBox" id="adminChatBox"></div>
                    <div class="chat-input-container">
                        <input type="text" id="adminChatInput" placeholder="메시지를 입력하세요..." />
                        <button id="adminSendButton"><i class="fas fa-paper-plane"></i></button>
                    </div>
                </div>
            </div>
        </main>
    </div>
</div>
<!-- 푸터 -->
<footer class="mt-auto">
    <jsp:include page="/WEB-INF/views/page/admin/layout/footer.jsp"/>
</footer>
<script>
    let contextPath = '${pageContext.request.contextPath}';
</script>
<script src="${pageContext.request.contextPath}/resources/js/chatAdmin.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
