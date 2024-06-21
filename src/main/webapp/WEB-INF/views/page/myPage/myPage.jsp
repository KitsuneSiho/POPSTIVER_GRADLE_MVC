<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="${root}/resources/js/myPage.js"></script>
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

<div class="myPage">
    <a class="on" href="">
        <h2>내 정보</h2>
    </a>
    <a href="bookmark">
        <h2>관심 행사</h2>
    </a>
    <a href="deleteUser">
        <h2>회원 탈퇴</h2>
    </a>
</div>

<form id="userInfoForm" method="post" onsubmit="submitForm(event)">
    <div class="userInfo">
        <ul class="info">
            <li>
                <span>회원 유형</span><br>
                <div class="userType">
                <input type="radio" id="host" name="user_type" value="1" ${user.user_type == 1 ? 'checked' : ''}>
                <label for="host">주최자</label>
                <input type="radio" id="user" name="user_type" value="2" ${user.user_type == 2 ? 'checked' : ''}>
                <label for="user">사용자</label>
                </div>
            </li>
            <li>
                <span>이름</span><br>
                <label>
                    <input type="text" name="user_name" value="${user.user_name}" readonly>
                </label>
            </li>
            <li>
                <span>닉네임</span><br>
                <label>
                    <input type="text" name="user_nickName" value="${user.user_nickName}">
                </label>
            </li>
            <li>
                <span>이메일</span><br>
                <label>
                    <input type="text" name="user_email" value="${user.user_email}" readonly>
                </label>
            </li>
            <li>
                <span>생일</span><br>
                <label>
                    <input type="text" name="user_birth" value="${user.user_birth}" readonly>
                </label>
            </li>
            <li>
                <span>성별</span><br>
                <div class="userGender">
                <input type="radio" id="male" name="user_gender" value="male" ${user.user_gender == 'male' ? 'checked' : ''}>
                <label for="male">남</label>
                <input type="radio" id="female" name="user_gender" value="female" ${user.user_gender == 'female' ? 'checked' : ''}>
                <label for="female">여</label>
                </div>
            </li>
        </ul>
    </div>


    <div class="updateButton">
        <button type="button" id="editButton" onclick="enableEdit()">수정하기</button>
        <button type="submit" id="saveButton" style="display:none">저장하기</button>
    </div>
</form>

<footer>
    ㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇ<br>
    ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ
</footer>

<script src="${root}/resources/js/menuModal.js"></script>
</body>

</html>