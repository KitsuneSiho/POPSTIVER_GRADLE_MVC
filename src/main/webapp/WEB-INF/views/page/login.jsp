<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:set var="root" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${root}/resources/css/login.css">
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

        /* 모달 스타일 */
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
            </label>
            <button type="submit" class="searchButton" onclick="window.location.href='searchResult'">
                <img src="${root}/resources/asset/main_search_button.svg" alt="">
            </button>
        </div>
    </div>

    <div class="mainTopButton">
        <button class="myPageButton" onclick="window.location.href='myPage.html'">
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
            <li><a href="login.jsp">로그인</a></li>
            <li><a href="map.jsp">근처 행사</a></li>
            <li><a href="">관심 행사</a></li>
            <li><a href="contact.jsp">게시판</a></li>
        </ul>
    </div>
</div>

<div class="loginButton">
    <div class="kakaoLogin">
        <button type="button" onclick="showModal()">
            <a href="https://kauth.kakao.com/oauth/authorize?client_id=cffd2c8562ed8ebbb1d01137aa0349f7&redirect_uri=http://localhost:8080/login/oauth2/code/kakao&response_type=code"
               th:href="@{https://kauth.kakao.com/oauth/authorize(client_id=cffd2c8562ed8ebbb1d01137aa0349f7, redirect_uri=http://localhost:8080/login/oauth2/code/kakao, response_type='code')}">
                <img src="/images/kakao_login_medium_narrow.png" alt="Kakao 로그인">
            </a>
        </button>
        <button type="button" onclick="logoutKakao()">로그아웃</button>
    </div>

    <script>
        function logoutKakao() {
            // 서버 로그아웃 요청
            fetch('/logout/kakao', { method: 'POST' })
                .then(response => {
                    if (response.ok) {
                        // 모든 쿠키 삭제
                        document.cookie.split(";").forEach(function(c) {
                            document.cookie = c.trim().split("=")[0] + '=;expires=Thu, 01 Jan 1970 00:00:00 UTC;path=/;';
                        });
                        // 리디렉션
                        window.location.href = 'https://kauth.kakao.com/oauth/logout?client_id=cffd2c8562ed8ebbb1d01137aa0349f7&logout_redirect_uri=http://localhost:8080/login';
                    }
                })
                .catch(error => console.error('Error:', error));
        }
    </script>
    </div>
    <div class="googleLogin">
        <button type="button" onclick="showModal()">
            <a href="https://accounts.google.com/o/oauth2/v2/auth?client_id=936690125897-3ckd20rldril6je2gn3p6575la97vhj9.apps.googleusercontent.com&redirect_uri=http://localhost:8080/auth/google/callback&response_type=code&scope=email%20profile%20openid">
                <img src="/images/google_login_button.png" alt="Google 로그인">
            </a>
        </button>
        <button type="button" onclick="logoutGoogle()">로그아웃</button>
    </div>

<script>
    function logoutGoogle() {
        fetch('/logout/google', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({}) // 빈 객체 전송
        })
            .then(response => {
                if (response.ok) {
                    // 모든 쿠키 삭제
                    document.cookie.split(";").forEach(function(c) {
                        document.cookie = c.trim().split("=")[0] + '=;expires=Thu, 01 Jan 1970 00:00:00 UTC;path=/;';
                    });
                    // 리디렉션
                    window.location.href = 'http://localhost:8080/login';
                }
            })
            .catch(error => console.error('Error:', error));
    }
</script>
<div class="naverLogin">
        <button type="button" onclick="showModal()">
            <a href="https://nid.naver.com/oauth2.0/authorize?client_id=jUYpMer5eMaFflJnxZX6&redirect_uri=http://localhost:8080/auth/naver/callback&response_type=code&scope=name%20email%20profile_image"
               th:href="@{https://nid.naver.com/oauth2.0/authorize(client_id='jUYpMer5eMaFflJnxZX6', redirect_uri='http://localhost:8080/auth/naver/callback', response_type='code', scope='name%20email%20profile_image')}">
                <img src="/images/naver_login_button.png" alt="Naver 로그인">
            </a>
        </button>
    <button type="button" onclick="logoutNaver()">네이버 로그아웃</button>
    </div>
</div>
<script>
    function logoutNaver() {
        // 서버로 네이버 로그아웃 요청 보내기
        fetch('/logout/naver', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({})
        })
            .then(response => {
                if (response.ok) {
                    // 모든 쿠키 삭제
                    document.cookie.split(";").forEach(function(c) {
                        document.cookie = c.trim().split("=")[0] + '=;expires=Thu, 01 Jan 1970 00:00:00 UTC;path=/;';
                    });
                    // 성공적으로 로그아웃한 경우
                    alert('네이버 로그아웃 완료!');
                    window.location.href = 'http://localhost:8080/login';
                    // 필요한 경우 추가적인 처리 (예: 쿠키 삭제, 페이지 리디렉션 등)
                } else {
                    // 로그아웃 실패 시 처리
                    alert('네이버 로그아웃 실패');
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('네이버 로그아웃 중 오류가 발생했습니다.');
            });
    }
</script>

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
        window.location.href = 'myPage.html';
    }

    // 모달 외부 클릭 시 모달 닫기
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