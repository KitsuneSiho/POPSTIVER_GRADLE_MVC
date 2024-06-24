<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:set var="root" value="${pageContext.request.contextPath }" />

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${root}/resources/css/mainCss/main.css">
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

<div class="mainButton">
    <button class="popupButton" onclick="window.location.href='mainPopup'">
        <img src="${root}/resources/asset/POPUP메인버튼.png" alt="">
    </button>
    <button class="festivalButton" onclick="window.location.href='mainFestival'">
        <img src="${root}/resources/asset/FESTIVAL메인버튼.png" alt="">
    </button>
</div>

<div class="mainPoster">
    <video width="1000px" height="400px" controls autoplay loop muted>
        <source src="${root}/resources/asset/포스터이미지/워터밤영상.mp4" type="video/mp4">
    </video>
</div>

<div class="popular">
    <div class="popularPosterText">
        <p class="popularText1">인기</p>
        <p class="popularText2" onclick="window.location.href='popularAdd'">더보기</p>
    </div>
    <div class="popularPoster">
        <div class="slide-container">
            <div class="slide-track">
                <div class="poster-item">
                    <img src="${root}/resources/asset/포스터이미지/서울.webp" alt="" onclick="window.location.href='posterInfo'">
                    <img src="${root}/resources/asset/좋아요.svg" class="bookmark" alt="">
                    <p class="poster-caption">서울 포스터</p>
                </div>
                <div class="poster-item">
                    <img src="${root}/resources/asset/포스터이미지/대구.webp" alt="">
                    <img src="${root}/resources/asset/좋아요.svg" class="bookmark" alt="">
                    <p class="poster-caption">대구 포스터</p>
                </div>
                <div class="poster-item">
                    <img src="${root}/resources/asset/포스터이미지/대전.webp" alt="">
                    <img src="${root}/resources/asset/좋아요.svg" class="bookmark" alt="">
                    <p class="poster-caption">대전 포스터</p>
                </div>
                <div class="poster-item">
                    <img src="${root}/resources/asset/포스터이미지/부산.webp" alt="">
                    <img src="${root}/resources/asset/좋아요.svg" class="bookmark" alt="">
                    <p class="poster-caption">부산 포스터</p>
                </div>
                <div class="poster-item">
                    <img src="${root}/resources/asset/포스터이미지/속초.webp" alt="">
                    <img src="${root}/resources/asset/좋아요.svg" class="bookmark" alt="">
                    <p class="poster-caption">속초 포스터</p>
                </div>
                <div class="poster-item">
                    <img src="${root}/resources/asset/포스터이미지/대구.webp" alt="">
                    <img src="${root}/resources/asset/좋아요.svg" class="bookmark" alt="">
                    <p class="poster-caption">대구 포스터</p>
                </div>
                <div class="poster-item">
                    <img src="${root}/resources/asset/포스터이미지/대전.webp" alt="">
                    <img src="${root}/resources/asset/좋아요.svg" class="bookmark" alt="">
                    <p class="poster-caption">대전 포스터</p>
                </div>
                <div class="poster-item">
                    <img src="${root}/resources/asset/포스터이미지/부산.webp" alt="">
                    <img src="${root}/resources/asset/좋아요.svg" class="bookmark" alt="">
                    <p class="poster-caption">부산 포스터</p>
                </div>
                <div class="poster-item">
                    <img src="${root}/resources/asset/포스터이미지/속초.webp" alt="">
                    <img src="${root}/resources/asset/좋아요.svg" class="bookmark" alt="">
                    <p class="poster-caption">속초 포스터</p>
                </div>
                <div class="poster-item">
                    <img src="${root}/resources/asset/포스터이미지/대구.webp" alt="">
                    <img src="${root}/resources/asset/좋아요.svg" class="bookmark" alt="">
                    <p class="poster-caption">대구 포스터</p>
                </div>
                <div class="poster-item">
                    <img src="${root}/resources/asset/포스터이미지/부산.webp" alt="">
                    <img src="${root}/resources/asset/좋아요.svg" class="bookmark" alt="">
                    <p class="poster-caption">부산 포스터</p>
                </div>
                <div class="poster-item">
                    <img src="${root}/resources/asset/포스터이미지/속초.webp" alt="">
                    <img src="${root}/resources/asset/좋아요.svg" class="bookmark" alt="">
                    <p class="poster-caption">속초 포스터</p>
                </div>

            </div>
        </div>
    </div>
</div>


<div class="open">
    <div class="openPosterText">
        <p class="openText1">오픈 예정</p>
        <p class="openText2" onclick="window.location.href='openAdd'">더보기</p>
    </div>
    <div class="openPoster">
        <div class="slide-container">
            <div class="slide-track">
                <div class="open-item">
                    <img src="${root}/resources/asset/포스터이미지/흠뻑쇼.gif" alt="">
                    <img src="${root}/resources/asset/좋아요.svg" class="bookmark" alt="">
                    <p class="open-caption">흠뻑쇼1</p>
                </div>
                <div class="open-item">
                    <img src="${root}/resources/asset/포스터이미지/흠뻑쇼3.gif" alt="">
                    <img src="${root}/resources/asset/좋아요.svg" class="bookmark" alt="">
                    <p class="open-caption">흠뻑쇼2</p>
                </div>
                <div class="open-item">
                    <img src="${root}/resources/asset/포스터이미지/흠뻑쇼.gif" alt="">
                    <img src="${root}/resources/asset/좋아요.svg" class="bookmark" alt="">
                    <p class="open-caption">흠뻑쇼1</p>
                </div>
                <div class="open-item">
                    <img src="${root}/resources/asset/포스터이미지/흠뻑쇼3.gif" alt="">
                    <img src="${root}/resources/asset/좋아요.svg" class="bookmark" alt="">
                    <p class="open-caption">흠뻑쇼2</p>
                </div>
                <div class="open-item">
                    <img src="${root}/resources/asset/포스터이미지/흠뻑쇼.gif" alt="">
                    <img src="${root}/resources/asset/좋아요.svg" class="bookmark" alt="">
                    <p class="open-caption">흠뻑쇼1</p>
                </div>
                <div class="open-item">
                    <img src="${root}/resources/asset/포스터이미지/흠뻑쇼3.gif" alt="">
                    <img src="${root}/resources/asset/좋아요.svg" class="bookmark" alt="">
                    <p class="open-caption">흠뻑쇼2</p>
                </div>
                <div class="open-item">
                    <img src="${root}/resources/asset/포스터이미지/흠뻑쇼.gif" alt="">
                    <img src="${root}/resources/asset/좋아요.svg" class="bookmark" alt="">
                    <p class="open-caption">흠뻑쇼1</p>
                </div>
                <div class="open-item">
                    <img src="${root}/resources/asset/포스터이미지/흠뻑쇼3.gif" alt="">
                    <img src="${root}/resources/asset/좋아요.svg" class="bookmark" alt="">
                    <p class="open-caption">흠뻑쇼2</p>
                </div>
                <div class="open-item">
                    <img src="${root}/resources/asset/포스터이미지/흠뻑쇼.gif" alt="">
                    <img src="${root}/resources/asset/좋아요.svg" class="bookmark" alt="">
                    <p class="open-caption">흠뻑쇼1</p>
                </div>
                <div class="open-item">
                    <img src="${root}/resources/asset/포스터이미지/흠뻑쇼3.gif" alt="">
                    <img src="${root}/resources/asset/좋아요.svg" class="bookmark" alt="">
                    <p class="open-caption">흠뻑쇼2</p>
                </div>
                <div class="open-item">
                    <img src="${root}/resources/asset/포스터이미지/흠뻑쇼.gif" alt="">
                    <img src="${root}/resources/asset/좋아요.svg" class="bookmark" alt="">
                    <p class="open-caption">흠뻑쇼1</p>
                </div>
                <div class="open-item">
                    <img src="${root}/resources/asset/포스터이미지/흠뻑쇼3.gif" alt="">
                    <img src="${root}/resources/asset/좋아요.svg" class="bookmark" alt="">
                    <p class="open-caption">흠뻑쇼2</p>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/views/page/fix/footer.jsp" />
<script src="${root}/resources/js/bookmarkToggle.js"></script>

</body>
</html>