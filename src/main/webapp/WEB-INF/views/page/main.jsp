<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:set var="root" value="${pageContext.request.contextPath }" />

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
    <link rel="stylesheet" href="${root}/resources/css/main.css">
</head>

<body>
<header class="mainTop">
    <div class="mainTopLogo">
        <h1><a href="${root}">POPSTIVER</a></h1>
    </div>

    <div class="mainTopSearch">
        <div class="mainTopSearchContainer">
            <label>
                <input type="text" placeholder="팝업스토어, 페스티벌 검색">
            </label>
            <button type="submit" class="searchButton" onclick="window.location.href='${root}/searchResult'">
                <img src="${root}/resources/asset/main_search_button.svg" alt="">
            </button>
        </div>
    </div>

    <div class="mainTopButton">
        <button class="myPageButton" onclick="window.location.href='${root}/myPage'">
            <img src="${root}/resources/asset/P20210418.JPG" alt="">
        </button>
        <button class="menuButton">
            <img src="${root}/resources/asset/main_menu_button.svg" alt="">
        </button>
    </div>
</header>

<div id="menuModal" class="modal">
    <div class="modal-content">
        <ul>
            <li><a href="${root}/login">로그인</a></li>
            <li><a href="${root}/map">근처 행사</a></li>
            <li><a href="">관심 행사</a></li>
            <li><a href="${root}/contact">게시판</a></li>
        </ul>
    </div>
</div>

<div class="mainButton">
    <button class="popupButton" onclick="window.location.href='${root}/mainPopup'">
        <img src="${root}/resources/asset/poster_image/P20210425.JPG" alt="">
    </button>
    <button class="festivalButton" onclick="window.location.href='${root}/mainFestival'">
        <img src="${root}/resources/asset/poster_image/P20210430.JPG" alt="">
    </button>
</div>

<div class="mainPoster">
    <video width="1000px" height="400px" controls autoplay loop muted>
        <source src="${root}/resources/asset/poster_image/waterbam-movie.mp4" type="video/mp4">
    </video>
</div>

<div class="popular">
    <div class="popularPosterText">
        <p class="popularText1">인기</p>
        <p class="popularText2" onclick="window.location.href='${root}/popularAdd.jsp'">더보기</p>
    </div>
    <div class="popularPoster">
        <img src="${root}/resources/asset/poster_image/seoul.webp" alt="">
        <img src="${root}/resources/asset/poster_image/daegu.webp" alt="">
        <img src="${root}/resources/asset/poster_image/daejeon.webp" alt="">
        <img src="${root}/resources/asset/poster_image/busan.webp" alt="">
        <img src="${root}/resources/asset/poster_image/sokcho.webp" alt="">
    </div>
</div>

<div class="open">
    <div class="openPosterText">
        <p class="openText1">오픈예정</p>
        <p class="openText2" onclick="window.location.href='${root}/openAdd.jsp'">더보기</p>
    </div>
    <div class="openPoster">
        <img src="${root}/resources/asset/poster_image/wetshow1.gif" alt="">
        <img src="${root}/resources/asset/poster_image/wetshow3.gif" alt="">
        <img src="${root}/resources/asset/poster_image/wetshow5.gif" alt="">
        <img src="${root}/resources/asset/poster_image/wetshow7.gif" alt="">
        <img src="${root}/resources/asset/poster_image/wetshow9.gif" alt="">
    </div>
</div>

<footer>
    ㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇ<br>
    ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ
</footer>

<script src="${root}/resources/js/menuModal.js"></script>
</body>

</html>
