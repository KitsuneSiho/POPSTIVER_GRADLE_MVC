<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/myPage.css">
    <title>POPSTIVER</title>
    <style>
        @font-face {
            font-family: Giants;
            src: url('${pageContext.request.contextPath}/resources/font/Giants-Inline.ttf');
        }

        @font-face {
            font-family: KBO;
            src: url('${pageContext.request.contextPath}/resources/font/KBO.ttf');
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
            <input type="text" placeholder="팝업스토어, 페스티벌 검색">
            <button type="submit" class="searchButton" onclick="window.location.href='searchResult'">
                <img src="${pageContext.request.contextPath}/resources/asset/main_search_button.svg">
            </button>
        </div>
    </div>

    <div class="mainTopButton">
        <button class="myPageButton" onclick="window.location.href='myPage.html'">
            <img src="${pageContext.request.contextPath}/resources/asset/P20210418.JPG">
        </button>
        <button class="menuButton">
            <img src="${pageContext.request.contextPath}/resources/asset/main_menu_button.svg">
    </div>
</header>
<div id="menuModal" class="modal">
    <div class="modal-content">
        <ul>
            <li><a href="login">로그인</a></li>
            <li><a href="map">근처 행사</a></li>
            <li><a href="">관심 행사</a></li>
            <li><a href="contact">게시판</a></li>
        </ul>
    </div>
</div>

<div class="myPage">
    <a class="on" href="myPage">
        <h2>내 정보</h2>
    </a>
    <a href="">
        <h2>관심 행사</h2>
    </a>
    <a href="">
        <h2>회원 탈퇴</h2>
    </a>
</div>

<form action="${pageContext.request.contextPath}/saveUser" method="post">
    <div class="userInfo">
        <ul class="info">
            <li>
                <span>회원 유형</span><br>
                <input type="radio" id="host" name="user_type" value="1">
                <label for="host">주최자</label>
                <input type="radio" id="user" name="user_type" value="2">
                <label for="user">사용자</label>
            </li>
            <li>
                <span>아이디</span><br>
                <input type="text" name="user_id" value="${userId}">
            </li>
            <li>
                <span>이름</span><br>
                <input type="text" name="user_name" value="${userName}">
            </li>
            <li>
                <span>이메일</span><br>
                <input type="text" name="user_email" value="${userEmail}">
            </li>
            <li>
                <span>생일</span><br>
                <input type="text" name="user_birth" value="${userBirthyear}${userBirthday}">
            </li>
            <li>
                <span>성별</span><br>
                <input type="radio" id="male" name="user_gender" value="male" ${userGender == 'male' ? 'checked' : ''}>
                <label for="male">남</label>
                <input type="radio" id="female" name="user_gender" value="female" ${userGender == 'female' ? 'checked' : ''}>
                <label for="female">여</label>
            </li>
        </ul>
    </div>

    <div class="tag">
        <span>관심 태그</span>
        <div class="tagButton">
            <button class="tag1">태그1</button>
            <button class="tag2">태그2</button>
            <button class="tag3">태그3</button>
            <button class="tag4">태그4</button>
            <button class="tag5">태그5</button>
            <button class="tag6">태그6</button>
            <button class="tag7">태그7</button>
            <button class="tag8">태그8</button>
            <button class="tag9">태그9</button>
            <button class="tag10">태그10</button>
        </div>
    </div>

    <div class="updateButton">
        <button type="submit">수정하기</button>
        <button type="reset">취소</button>
    </div>
</form>

<footer>
    ㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇ<br>
    ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ
</footer>

<script src="${pageContext.request.contextPath}/resources/js/menuModal.js"></script>
</body>

</html>
