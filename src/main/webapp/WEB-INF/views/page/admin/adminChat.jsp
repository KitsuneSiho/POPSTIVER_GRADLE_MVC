<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>관리자 채팅</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1.5.1/dist/sockjs.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/stompjs@2.3.3/lib/stomp.min.js"></script>
    <style>
        .chatBox {
            width: 100%;
            height: 400px;
            border: 1px solid #ddd;
            overflow-y: scroll;
            padding: 10px;
            box-sizing: border-box;
            background: #f9f9f9;
        }
        .chatBox div {
            margin-bottom: 10px;
        }
    </style>
    <script>
        // JSP 스크립트를 사용하여 root 변수를 JavaScript 변수로 설정
        var root = "${pageContext.request.contextPath}";
    </script>
    <!-- chatAdmin.js는 마지막에 추가합니다. -->
</head>
<body>
<h1>관리자 채팅 화면</h1>
<div class="chatBox"></div>
<script src="${root}/resources/js/chatAdmin.js"></script>
</body>
</html>
