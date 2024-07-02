<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="root" value="${pageContext.request.contextPath }" />

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${root}/resources/css/myPageCss/bookmark.css">
    <title>POPSTIVER - 관심 행사</title>
    <script>
        // 각 섹션에 이벤트가 있는지 여부를 JavaScript 변수로 설정
        var hasOngoing = ${not empty ongoingEvents};
        var hasUpcoming = ${not empty upcomingEvents};
        var hasEnded = ${not empty endedEvents};
    </script>
</head>

<body>
<jsp:include page="/WEB-INF/views/page/fix/header.jsp" />

<div class="searchList">
    <article>
        <div class="searchListOpen" id="ongoingSection" onclick="toggleSearchList(this)">
            <p>진행중</p>
            <img src="${root}/resources/asset/화살표.svg" class="arrow" alt="화살표">
        </div>
        <div class="popupFestivalInfo" id="ongoingContent">
            <div class="carousel">
                <div class="carousel-content" id="carousel-content">
                    <c:forEach var="event" items="${ongoingEvents}">
                        <div class="card">
                            <div class="card-content">
                                <img src="${event.attachment}" alt="포스터">
                                <img src="${root}/resources/asset/좋아요.svg"
                                     class="bookmark"
                                     alt=""
                                     data-event-no="${event.event_no}"
                                     data-event-type="${event.event_type}">
                                <h3>${event.title}</h3>
                                <p>
                                    <img src="${root}/resources/asset/위치표시.svg" class="cardAddress" alt="">
                                        ${event.location}
                                </p>
                                <p>
                                    <img src="${root}/resources/asset/날짜.svg" class="cardDate" alt="">
                                        ${event.startDate} - ${event.endDate}
                                </p>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>

        <div class="searchListOpen" id="upcomingSection" onclick="toggleSearchList(this)">
            <p>오픈 예정</p>
            <img src="${root}/resources/asset/화살표.svg" class="arrow" alt="화살표">
        </div>
        <div class="popupFestivalInfo" id="upcomingContent">
            <div class="carousel">
                <div class="carousel-content" id="carousel-content">
                    <c:forEach var="event" items="${upcomingEvents}">
                        <div class="card">
                            <div class="card-content">
                                <img src="${event.attachment}" alt="포스터">
                                <img src="${root}/resources/asset/좋아요.svg"
                                     class="bookmark"
                                     alt=""
                                     data-event-no="${event.event_no}"
                                     data-event-type="${event.event_type}">
                                <h3>${event.title}</h3>
                                <p>
                                    <img src="${root}/resources/asset/위치표시.svg" class="cardAddress" alt="">
                                        ${event.location}
                                </p>
                                <p>
                                    <img src="${root}/resources/asset/날짜.svg" class="cardDate" alt="">
                                        ${event.startDate} - ${event.endDate}
                                </p>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>

        <div class="searchListOpen" id="endedSection" onclick="toggleSearchList(this)">
            <p>종료</p>
            <img src="${root}/resources/asset/화살표.svg" class="arrow" alt="화살표">
        </div>
        <div class="popupFestivalInfo" id="endedContent">
            <div class="carousel">
                <div class="carousel-content" id="carousel-content">
                    <c:forEach var="event" items="${endedEvents}">
                        <div class="card">
                            <div class="card-content">
                                <img src="${event.attachment}" alt="포스터">
                                <img src="${root}/resources/asset/좋아요.svg"
                                     class="bookmark"
                                     alt=""
                                     data-event-no="${event.event_no}"
                                     data-event-type="${event.event_type}">
                                <h3>${event.title}</h3>
                                <p>
                                    <img src="${root}/resources/asset/위치표시.svg" class="cardAddress" alt="">
                                        ${event.location}
                                </p>
                                <p>
                                    <img src="${root}/resources/asset/날짜.svg" class="cardDate" alt="">
                                        ${event.startDate} - ${event.endDate}
                                </p>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </article>
</div>

<jsp:include page="/WEB-INF/views/page/fix/footer.jsp" />

<script src="${root}/resources/js/bookmark.js"></script>
<script src="${root}/resources/js/bookmarkToggle.js"></script>
</body>

</html>