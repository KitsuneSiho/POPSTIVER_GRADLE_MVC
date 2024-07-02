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

        @font-face {
            font-family: Pre;
            src: url('${root}/resources/font/Pre.ttf');
        }
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="${root}/resources/js/togetherEdit.js"></script>
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
    <form method="post" onsubmit="submitForm(event)">
        <ul class="businessList">
            <li>
                <span>제목</span>
                <label class="title">
                    <input type="text" name="comp_title" placeholder="${current_together.comp_title}" value="${current_together.comp_title}">
                </label>
            </li>
            <li>
                <span>유형</span>
                <input type="radio" id="culture" name="event_type" value="1" checked>
                <label for="culture">문화·전통체험</label>
                <input type="radio" id="festival" name="event_type" value="2">
                <label for="festival">지역 페스티벌</label>
                <input type="radio" id="popup-store" name="event_type" value="3">
                <label for="popup-store">팝업 스토어</label>

            </li>
            <li>
                <span>동행기간</span>
                <label class="dateLabel">
                    <input type="date" class="date" name="comp_date" value="${current_together.comp_date}">
                </label>
            </li>
            <li>
                <span>내용</span>
                <label class="infoTextarea">
                    <textarea name="comp_content" placeholder="${current_together.comp_content}" rows="10" style="resize: none" >${current_together.comp_content}</textarea>
                </label>
            </li>
            <li>
                <span>행사링크</span>
                <label class="link">
                    <input type="text" name="comp_link" placeholder="${current_together.comp_link}" value="${current_together.comp_link}">
                </label>
                <input type="hidden" id="user_id" name="user_id" value="">
                <input type="hidden" id="user_name" name="user_name" value="">
                <input type="hidden" id="comp_no" name="comp_no" value="${current_together.comp_no}">
            </li>
        </ul>
        <div class="updateButton">
            <button type="submit">수정하기</button>
            <button type="reset" onclick="window.location.href='together'">취소</button>
        </div>
    </form>
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
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1.5.1/dist/sockjs.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/stompjs@2.3.3/lib/stomp.min.js"></script>
</body>
</html>