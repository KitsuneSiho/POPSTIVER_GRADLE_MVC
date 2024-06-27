<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:set var="root" value="${pageContext.request.contextPath }" />

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${root}/resources/css/fixCss/footer.css">
    <title>POPSTIVER</title>
</head>
<body>
<div class="wrapper">
    <div class="content">
        <!-- 페이지의 실제 콘텐츠 -->
    </div>
    <footer class="footer">
        (주)POPSTIVER<br>
        주소 : 서울 서초구 강남대로<br>
        대표전화 : (02)123-4567 FAX : (02)123-4567<br>
        abc@postiver.co.kr<br>
        Copyright 2024. POPSTVIER Co.,Ltd. All rights reserved
    </footer>
</div>
</body>
</html>
