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
    </style>
</head>

<body>
<jsp:include page="/WEB-INF/views/page/fix/header.jsp" />

<div class="searchList">
    <article>
        <c:set var="now" value="<%=new java.util.Date()%>" />
        <fmt:formatDate var="today" value="${now}" pattern="yyyy-MM-dd" />

        <div class="searchListOpen" onclick="toggleSearchList(this)">
            <p>진행 중</p>
            <img src="${root}/resources/asset/화살표.svg" class="arrow" alt="화살표">
        </div>
        <div class="popupFestivalInfo">
            <div class="carousel">
                <div class="carousel-content" id="carousel-content">
                    <c:forEach var="result" items="${results}">
                        <c:if test="${today >= result.start_date && today <= result.end_date}">
                            <div class="card">
                                <div class="card-content">
                                    <img src="<c:out value="${result.attachment}" />" />
                                    <h3><c:out value="${result.title}" /></h3>
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

        <div class="searchListOpen" onclick="toggleSearchList(this)">
            <p>오픈 예정</p>
            <img src="${root}/resources/asset/화살표.svg" class="arrow" alt="화살표">
        </div>
        <div class="popupFestivalInfo">
            <div class="carousel">
                <div class="carousel-content" id="carousel-content">
                    <c:forEach var="result" items="${results}">
                        <c:if test="${today < result.start_date}">
                            <div class="card">
                                <div class="card-content">
                                    <img src="<c:out value="${result.attachment}" />" />
                                    <h3><c:out value="${result.title}" /></h3>
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

        <div class="searchListOpen" onclick="toggleSearchList(this)">
            <p>종료</p>
            <img src="${root}/resources/asset/화살표.svg" class="arrow" alt="화살표">
        </div>
        <div class="popupFestivalInfo">
            <div class="carousel">
                <div class="carousel-content" id="carousel-content">
                    <c:forEach var="result" items="${results}">
                        <c:if test="${today > result.end_date}">
                            <div class="card">
                                <div class="card-content">
                                    <img src="<c:out value="${result.attachment}" />" />
                                    <h3><c:out value="${result.title}" /></h3>
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
<script src="${root}/resources/js/searchResult.js"></script>
<script src="${root}/resources/js/bookmarkToggle.js"></script>
</body>

</html>