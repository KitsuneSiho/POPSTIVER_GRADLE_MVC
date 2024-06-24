<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="root" value="${pageContext.request.contextPath }" />

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${root}/resources/css/etcCss/calendar.css">
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

<body>
<jsp:include page="/WEB-INF/views/page/fix/header.jsp" />
<div class="calenderTitle">
    <h1>행사 일정</h1>
</div>

<div id='calender'></div>
<dialog>
    <button class="close-button">&times;</button>
    <div>제목 테스트</div>
</dialog>


<jsp:include page="/WEB-INF/views/page/fix/footer.jsp" />
<script src="${root}/resources/js/calendar.js"></script>
<script src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.14/index.global.min.js'></script>
<script src='https://cdn.jsdelivr.net/npm/@fullcalendar/google-calendar@6.1.14/index.global.min.js'></script>


</body>

</html>