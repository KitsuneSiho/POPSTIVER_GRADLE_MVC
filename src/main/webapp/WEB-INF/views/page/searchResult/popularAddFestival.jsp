<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="root" value="${pageContext.request.contextPath }" />

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${root}/resources/css/searchResultCss/popularAddFestival.css">
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

<div class="searchList">
    <article>
        <c:set var="now" value="<%=new java.util.Date()%>" />
        <fmt:formatDate var="today" value="${now}" pattern="yyyy-MM-dd" />

        <c:set var="hasOngoing" value="false" />
        <c:set var="hasUpcoming" value="false" />
        <c:set var="hasEnded" value="false" />

        <div class="searchListOpen" id="ongoingSection" onclick="toggleSearchList(this)">
            <p>진행중 페스티벌</p>
            <img src="${root}/resources/asset/화살표.svg" class="arrow" alt="화살표">
        </div>
        <div class="popupFestivalInfo" id="ongoingContent">
            <div class="carousel">
                <div class="carousel-content" id="carousel-content-ongoing">
                    <c:forEach var="festival" items="${festivals}">
                        <c:if test="${today >= festival.festival_start && today <= festival.festival_end}">
                            <c:set var="hasOngoing" value="true" />
                            <div class="card">
                                <div class="card-content" data-eventtype="${festival.event_type}" data-eventno="${festival.festival_no}">
                                    <img src="${festival.festival_attachment}" alt="포스터">
                                    <img src="${root}/resources/asset/${isLiked ? '좋아요' : '아니좋아요'}.svg"
                                         class="bookmark"
                                         alt=""
                                         data-event-no="${festival.festival_no}"
                                         data-event-type="${festival.event_type}">
                                    <h3>${festival.festival_title}</h3>
                                    <p>
                                        <img src="${root}/resources/asset/위치표시.svg" class="cardAddress" alt="">
                                            ${festival.festival_dist} ${festival.festival_subdist} ${festival.festival_location}
                                    </p>
                                    <p>
                                        <img src="${root}/resources/asset/날짜.svg" class="cardDate" alt="">
                                            ${festival.festival_start} - ${festival.festival_end}
                                    </p>
                                </div>
                            </div>
                        </c:if>
                    </c:forEach>
                </div>
                <div class="pagination">
                    <button class="prev-page">&lt;</button>
                    <span class="page-info">1/1</span>
                    <button class="next-page">&gt;</button>
                </div>
            </div>
        </div>

        <div class="searchListOpen" id="upcomingSection" onclick="toggleSearchList(this)">
            <p>오픈 예정 페스티벌</p>
            <img src="${root}/resources/asset/화살표.svg" class="arrow" alt="화살표">
        </div>
        <div class="popupFestivalInfo" id="upcomingContent">
            <div class="carousel">
                <div class="carousel-content" id="carousel-content-upcoming">
                    <c:forEach var="festival" items="${festivals}">
                        <c:if test="${today < festival.festival_start}">
                            <c:set var="hasUpcoming" value="true" />
                            <div class="card">
                                <div class="card-content" data-eventtype="${festival.event_type}" data-eventno="${festival.festival_no}">
                                    <img src="${festival.festival_attachment}" alt="포스터">
                                    <img src="${root}/resources/asset/${isLiked ? '좋아요' : '아니좋아요'}.svg"
                                         class="bookmark"
                                         alt=""
                                         data-event-no="${festival.festival_no}"
                                         data-event-type="${festival.event_type}">
                                    <h3>${festival.festival_title}</h3>
                                    <p>
                                        <img src="${root}/resources/asset/위치표시.svg" class="cardAddress" alt="">
                                            ${festival.festival_dist} ${festival.festival_subdist} ${festival.festival_location}
                                    </p>
                                    <p>
                                        <img src="${root}/resources/asset/날짜.svg" class="cardDate" alt="">
                                            ${festival.festival_start} - ${festival.festival_end}
                                    </p>
                                </div>
                            </div>
                        </c:if>
                    </c:forEach>
                </div>
                <div class="pagination">
                    <button class="prev-page">&lt;</button>
                    <span class="page-info">1/1</span>
                    <button class="next-page">&gt;</button>
                </div>
            </div>
        </div>

        <div class="searchListOpen" id="endedSection" onclick="toggleSearchList(this)">
            <p>종료 페스티벌</p>
            <img src="${root}/resources/asset/화살표.svg" class="arrow" alt="화살표">
        </div>
        <div class="popupFestivalInfo" id="endedContent">
            <div class="carousel">
                <div class="carousel-content" id="carousel-content-ended">
                    <c:forEach var="festival" items="${festivals}">
                    <c:if test="${today > festival.festival_end}">
                        <c:set var="hasEnded" value="true" />
                        <div class="card">
                            <div class="card-content" data-eventtype="${festival.event_type}" data-eventno="${festival.festival_no}">
                                <img src="${festival.festival_attachment}" alt="포스터">
                                <img src="${root}/resources/asset/${isLiked ? '좋아요' : '아니좋아요'}.svg"
                                     class="bookmark"
                                     alt=""
                                     data-event-no="${festival.festival_no}"
                                     data-event-type="${festival.event_type}">
                                <h3>${festival.festival_title}</h3>
                                <p>
                                    <img src="${root}/resources/asset/위치표시.svg" class="cardAddress" alt="">
                                        ${festival.festival_dist} ${festival.festival_subdist} ${festival.festival_location}
                                </p>
                                <p>
                                    <img src="${root}/resources/asset/날짜.svg" class="cardDate" alt="">
                                        ${festival.festival_start} - ${festival.festival_end}
                                </p>
                            </div>
                        </div>
                    </c:if>
                    </c:forEach>
                </div>
                <div class="pagination">
                    <button class="prev-page">&lt;</button>
                    <span class="page-info">1/1</span>
                    <button class="next-page">&gt;</button>
                </div>
            </div>
        </div>
    </article>
</div>

<jsp:include page="/WEB-INF/views/page/fix/footer.jsp" />

<input type="hidden" id="user_id" name="user_id" value="">
<input type="hidden" id="user_name" name="user_name" value="">

<script>
    var hasOngoing = ${hasOngoing};
    var hasUpcoming = ${hasUpcoming};
    var hasEnded = ${hasEnded};
</script>

<script src="${root}/resources/js/searchResult.js"></script>
<script src="${root}/resources/js/bookmarkToggle.js"></script>
</body>

</html>