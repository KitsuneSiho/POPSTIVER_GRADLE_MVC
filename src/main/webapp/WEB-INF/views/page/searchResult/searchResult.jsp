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
    <link rel="stylesheet" href="${root}/resources/css/searchResultCss/searchResult.css">
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
        <div class="searchResultTitle">
            <h1>' ${param.keyword} ' 검색 결과</h1>
        </div>

        <c:set var="hasOngoing" value="false" />
        <c:set var="hasUpcoming" value="false" />
        <c:set var="hasEnded" value="false" />

        <div class="searchListOpen" id="ongoingSection" onclick="toggleSearchList(this)">
            <p>진행 중</p>
            <img src="${root}/resources/asset/화살표.svg" class="arrow" alt="화살표">
        </div>
        <div class="popupFestivalInfo" id="ongoingContent">
            <div class="carousel">
                <div class="carousel-content" id="carousel-content">
                    <c:forEach var="result" items="${results}">
                        <c:if test="${today >= result.start_date && today <= result.end_date}">
                            <c:set var="hasOngoing" value="true" />
                            <div class="card">
                                <div class="card-content" data-eventtype="${result.event_type}" data-eventno="${result.event_no}">
                                    <!-- 포스터 클릭 시 상세 페이지로 이동 -->
                                    <a href="${root}/${result.event_type == '3' ? 'popup_Details' : 'festival_Details'}/${result.event_no}">
                                        <img src="<c:out value="${result.attachment}" />" alt="포스터 이미지"/>
                                    </a>

                                    <img src="${root}/resources/asset/${isLiked ? '좋아요' : '아니좋아요'}.svg"
                                         class="bookmark"
                                         alt=""
                                         data-event-no="${result.event_no}"
                                         data-event-type="${result.event_type}">
                                    <span class="like-count">${likeCount}</span>
                                    <!-- 제목 클릭 시 상세 페이지로 이동 -->
                                    <h3>
                                        <a href="${root}/${result.event_type == '3' ? 'popup_Details' : 'festival_Details'}/${result.event_no}">
                                            <c:out value="${result.title}" />
                                        </a>
                                    </h3>
                                    <p>
                                        <img src="${root}/resources/asset/위치표시.svg" class="cardAddress" alt="">
                                        <c:out value="${result.location}" />
                                    </p>
                                    <p>
                                        <img src="${root}/resources/asset/날짜.svg" class="cardDate" alt="">
                                        <c:out value="${result.start_date}" /> - <c:out value="${result.end_date}" />
                                    </p>
                                    <input type="hidden" id="user_id" name="user_id" value="">
                                    <input type="hidden" id="user_name" name="user_name" value="">
                                </div>
                            </div>
                        </c:if>
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
                    <c:forEach var="result" items="${results}">
                        <c:if test="${today < result.start_date}">
                            <c:set var="hasUpcoming" value="true" />
                            <div class="card">
                                <div class="card-content">
                                    <!-- 포스터 클릭 시 상세 페이지로 이동 -->
                                    <a href="${root}/${result.event_type == '3' ? 'popup_Details' : 'festival_Details'}/${result.event_no}">
                                        <img src="<c:out value="${result.attachment}" />" alt="포스터 이미지"/>
                                    </a>
                                    <img src="${root}/resources/asset/${isLiked ? '좋아요' : '아니좋아요'}.svg"
                                         class="bookmark"
                                         alt=""
                                         data-event-no="${result.event_no}"
                                         data-event-type="${result.event_type}">
                                    <span class="like-count">${likeCount}</span>
                                    <!-- 제목 클릭 시 상세 페이지로 이동 -->
                                    <h3>
                                        <a href="${root}/${result.event_type == '3' ? 'popup_Details' : 'festival_Details'}/${result.event_no}">
                                            <c:out value="${result.title}" />
                                        </a>
                                    </h3>
                                    <p>
                                        <img src="${root}/resources/asset/위치표시.svg" class="cardAddress" alt="">
                                        <c:out value="${result.location}" />
                                    </p>
                                    <p>
                                        <img src="${root}/resources/asset/날짜.svg" class="cardDate" alt="">
                                        <c:out value="${result.start_date}" /> - <c:out value="${result.end_date}" />
                                    </p>
                                </div>
                            </div>
                        </c:if>
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
                    <c:forEach var="result" items="${results}">
                        <c:if test="${today > result.end_date}">
                            <c:set var="hasEnded" value="true" />
                            <div class="card">
                                <div class="card-content">
                                    <!-- 포스터 클릭 시 상세 페이지로 이동 -->
                                    <a href="${root}/${result.event_type == '3' ? 'popup_Details' : 'festival_Details'}/${result.event_no}">
                                        <img src="<c:out value="${result.attachment}" />" alt="포스터 이미지"/>
                                    </a>
                                    <img src="${root}/resources/asset/${isLiked ? '좋아요' : '아니좋아요'}.svg"
                                         class="bookmark"
                                         alt=""
                                         data-event-no="${result.event_no}"
                                         data-event-type="${result.event_type}">
                                    <span class="like-count">${likeCount}</span>
                                    <!-- 제목 클릭 시 상세 페이지로 이동 -->
                                    <h3>
                                        <a href="${root}/${result.event_type == '3' ? 'popup_Details' : 'festival_Details'}/${result.event_no}">
                                            <c:out value="${result.title}" />
                                        </a>
                                    </h3>
                                    <p>
                                        <img src="${root}/resources/asset/위치표시.svg" class="cardAddress" alt="">
                                        <c:out value="${result.location}" />
                                    </p>
                                    <p>
                                        <img src="${root}/resources/asset/날짜.svg" class="cardDate" alt="">
                                        <c:out value="${result.start_date}" /> - <c:out value="${result.end_date}" />
                                    </p>
                                </div>
                            </div>
                        </c:if>
                    </c:forEach>
                </div>
            </div>
        </div>
    </article>
</div>

<jsp:include page="/WEB-INF/views/page/fix/footer.jsp" />
<script>
    var hasOngoing = ${hasOngoing};
    var hasUpcoming = ${hasUpcoming};
    var hasEnded = ${hasEnded};
</script>
<script src="${root}/resources/js/searchResult.js"></script>
<script src="${root}/resources/js/bookmarkToggle.js"></script>

<%--                    <!--좋아요 기능-->--%>
<%--<script>--%>
<%--    function toggleLike(event_no, event_type, element) {--%>
<%--        fetch('${root}/api/like/toggle', {--%>
<%--            method: 'POST',--%>
<%--            headers: {--%>
<%--                'Content-Type': 'application/json',--%>
<%--            },--%>
<%--            body: JSON.stringify({--%>
<%--                event_no: event_no,--%>
<%--                event_type: event_type--%>
<%--            }),--%>
<%--        })--%>
<%--            .then(response => response.json())--%>
<%--            .then(data => {--%>
<%--                if (data.isLiked) {--%>
<%--                    element.src = "${root}/resources/asset/좋아요.svg";--%>
<%--                } else {--%>
<%--                    element.src = "${root}/resources/asset/아니좋아요.svg";--%>
<%--                }--%>
<%--            })--%>
<%--            .catch((error) => {--%>
<%--                console.error('Error:', error);--%>
<%--            });--%>
<%--    }--%>
<%--</script>--%>

</body>

</html>