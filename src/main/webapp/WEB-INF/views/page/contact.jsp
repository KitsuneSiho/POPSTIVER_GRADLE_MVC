<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:set var="root" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../../../resources/css/contact.css">
    <title>POPSTIVER</title>
    <style>
        @font-face {
            font-family: Giants;
            src: url('${root}/resources/asset/font/Giants-inline.ttf');
        }

        @font-face {
            font-family: KBO;
            src: url('${root}/resources/asset/font/KBO.ttf');
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
            <li><a href="">게시판</a></li>
        </ul>
    </div>
</div>

<div class="contactMenu">
    <a class="on" href="contact.jsp">
        <h2>공지사항</h2>
    </a>
    <a href="faq.jsp">
        <h2>FAQ</h2>
    </a>
    <a href="money.jsp">
        <h2>비즈니스 문의</h2>
    </a>
    <a href="report.jsp">
        <h2>제보하기</h2>
    </a>
    <a href="together.jsp">
        <h2>동행구하기</h2>
    </a>
    <a href="free.jsp">
        <h2>자유게시판</h2>
    </a>
</div>

<div class="board">
    <table class="boardTable">
        <tbody>
        <tr>
            <td>공지사항</td>
            <td>2024-06-12 15:12</td>
        </tr>
        </tbody>
    </table>
</div>

<div class="pageNumber">
    <ul>
        <li>
            <a class="pageOn">1</a>
        </li>
    </ul>
</div>

<footer>
    ㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇ<br>
    ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ
</footer>

<script src="${root}/resources/js/menuModal.js"></script>
</body>

</html>