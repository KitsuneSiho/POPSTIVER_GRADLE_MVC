<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:set var="root" value="${pageContext.request.contextPath }" />

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${root}/resources/css/fixCss/header.css">
    <link rel="stylesheet" href="${root}/resources/css/fixCss/menuModal.css">
    <title>POPSTIVER</title>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script> const root = "${root}"; </script>
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
<script src="${root}/resources/js/menuModal.js"></script>
</body>