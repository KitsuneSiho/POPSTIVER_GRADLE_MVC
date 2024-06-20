<%@ page contentType="text/html;charset=UTF-8" %>
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

        .infoModal {
            display: none;
            position: fixed;
            z-index: 1;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgba(0, 0, 0, 0.4);
        }

        .infoModal-content {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            background-color: white;
            padding: 20px;
            border: 1px solid #888;
            width: 300px;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.3);
            border-radius: 10px;
            text-align: center;
        }

        .infoModal-content button {
            margin-top: 20px;
            padding: 10px 20px;
            font-size: 16px;
            cursor: pointer;
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

<div class="loginButton">
    <!-- Kakao Login -->
    <div class="kakaoLogin">
        <button type="button" onclick="showModal()">
            <a href="${root}/oauth2/authorization/kakao">
                <img src="${root}/resources/images/kakao_login_medium_narrow.png" alt="Kakao 로그인">
            </a>
        </button>
        <button type="button" onclick="logoutKakao()">로그아웃</button>
<div class="loginTitle">
    <h1>간편 로그인</h1>
    <p>아이디 / 비밀번호 입력할 필요 없어요!<br>
        SNS 아이디로 빠르게 로그인 / 회원가입 하세요 :)
    </p>
</div>

<div class="nkgloginButton">
    <div class="naverLogin">
            <button type="button" onclick="">
                <img src="${root}/resources/asset/네이버아이콘.png" alt="">
                <span>네이버 로그인 / 회원가입</span>
            </button>
    </div>
    <div class="kakaoLogin">
        <button type="button" onclick="">
            <img src="${root}/resources/asset/카카오아이콘.png" alt="">
            <span>카카오 로그인 / 회원가입</span>

    <script>
        function logoutKakao() {
            fetch('${root}/logout/kakao', { method: 'POST' })
                .then(response => {
                    if (response.ok) {
                        document.cookie.split(";").forEach(function(c) {
                            document.cookie = c.trim().split("=")[0] + '=;expires=Thu, 01 Jan 1970 00:00:00 UTC;path=/;';
                        });
                        window.location.href = 'https://kauth.kakao.com/oauth/logout?client_id=${root}/oauth2/authorization/kakao&logout_redirect_uri=http://localhost:8080/login';
                    }
                })
                .catch(error => console.error('Error:', error));
        }
    </script>

    <!-- Google Login -->
    <div class="googleLogin">
        <button type="button" onclick="showModal()">
            <a href="${root}/oauth2/authorization/google">
                <img src="${root}/resources/images/google_login_button.png" alt="Google 로그인">
            </a>
        </button>
        <button type="button" onclick="logoutGoogle()">로그아웃</button>
    </div>
    <div class="googleLogin">
        <button type="button" onclick="">
            <img src="${root}/resources/asset/구글아이콘.png" alt="">
            <span>구글 로그인 / 회원가입</span>

    <script>
        function logoutGoogle() {
            fetch('${root}/logout/google', { method: 'POST' })
                .then(response => {
                    if (response.ok) {
                        document.cookie.split(";").forEach(function(c) {
                            document.cookie = c.trim().split("=")[0] + '=;expires=Thu, 01 Jan 1970 00:00:00 UTC;path=/;';
                        });
                        window.location.href = '${root}/login';
                    }
                })
                .catch(error => console.error('Error:', error));
        }
    </script>

    <!-- Naver Login -->
    <div class="naverLogin">
        <button type="button" onclick="showModal()">
            <a href="${root}/oauth2/authorization/naver">
                <img src="${root}/resources/images/naver_login_button.png" alt="Naver 로그인">
            </a>
        </button>
        <button type="button" onclick="logoutNaver()">로그아웃</button>
    </div>

    <script>
        function logoutNaver() {
            fetch('${root}/logout/naver', { method: 'POST' })
                .then(response => {
                    if (response.ok) {
                        document.cookie.split(";").forEach(function(c) {
                            document.cookie = c.trim().split("=")[0] + '=;expires=Thu, 01 Jan 1970 00:00:00 UTC;path=/;';
                        });
                        window.location.href = '${root}/login';
                    }
                })
                .catch(error => console.error('Error:', error));
        }
    </script>
</div>


<%--<!-- 모달 -->--%>
<%--<div id="myModal" class="infoModal">--%>
<%--    <div class="infoModal-content">--%>
<%--        <p>회원가입을 축하드립니다. 추가 정보를 입력해주세요</p>--%>
<%--        <button onclick="closeModal()">확인</button>--%>
<%--    </div>--%>
<%--</div>--%>

<footer>
    ㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇ<br>ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ
</footer>

<script>
    function showModal() {
        document.getElementById('myModal').style.display = 'block';
    }

    function closeModal() {
        document.getElementById('myModal').style.display = 'none';
        window.location.href = 'myPage';
    }

    window.onclick = function(event) {
        const modal = document.getElementById('myModal');
        if (event.target === modal) {
            modal.style.display = 'none';
        }
    }
</script>
<script src="${root}/resources/js/menuModal.js"></script>
</body>

</html>
