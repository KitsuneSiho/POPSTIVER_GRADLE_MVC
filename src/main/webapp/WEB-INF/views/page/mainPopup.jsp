<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:set var="root" value="${pageContext.request.contextPath }" />

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${root}/resources/css/mainPopup.css">
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
        <h1><a href="main.jsp">POPSTIVER</a></h1>
    </div>

    <div class="mainTopSearch">
        <div class="mainTopSearchContainer">
            <label>
                <input type="text" placeholder="팝업스토어, 페스티벌 검색">
                <button type="submit" class="searchButton" onclick="window.location.href='searchResult.jsp'">
                    <img src="${root}/resources/asset/메인검색창검색버튼.svg" alt="">
                </button>
            </label>
        </div>
    </div>



    <div class="mainTopButton">
        <button class="myPageButton" onclick="window.location.href='myPage.jsp'">
            <img src="${root}/resources/asset/myPageButton.svg" alt="">
        </button>
        <button class="menuButton">
            <img src="${root}/resources/asset/메인메뉴버튼.svg" alt="">
    </div>


</header>
<div id="menuModal" class="modal">
    <div class="modal-content">
        <ul>
            <li><a href="login.jsp">로그인</a></li>
            <li><a href="map.jsp">근처 행사</a></li>
            <li><a href="bookmark.jsp">관심 행사</a></li>
            <li><a href="contact.jsp">게시판</a></li>
        </ul>
    </div>
</div>

<div class="mainButton">
    <button class="popupButton" onclick="window.location.href='mainPopup.jsp'">
        <img src="${root}/resources/asset/POPUP메인버튼.png" alt="">
    </button>
    <button class="festivalButton" onclick="window.location.href='mainFestival.jsp'">
        <img src="${root}/resources/asset/FESTIVAL메인버튼.png" alt="">
    </button>
</div>

<div class="mainPoster">
    <img src="${root}/resources/asset/포스터이미지/워터밤가로.webp" alt="">
</div>

<div class="popular">
    <div class="popularPosterText">
        <p class="popularText1">인기</p>
        <p class="popularText2" onclick="window.location.href='popularAdd.jsp'">더보기</p>
    </div>
    <div class="popularPoster">
        <img src="${root}/resources/asset/포스터이미지/서울.webp" alt="" onclick="window.location.href='posterInfo.jsp'">
        <img src="${root}/resources/asset/포스터이미지/대구.webp" alt="">
        <img src="${root}/resources/asset/포스터이미지/대전.webp" alt="">
        <img src="${root}/resources/asset/포스터이미지/부산.webp" alt="">
        <img src="${root}/resources/asset/포스터이미지/속초.webp" alt="">
        <img src="${root}/resources/asset/포스터이미지/속초.webp" alt="">
    </div>
</div>

<div class="open">
    <div class="openPosterText">
        <p class="openText1">오픈예정</p>
        <p class="openText2" onclick="window.location.href='openAdd.jsp'">더보기</p>
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