<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:set var="root" value="${pageContext.request.contextPath }" />

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${root}/resources/css/mainCss/main.css">
    <link rel="stylesheet" href="${root}/resources/css/fixCss/header.css">
    <link rel="stylesheet" href="${root}/resources/css/fixCss/footer.css">
    <link rel="stylesheet" href="${root}/resources/css/fixCss/menuModal.css">
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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="${root}/resources/js/loginName.js"></script>
</head>

<body>
<header class="mainTop">
    <div class="mainTopLogo">
        <h1><a href="../main">POPSTIVER</a></h1>
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
        <sec:authorize access="!isAuthenticated()">
            <button class="loginButton" onclick="window.location.href='login'">
                로그인
            </button>
        </sec:authorize>


        <sec:authorize access="isAuthenticated()">
            <button class="logoutButton" onclick="window.location.href='logout'">
                로그아웃
            </button>
        </sec:authorize>

        <button class="menuButton">
            <img src="${root}/resources/asset/메인메뉴버튼.svg" alt="">
        </button>
    </div>

</header>
<div id="menuModal" class="modal">
    <div class="modal-content">
        <ul>
            <li><a href="${root}/myPage">마이페이지</a></li>
            <li><a href="${root}/map">근처 행사</a></li>
            <li><a href="${root}/bookmark">관심 행사</a></li>
            <li><a href="${root}/calendar">행사 일정</a></li>
            <li><a href="${root}/contact">게시판</a></li>
        </ul>
    </div>
</div>

<div class="mainButton">
    <button class="popupButton" onclick="window.location.href='mainPopup'">
        <img src="${root}/resources/asset/POPUP메인버튼.png" alt="">
    </button>
    <button class="festivalButton" onclick="window.location.href='mainFestival'">
        <img src="${root}/resources/asset/FESTIVAL메인버튼.png" alt="">
    </button>
</div>

<div class="mainPoster">
    <video width="1000px" height="400px" controls autoplay loop muted>
        <source src="${root}/resources/asset/포스터이미지/워터밤영상.mp4" type="video/mp4">
    </video>
</div>

<div class="popular">
    <div class="popularPosterText">
        <p class="popularText1">인기</p>
        <p class="popularText2" onclick="window.location.href='popularAdd'">더보기</p>
    </div>
    <div class="popularPoster">
        <img src="${root}/resources/asset/포스터이미지/서울.webp" alt="" onclick="window.location.href='posterInfo'">
        <img src="${root}/resources/asset/포스터이미지/대구.webp" alt="">
        <img src="${root}/resources/asset/포스터이미지/대전.webp" alt="">
        <img src="${root}/resources/asset/포스터이미지/부산.webp" alt="">
        <img src="${root}/resources/asset/포스터이미지/속초.webp" alt="">
        <img src="${root}/resources/asset/포스터이미지/속초.webp" alt="">
    </div>
</div>

<div class="open">
    <div class="openPosterText">
        <p class="openText1">오픈 예정</p>
        <p class="openText2" onclick="window.location.href='openAdd'">더보기</p>
    </div>
    <div class="openPoster">
        <img src="${root}/resources/asset/포스터이미지/흠뻑쇼.gif" alt="">
        <img src="${root}/resources/asset/포스터이미지/흠뻑쇼3.gif" alt="">
        <img src="${root}/resources/asset/포스터이미지/흠뻑쇼5.gif" alt="">
        <img src="${root}/resources/asset/포스터이미지/흠뻑쇼7.gif" alt="">
        <img src="${root}/resources/asset/포스터이미지/흠뻑쇼9.gif" alt="">
        <img src="${root}/resources/asset/포스터이미지/흠뻑쇼9.gif" alt="">
    </div>
</div>
<footer>
    ㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇ<br>
    ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ
</footer>

<script src="${root}/resources/js/menuModal.js"></script>
</body>

</html>