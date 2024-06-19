<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="root" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${root}/resources/css/myPageCss/myPage.css">
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
                <button type="submit" class="searchButton" onclick="window.location.href='searchResult'">
                    <img src="${root}/resources/asset/메인검색창검색버튼.svg" alt="">
                </button>
            </label>
        </div>
    </div>



    <div class="mainTopButton">
        <button class="loginButton" onclick="window.location.href='login'">
            로그인
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
            <li><a href="calendar">행사 일정</a></li>
            <li><a href="contact">게시판</a></li>
        </ul>
    </div>
</div>


<div class="myPage">
    <a class="on" href="myPage">
        <h2>내 정보</h2>
    </a>
    <a href="bookmark">
        <h2>관심 행사</h2>
    </a>
    <a href="deleteUser">
        <h2>회원 탈퇴</h2>
    </a>
</div>

<div class="userInfo">
    <ul class="info">
        <li>
            <span>이름</span><br>
            <label>
                <input type="text">
            </label>
        </li>
        <li>
            <span>닉네임</span><br>
            <label>
                <input type="text">
            </label>
        </li>
        <li>
            <span>계정 정보</span><br>
            <label>
                <input type="email">
            </label>
        </li>
        <li>
            <span>연락처</span><br>
            <label>
                <input type="tel">
            </label>
        </li>
        <li>
            <span>생일</span><br>
            <label>
                <input type="text" placeholder="년 - 월 - 일">
            </label>
        </li>
        <li>
            <span>주소</span><br>
            <label>
                <input type="text">
            </label>
            <button class="searchAddress" type="button">주소 검색</button>
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



<footer>
    ㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇ<br>
    ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ
</footer>

<script src="${root}/resources/js/menuModal.js"></script>
</body>

</html>