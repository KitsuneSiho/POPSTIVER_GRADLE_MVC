<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>1:1 채팅 관리</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="<c:url value='/resources/css/adminCss/admin.css' />">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1.5.1/dist/sockjs.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/stompjs@2.3.3/lib/stomp.min.js"></script>

    <style>
        body {
            font-family: 'Noto Sans KR', sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
        }

        .chat-container {
            width: 100%;
            max-width: 1000px;
            height: 700px;
            background: #fff;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
            border-radius: 15px;
            overflow: hidden;
            display: flex;
            margin: 20px auto;
        }

        .user-list {
            width: 300px;
            border-right: 1px solid #ccc;
            background: #fafafa;
            overflow-y: auto;
        }

        .user-list ul {
            list-style: none;
            padding: 0;
            margin: 0;
        }

        .user-list ul li {
            padding: 20px;
            border-bottom: 1px solid #ddd;
            cursor: pointer;
            display: flex;
            align-items: center;
            justify-content: space-between;
            transition: background 0.3s ease;
        }

        .user-list ul li:hover {
            background-color: #e9e9e9;
        }

        .chat-section {
            flex-grow: 1;
            display: flex;
            flex-direction: column;
            overflow: hidden;
            background: #f8f8f8;
        }

        .chat-header {
            background: #007bff;
            color: white;
            padding: 15px 20px;
            text-align: center;
            font-size: 1.5em;
            font-weight: bold;
            border-bottom: 1px solid #0056b3;
        }

        .chatBox {
            flex-grow: 1;
            padding: 20px;
            overflow-y: auto;
            display: flex;
            flex-direction: column;
            background: #f8f8f8;
        }

        .chatBox .message {
            margin-bottom: 15px;
            padding: 15px;
            border-radius: 15px;
            max-width: 70%;
            word-wrap: break-word;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            transition: background 0.3s ease;
        }

        .chatBox .message.received {
            background: #e1f5fe;
            align-self: flex-start;
        }

        .chatBox .message.sent {
            background: #c8e6c9;
            align-self: flex-end;
        }

        .chat-input-container {
            display: flex;
            padding: 20px;
            border-top: 1px solid #ddd;
            background: #fff;
            align-items: center;
        }

        #adminChatInput {
            flex: 1;
            padding: 15px;
            border: 1px solid #ddd;
            border-radius: 25px;
            margin-right: 15px;
            font-size: 1em;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            transition: border 0.3s ease;
        }

        #adminChatInput:focus {
            border-color: #007bff;
        }

        #adminSendButton {
            padding: 12px 25px;
            border: none;
            border-radius: 25px;
            background: #007bff;
            color: white;
            font-size: 1em;
            cursor: pointer;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            transition: background 0.3s ease;
        }

        #adminSendButton:hover {
            background: #0056b3;
        }

        .navbar {
            z-index: 1030; /* Ensure the navbar is above the sidebar */
        }

        .current-user-title {
            padding: 10px 20px;
            font-size: 1.2em;
            border-bottom: 1px solid #ddd;
            background: #f1f1f1;
        }

        .current-user-title span {
            font-weight: bold;
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
