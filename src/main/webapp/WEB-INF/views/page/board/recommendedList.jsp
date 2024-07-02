<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="root" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${root}/resources/css/boardCss/recommended.css">
    <title>추천 결과</title>
</head>


<body>
<jsp:include page="/WEB-INF/views/page/fix/header.jsp" />

<div class="recommendedList">
    <article>
        <div class="recommendedTitle">
            <h1>관심 태그 기반 축제 및 팝업 추천</h1>
        </div>

        <!-- 사용자 관심 태그 기반 축제 추천 -->
        <div class="recommendedListOpen" id="userFestivalSection" onclick="toggleRecommendedList(this)">
            <p>축제 추천</p>
            <img src="${root}/resources/asset/화살표.svg" class="arrow" alt="화살표">
        </div>
        <div class="popupFestivalInfo" id="userFestivalContent">
            <div class="carousel">
                <div class="carousel-content">
                    <c:forEach var="festival" items="${user_festivals}">
                        <div class="card">
                            <div class="card-content">
                                <a href="${root}/festival_Details/${festival.festival_no}">
                                    <img src="${festival.festival_attachment}" alt="포스터 이미지"/>
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
                                    <span class="date-field"><c:out value="${festival.festival_start}" /></span>  ~
                                    <span class="date-field"><c:out value="${festival.festival_end}" /></span>
                                </p>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>

        <!-- 사용자 관심 태그 기반 팝업 추천 -->
        <div class="recommendedListOpen" id="userPopupSection" onclick="toggleRecommendedList(this)">
            <p>팝업 추천</p>
            <img src="${root}/resources/asset/화살표.svg" class="arrow" alt="화살표">
        </div>
        <div class="popupFestivalInfo" id="userPopupContent">
            <div class="carousel">
                <div class="carousel-content">
                    <c:forEach var="popup" items="${user_popups}">
                        <div class="card">
                            <div class="card-content">
                                <a href="${root}/popup_Details/${popup.popup_no}">
                                    <img src="${popup.popup_attachment}" alt="포스터 이미지"/>
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
                                    <span class="date-field"><c:out value="${popup.popup_start}" /></span>  ~
                                    <span class="date-field"><c:out value="${popup.popup_end}" /></span>
                                </p>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>


        <!-- 비슷한 취향을 가진 다른 사용자의 태그 기반 축제 추천 -->
        <div class="recommendedListOpen" id="similarFestivalSection" onclick="toggleRecommendedList(this)">
            <p>비슷한 취향을 가진 다른 사용자의 관심 축제</p>
            <img src="${root}/resources/asset/화살표.svg" class="arrow" alt="화살표">
        </div>
        <div class="popupFestivalInfo" id="similarFestivalContent">
            <div class="carousel">
                <div class="carousel-content">
                    <c:forEach var="festival" items="${similar_festivals}">
                        <div class="card">
                            <div class="card-content">
                                <a href="${root}/festival_Details/${festival.festival_no}">
                                    <img src="${festival.festival_attachment}" alt="포스터 이미지"/>
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
                                    <span class="date-field"><c:out value="${festival.festival_start}" /></span>  ~
                                    <span class="date-field"><c:out value="${festival.festival_end}" /></span>
                                </p>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>

        <!-- 비슷한 취향을 가진 다른 사용자의 태그 기반 팝업 추천 -->
        <div class="recommendedListOpen" id="similarPopupSection" onclick="toggleRecommendedList(this)">
            <p>비슷한 취향을 가진 다른 사용자의 관심 팝업</p>
            <img src="${root}/resources/asset/화살표.svg" class="arrow" alt="화살표">
        </div>
        <div class="popupFestivalInfo" id="similarPopupContent">
            <div class="carousel">
                <div class="carousel-content">
                    <c:forEach var="popup" items="${similar_popups}">
                        <div class="card">
                            <div class="card-content">
                                <a href="${root}/popup_Details/${popup.popup_no}">
                                    <img src="${popup.popup_attachment}" alt="포스터 이미지"/>
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
                                    <span class="date-field"><c:out value="${popup.popup_start}" /></span>  ~
                                    <span class="date-field"><c:out value="${popup.popup_end}" /></span>
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
    // 현재 날짜를 yyyy-mm-dd 형식으로 포맷팅하는 함수
    function formatDateString(dateString) {
        const date = new Date(dateString);
        const year = date.getFullYear();
        const month = String(date.getMonth() + 1).padStart(2, '0'); // 월은 0부터 시작하므로 +1
        const day = String(date.getDate()).padStart(2, '0');
        return year + "-" + month + "-" + day;
    }

    // 페이지 로드 시 실행
    document.addEventListener('DOMContentLoaded', (event) => {
        document.querySelectorAll('.date-field').forEach((element) => {
            const originalDate = element.textContent.trim();
            element.textContent = formatDateString(originalDate);
        });
    });

    function toggleRecommendedList(section) {
        var content = section.nextElementSibling;
        if (content.style.display === "none" || content.style.display === "") {
            content.style.display = "block";
        } else {
            content.style.display = "none";
        }
    }
</script>
</body>
</html>