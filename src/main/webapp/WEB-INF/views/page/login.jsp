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
    <div class="naverLogin">
        <button type="button" onclick="showModal()">
            <a href="javascript:void(0)">네이버로그인</a>
        </button>
    </div>
    <div class="kakaoLogin">
        <button type="button" onclick="showModal()">
            <a href="javascript:void(0)">카카오로그인</a>
        </button>
    </div>
    <div class="googleLogin">
        <button type="button" onclick="showModal()">
            <a href="javascript:void(0)">구글로그인</a>
        </button>
    </div>
</div>

<!-- 모달 -->
<div id="myModal" class="infoModal">
    <div class="infoModal-content">
        <p>회원가입을 축하드립니다. 추가 정보를 입력해주세요</p>
        <button onclick="closeModal()">확인</button>
    </div>
</div>

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