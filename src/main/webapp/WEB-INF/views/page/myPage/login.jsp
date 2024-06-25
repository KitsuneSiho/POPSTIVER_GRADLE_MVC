<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="root" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html lang="en">


<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${root}/resources/css/myPageCss/login.css">
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


<div class="loginTitle">
    <h1>간편 로그인</h1>
    <p>아이디 / 비밀번호 입력할 필요 없어요!<br>
        SNS 아이디로 빠르게 로그인 / 회원가입 하세요 :)
    </p>
</div>

<div class="nkgloginButton">
    <div class="naverLogin">
            <button type="button" onclick="window.location.href='${root}/oauth2/authorization/naver'">
                <img src="${root}/resources/asset/네이버아이콘.png" alt="">
                <span>네이버 로그인 / 회원가입</span>
            </button>
    </div>

    <div class="kakaoLogin">
        <button type="button" onclick="window.location.href='${root}/oauth2/authorization/kakao'">
            <img src="${root}/resources/asset/카카오아이콘.png" alt="">
            <span>카카오 로그인 / 회원가입</span>
        </button>
    </div>

    <div class="googleLogin">
        <button type="button" onclick="window.location.href='${root}/oauth2/authorization/google'">
            <img src="${root}/resources/asset/구글아이콘.png" alt="">
            <span>구글 로그인 / 회원가입</span>
        </button>
    </div>

</div>


<jsp:include page="/WEB-INF/views/page/fix/footer.jsp" />
<script src="${root}/resources/js/loginApi.js"></script>
</body>

</html>
