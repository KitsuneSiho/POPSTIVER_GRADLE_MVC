<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="root" value="${pageContext.request.contextPath }" />

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${root}/resources/css/searchResultCss/popularAddPopup.css">
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

<div class="searchList">
    <article>
        <div class="searchListOpen" onclick="toggleSearchList(this)">
            <p>진행중 팝업스토어</p>
            <img src="${root}/resources/asset/화살표.svg" class="arrow" alt="화살표">
        </div>
        <div class="popupFestivalInfo">
            <div class="carousel">
                <div class="carousel-content" id="carousel-content">
                    <div class="card">
                        <div class="card-content">
                            <img src="${root}/resources/asset/포스터이미지/흠뻑쇼6.gif" alt="포스터1">
                            <img src="${root}/resources/asset/좋아요.svg" class="bookmark" alt="">
                            <h3>It's Your Day: 이번 광고, 생일 카페 주인공은 바로 너!</h3>
                            <p>
                                <img src="${root}/resources/asset/위치표시.svg" class="cardAddress" alt="">
                                서울특별시 마포구
                            </p>
                            <p>
                                <img src="${root}/resources/asset/날짜.svg" class="cardDate" alt="">
                                24.05.02 - 24.06.30
                            </p>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-content">
                            <img src="${root}/resources/asset/포스터이미지/흠뻑쇼6.gif" alt="포스터1">
                            <img src="${root}/resources/asset/좋아요.svg" class="bookmark" alt="">
                            <h3>It's Your Day: 이번 광고, 생일 카페 주인공은 바로 너!</h3>
                            <p>
                                <img src="${root}/resources/asset/위치표시.svg" class="cardAddress" alt="">
                                서울특별시 마포구
                            </p>
                            <p>
                                <img src="${root}/resources/asset/날짜.svg" class="cardDate" alt="">
                                24.05.02 - 24.06.30
                            </p>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-content">
                            <img src="${root}/resources/asset/포스터이미지/흠뻑쇼6.gif" alt="포스터1">
                            <img src="${root}/resources/asset/좋아요.svg" class="bookmark" alt="">
                            <h3>It's Your Day: 이번 광고, 생일 카페 주인공은 바로 너!</h3>
                            <p>
                                <img src="${root}/resources/asset/위치표시.svg" class="cardAddress" alt="">
                                서울특별시 마포구
                            </p>
                            <p>
                                <img src="${root}/resources/asset/날짜.svg" class="cardDate" alt="">
                                24.05.02 - 24.06.30
                            </p>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-content">
                            <img src="${root}/resources/asset/포스터이미지/흠뻑쇼6.gif" alt="포스터1">
                            <img src="${root}/resources/asset/좋아요.svg" class="bookmark" alt="">
                            <h3>It's Your Day: 이번 광고, 생일 카페 주인공은 바로 너!</h3>
                            <p>
                                <img src="${root}/resources/asset/위치표시.svg" class="cardAddress" alt="">
                                서울특별시 마포구
                            </p>
                            <p>
                                <img src="${root}/resources/asset/날짜.svg" class="cardDate" alt="">
                                24.05.02 - 24.06.30
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="searchListOpen" onclick="toggleSearchList(this)">
            <p>오픈 예정 팝업스토어</p>
            <img src="${root}/resources/asset/화살표.svg" class="arrow" alt="화살표">
        </div>
        <div class="popupFestivalInfo">
            <div class="carousel">
                <div class="carousel-content" id="carousel-content">
                    <div class="card">
                        <div class="card-content">
                            <img src="${root}/resources/asset/포스터이미지/흠뻑쇼6.gif" alt="포스터1">
                            <img src="${root}/resources/asset/좋아요.svg" class="bookmark" alt="">
                            <h3>It's Your Day: 이번 광고, 생일 카페 주인공은 바로 너!</h3>
                            <p>
                                <img src="${root}/resources/asset/위치표시.svg" class="cardAddress" alt="">
                                서울특별시 마포구
                            </p>
                            <p>
                                <img src="${root}/resources/asset/날짜.svg" class="cardDate" alt="">
                                24.05.02 - 24.06.30
                            </p>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-content">
                            <img src="${root}/resources/asset/포스터이미지/흠뻑쇼6.gif" alt="포스터1">
                            <img src="${root}/resources/asset/좋아요.svg" class="bookmark" alt="">
                            <h3>It's Your Day: 이번 광고, 생일 카페 주인공은 바로 너!</h3>
                            <p>
                                <img src="${root}/resources/asset/위치표시.svg" class="cardAddress" alt="">
                                서울특별시 마포구
                            </p>
                            <p>
                                <img src="${root}/resources/asset/날짜.svg" class="cardDate" alt="">
                                24.05.02 - 24.06.30
                            </p>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-content">
                            <img src="${root}/resources/asset/포스터이미지/흠뻑쇼6.gif" alt="포스터1">
                            <img src="${root}/resources/asset/좋아요.svg" class="bookmark" alt="">
                            <h3>It's Your Day: 이번 광고, 생일 카페 주인공은 바로 너!</h3>
                            <p>
                                <img src="${root}/resources/asset/위치표시.svg" class="cardAddress" alt="">
                                서울특별시 마포구
                            </p>
                            <p>
                                <img src="${root}/resources/asset/날짜.svg" class="cardDate" alt="">
                                24.05.02 - 24.06.30
                            </p>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-content">
                            <img src="${root}/resources/asset/포스터이미지/흠뻑쇼6.gif" alt="포스터1">
                            <img src="${root}/resources/asset/좋아요.svg" class="bookmark" alt="">
                            <h3>It's Your Day: 이번 광고, 생일 카페 주인공은 바로 너!</h3>
                            <p>
                                <img src="${root}/resources/asset/위치표시.svg" class="cardAddress" alt="">
                                서울특별시 마포구
                            </p>
                            <p>
                                <img src="${root}/resources/asset/날짜.svg" class="cardDate" alt="">
                                24.05.02 - 24.06.30
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="searchListOpen" onclick="toggleSearchList(this)">
            <p>종료 팝업스토어</p>
            <img src="${root}/resources/asset/화살표.svg" class="arrow" alt="화살표">
        </div>
        <div class="popupFestivalInfo">
            <div class="carousel">
                <div class="carousel-content" id="carousel-content">
                    <div class="card">
                        <div class="card-content">
                            <img src="${root}/resources/asset/포스터이미지/흠뻑쇼6.gif" alt="포스터1">
                            <img src="${root}/resources/asset/좋아요.svg" class="bookmark" alt="">
                            <h3>It's Your Day: 이번 광고, 생일 카페 주인공은 바로 너!</h3>
                            <p>
                                <img src="${root}/resources/asset/위치표시.svg" class="cardAddress" alt="">
                                서울특별시 마포구
                            </p>
                            <p>
                                <img src="${root}/resources/asset/날짜.svg" class="cardDate" alt="">
                                24.05.02 - 24.06.30
                            </p>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-content">
                            <img src="${root}/resources/asset/포스터이미지/흠뻑쇼6.gif" alt="포스터1">
                            <img src="${root}/resources/asset/좋아요.svg" class="bookmark" alt="">
                            <h3>It's Your Day: 이번 광고, 생일 카페 주인공은 바로 너!</h3>
                            <p>
                                <img src="${root}/resources/asset/위치표시.svg" class="cardAddress" alt="">
                                서울특별시 마포구
                            </p>
                            <p>
                                <img src="${root}/resources/asset/날짜.svg" class="cardDate" alt="">
                                24.05.02 - 24.06.30
                            </p>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-content">
                            <img src="${root}/resources/asset/포스터이미지/흠뻑쇼6.gif" alt="포스터1">
                            <img src="${root}/resources/asset/좋아요.svg" class="bookmark" alt="">
                            <h3>It's Your Day: 이번 광고, 생일 카페 주인공은 바로 너!</h3>
                            <p>
                                <img src="${root}/resources/asset/위치표시.svg" class="cardAddress" alt="">
                                서울특별시 마포구
                            </p>
                            <p>
                                <img src="${root}/resources/asset/날짜.svg" class="cardDate" alt="">
                                24.05.02 - 24.06.30
                            </p>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-content">
                            <img src="${root}/resources/asset/포스터이미지/흠뻑쇼6.gif" alt="포스터1">
                            <img src="${root}/resources/asset/좋아요.svg" class="bookmark" alt="">
                            <h3>It's Your Day: 이번 광고, 생일 카페 주인공은 바로 너!</h3>
                            <p>
                                <img src="${root}/resources/asset/위치표시.svg" class="cardAddress" alt="">
                                서울특별시 마포구
                            </p>
                            <p>
                                <img src="${root}/resources/asset/날짜.svg" class="cardDate" alt="">
                                24.05.02 - 24.06.30
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </article>
</div>

<footer>
    ㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇ<br>
    ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ
</footer>

<script src="${root}/resources/js/searchResult.js"></script>
<script src="${root}/resources/js/menuModal.js"></script>
<script src="${root}/resources/js/bookmarkToggle.js"></script>
</body>

</html>