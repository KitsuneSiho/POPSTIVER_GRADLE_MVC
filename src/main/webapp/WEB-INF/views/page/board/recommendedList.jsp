<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="root" value="${pageContext.request.contextPath }" />

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${root}/resources/css/boardCss/recommended.css">
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
        <div class="searchResultTitle">
            <h1>추천된 축제 및 팝업</h1>
        </div>

        <div class="searchListOpen" id="festivalSection" onclick="toggleSearchList(this)">
            <p>축제 추천</p>
            <img src="${root}/resources/asset/화살표.svg" class="arrow" alt="화살표">
        </div>
        <div class="popupFestivalInfo" id="festivalContent">
            <div class="carousel">
                <div class="carousel-content" id="carousel-content">
                    <c:forEach var="festival" items="${festivals}">
                        <div class="card">
                            <div class="card-content">
                                <a href="${root}/festival_Details/${festival.festival_no}">
                                    <img src="<c:out value="${festival.festival_attachment}" />" alt="포스터 이미지"/>
                                </a>
                                <img src="${root}/resources/asset/좋아요.svg" class="bookmark" alt="">
                                <h3>
                                    <a href="${root}/festival_Details/${festival.festival_no}">
                                        <c:out value="${festival.festival_title}" />
                                    </a>
                                </h3>
                                <p>
                                    <img src="${root}/resources/asset/위치표시.svg" class="cardAddress" alt="">
                                    <c:out value="${festival.festival_location}" />
                                </p>
                                <p>
                                    <img src="${root}/resources/asset/날짜.svg" class="cardDate" alt="">
                                    <c:out value="${festival.festival_start}" /> - <c:out value="${festival.festival_end}" />
                                </p>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>

        <div class="searchListOpen" id="popupSection" onclick="toggleSearchList(this)">
            <p>팝업 추천</p>
            <img src="${root}/resources/asset/화살표.svg" class="arrow" alt="화살표">
        </div>
        <div class="popupFestivalInfo" id="popupContent">
            <div class="carousel">
                <div class="carousel-content" id="carousel-content">
                    <c:forEach var="popup" items="${popups}">
                        <div class="card">
                            <div class="card-content">
                                <a href="${root}/popup_Details/${popup.popup_no}">
                                    <img src="<c:out value="${popup.popup_attachment}" />" alt="포스터 이미지"/>
                                </a>
                                <img src="${root}/resources/asset/좋아요.svg" class="bookmark" alt="">
                                <h3>
                                    <a href="${root}/popup_Details/${popup.popup_no}">
                                        <c:out value="${popup.popup_title}" />
                                    </a>
                                </h3>
                                <p>
                                    <img src="${root}/resources/asset/위치표시.svg" class="cardAddress" alt="">
                                    <c:out value="${popup.popup_location}" />
                                </p>
                                <p>
                                    <img src="${root}/resources/asset/날짜.svg" class="cardDate" alt="">
                                    <c:out value="${popup.popup_start}" /> - <c:out value="${popup.popup_end}" />
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
<script>
    function toggleSearchList(section) {
        var content = section.nextElementSibling;
        if (content.style.display === "none" || content.style.display === "") {
            content.style.display = "block";
        } else {
            content.style.display = "none";
        }
    }
</script>
<script src="${root}/resources/js/bookmarkToggle.js"></script>
<script src="${root}/resources/js/searchResult.js"></script>
</body>

</html>
