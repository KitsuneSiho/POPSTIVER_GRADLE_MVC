<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="root" value="${pageContext.request.contextPath }" />

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${root}/resources/css/searchResultCss/popularAdd.css">
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
<jsp:include page="/WEB-INF/views/page/fix/header.jsp" />

<div class="searchList">
    <article>
        <div class="searchListOpen" onclick="toggleSearchList(this)">
            <p>진행 중</p>
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
            <p>오픈 예정</p>
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
            <p>종료</p>
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

<jsp:include page="/WEB-INF/views/page/fix/footer.jsp" />

<script src="${root}/resources/js/searchResult.js"></script>
<script src="${root}/resources/js/bookmarkToggle.js"></script>
</body>

</html>