<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="root" value="${pageContext.request.contextPath }" />

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${root}/resources/css/boardCss/togetherWrite.css">
    <link rel="stylesheet" href="${root}/resources/css/boardCss/chatModal.css">
    <title>POPSTIVER</title>
    <style>
        @font-face {
            font-family: Giants;
            src: url('${root}/resources/font/Giants-Inline.ttf');
        }

        @font-face {
            font-family: KBO;
            src: url('${root}/resources/font/KBO.ttf');
        }
    </style>
</head>

<body>
<jsp:include page="/WEB-INF/views/page/fix/header.jsp" />

<div class="contactMenu">
    <a href="contact">
        <h2>공지사항</h2>
    </a>
    <a href="money">
        <h2>비즈니스 문의</h2>
    </a>
    <a href="report">
        <h2>제보하기</h2>
    </a>
    <a class="on" href="together">
        <h2>동행구하기</h2>
    </a>
    <a href="free">
        <h2>자유게시판</h2>
    </a>
</div>


<div class="business">
    <ul class="businessList">
        <li>
            <span>제목</span>
            <label class="title">
                <input type="text" placeholder="30자 이내로 입력해주세요">
            </label>
        </li>
        <li>
            <span>유형</span>
            <input type="radio" id="festival" name="type" value="지역 페스티벌">
            <label for="festival">지역 페스티벌</label>
            <input type="radio" id="popup-store" name="type" value="팝업 스토어" checked>
            <label for="popup-store">팝업 스토어</label>
        </li>
        <li>
            <span>동행기간</span>
            <label class="dateLabel">
                <input type="date" class="date">
            </label>
        </li>
        <li>
            <span>내용</span>
            <label class="infoTextarea">
                <textarea placeholder="자유롭게 작성해주세요" rows="10"></textarea>
            </label>
        </li>
        <li>
            <span>행사링크</span>
            <label class="link">
                <input type="text" placeholder="해당 행사 링크를 입력해주세요">
            </label>
        </li>
    </ul>
</div>

<div class="updateButton">
    <button type="submit">동행구하기</button>
    <button type="reset">취소</button>
</div>

<img src="${root}/resources/asset/채팅버튼.svg" id="chatButton" class="chatButton" alt="">

<div id="chatModal" class="chatModal">
    <div class="chatModalContent">
        <span class="closeChatModal">&times;</span>
        <h2>1:1 채팅</h2>
        <div class="chatBox">
            <!-- Chat messages will go here -->
        </div>
        <label for="chatInput"></label><input type="text" id="chatInput" placeholder="메시지를 입력해주세요" />
        <button id="sendChatButton">보내기</button>
    </div>
</div>

<jsp:include page="/WEB-INF/views/page/fix/footer.jsp" />

<script src="${root}/resources/js/chatModal.js"></script>
</body>

</html>
