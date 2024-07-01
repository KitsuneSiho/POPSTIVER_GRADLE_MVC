<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="root" value="${pageContext.request.contextPath }" />

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${root}/resources/css/mainCss/mainPopup.css">
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

        @font-face {
            font-family: Pre;
            src: url('${root}/resources/font/Pre.ttf');
        }
    </style>
</head>

<body>

<jsp:include page="/WEB-INF/views/page/fix/header.jsp" />

<div class="mainButton">
    <button class="festivalButton" onclick="window.location.href='mainFestival'">
        FESTIVAL 보러가기
    </button>
</div>

<div class="mainPoster">
    <img src="${root}/resources/asset/포스터이미지/워터밤가로.webp" alt="">
</div>

<div class="main-content">
    <div class="popular">
        <div class="popularPosterText">
            <p class="popularText1">인기</p>
            <p class="popularText2" onclick="window.location.href='popularAddPopup'">더보기</p>
        </div>
        <div class="popularPoster">
            <div class="slide-container">
                <div class="slide-track">
                    <div class="poster-item">
                        <img src="${root}/resources/asset/포스터이미지/서울.webp" alt="" onclick="window.location.href='posterInfo'">
                        <img src="${root}/resources/asset/${isLiked ? '좋아요' : '아니좋아요'}.svg"
                             class="bookmark"
                             alt=""
                             data-event-no="${result.event_no}"
                             data-event-type="${result.event_type}">
                        <span class="like-count">${likeCount}</span>
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
            <p class="openText2" onclick="window.location.href='openAddPopup'">더보기</p>
        </div>
        <div class="openPoster">
            <div class="slide-container">
                <div class="slide-track">
                    <c:choose>
                        <c:when test="${not empty upcomingPopups}">
                            <c:forEach var="popup" items="${upcomingPopups}">
                                <div class="open-item">
                                    <img src="${popup.popup_attachment}" alt="${popup.popup_title}" onclick="window.location.href='popupInfo?id=${popup.popup_no}'">
                                    <img src="${root}/resources/asset/좋아요.svg" class="bookmark" alt="" data-popup-no="${popup.popup_no}">
                                    <p class="open-caption">${popup.popup_title}</p>
                                </div>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <p>현재 오픈 예정인 팝업 스토어가 없습니다.</p>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/views/page/fix/footer.jsp" />

<script src="${root}/resources/js/bookmarkToggle.js"></script>

</body>
</html>