<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:set var="root" value="${pageContext.request.contextPath }" />

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${root}/resources/css/reportWrite.css">
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
<header class="mainTop">
    <div class="mainTopLogo">
        <h1><a href="main">POPSTIVER</a></h1>
    </div>

    <div class="mainTopSearch">
        <div class="mainTopSearchContainer">
            <label>
                <input type="text" placeholder="팝업스토어, 페스티벌 검색">
            </label>
            <button type="submit" class="searchButton" onclick="window.location.href='searchResult'">
                <img src="${root}/resources/asset/메인검색창검색버튼.svg" alt="">
            </button>
        </div>
    </div>



    <div class="mainTopButton">
        <button class="myPageButton" onclick="window.location.href='myPage'">
            <img src="${root}/resources/asset/myPageButton.svg" alt="">
        </button>
        <button class="menuButton">
            <img src="${root}/resources/asset/메인메뉴버튼.svg" alt="">
        </button>
    </div>


</header>
<div id="menuModal" class="modal">
    <div class="modal-content">
        <ul>
            <li><a href="login">로그인</a></li>
            <li><a href="map">근처 행사</a></li>
            <li><a href="bookmark">관심 행사</a></li>
            <li><a href="calender">행사 일정</a></li>
            <li><a href="contact">게시판</a></li>
        </ul>
    </div>
</div>

<div class="contactMenu">
    <a href="contact">
        <h2>공지사항</h2>
    </a>
    <a href="money">
        <h2>비즈니스 문의</h2>
    </a>
    <a class="on" href="report">
        <h2>제보하기</h2>
    </a>
    <a href="together">
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
            <span>행사 내용</span>
            <label class="infoTextarea">
                <textarea placeholder="어떤 행사인지 쉽게 알 수 있도록 상세히 적어주세요!" rows="10"></textarea>
            </label>
        </li>
        <li>
            <span>주소</span>
            <label class="addressLabel">
                <input type="text">
            </label>
            <button class="searchAddress" type="button">주소 검색</button>
        </li>
        <li>
            <span>행사기간</span>
            <label class="dateLabel">
                <input type="date" class="date">
            </label>
            <p>부터</p>
            <label class="dateLabel">
                <input type="date" class="date">
            </label>
            <p>까지</p>
        </li>
        <li>
            <span>사진</span>
            <input type="image" class="image" alt="">
        </li>
    </ul>
</div>

<div class="updateButton">
    <button type="submit">제보하기</button>
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
<footer>
    ㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇ<br>
    ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ
</footer>

<script src="${root}/resources/js/menuModal.js"></script>
<script src="${root}/resources/js/chatModal.js"></script>
</body>

</html>