<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="root" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html lang="en">


<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${root}/resources/css/myPageCss/login.css">
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


<div class="loginTitle">
    <h1>간편 로그인</h1>
    <p>아이디 / 비밀번호 입력할 필요 없어요!<br>
        SNS 아이디로 빠르게 로그인 / 회원가입 하세요 :)
    </p>
</div>

<div class="nkgloginButton">
    <div class="naverLogin">
            <button type="button" onclick="window.location.href='${root}/oauth2/authorization/naver'">
                <img src="${root}/resources/asset/네이버아이콘.png" alt="">
                <span>네이버 로그인 / 회원가입</span>
            </button>
    </div>

    <div class="kakaoLogin">
        <button type="button" onclick="window.location.href='${root}/oauth2/authorization/kakao'">
            <img src="${root}/resources/asset/카카오아이콘.png" alt="">
            <span>카카오 로그인 / 회원가입</span>
        </button>
    </div>

    <div class="googleLogin">
        <button type="button" onclick="window.location.href='${root}/oauth2/authorization/google'">
            <img src="${root}/resources/asset/구글아이콘.png" alt="">
            <span>구글 로그인 / 회원가입</span>
        </button>
    </div>

</div>


<footer>
    ㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇ<br>ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ
</footer>


<script src="${root}/resources/js/menuModal.js"></script>
<script src="${root}/resources/js/loginApi.js"></script>
</body>

</html>