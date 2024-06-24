<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
 html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>관리자 채팅</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1.5.1/dist/sockjs.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/stompjs@2.3.3/lib/stomp.min.js"></script>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .chat-container {
            width: 100%;
            max-width: 600px;
            background: #fff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
            overflow: hidden;
        }

        .chat-header {
            background: #007bff;
            color: white;
            padding: 10px 20px;
            text-align: center;
            font-size: 1.2em;
            font-weight: bold;
        }

        .chatBox {
            width: 100%;
            height: 300px;
            border-top: 1px solid #ddd;
            overflow-y: scroll;
            padding: 10px;
            box-sizing: border-box;
            background: #f9f9f9;
        }

        .chatBox div {
            margin-bottom: 10px;
            padding: 5px 10px;
            border-radius: 5px;
            background: #e1f5fe;
            display: inline-block;
            max-width: 80%;
        }

        .chat-input-container {
            display: flex;
            padding: 10px;
            border-top: 1px solid #ddd;
            background: #f4f4f9;
        }

        #adminChatInput {
            flex: 1;
            padding: 10px;
            border: none;
            border-radius: 5px;
            margin-right: 10px;
            font-size: 1em;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        #adminSendButton {
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            background: #007bff;
            color: white;
            font-size: 1em;
            cursor: pointer;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        #adminSendButton:hover {
            background: #0056b3;
        }
    </style>
</head>
<body>
<div class="chat-container">
    <div class="chat-header">관리자 채팅 화면</div>
    <div class="chatBox"></div>
    <div class="chat-input-container">
        <input type="text" id="adminChatInput" placeholder="메시지를 입력하세요..." />
        <button id="adminSendButton"><i class="fas fa-paper-plane"></i></button>
    </div>
</div>
<script src="${root}/resources/js/chatAdmin.js"></script>
</body>
</html>
