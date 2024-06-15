<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>카카오 로그인</title>
</head>
<body>
<!-- Kakao 로그인 버튼 -->
<div class="text-center">
    <a href="https://kauth.kakao.com/oauth/authorize?client_id=cffd2c8562ed8ebbb1d01137aa0349f7&redirect_uri=http://localhost:8080/login/oauth2/code/kakao&response_type=code"
       th:href="@{https://kauth.kakao.com/oauth/authorize(client_id=cffd2c8562ed8ebbb1d01137aa0349f7, redirect_uri=http://localhost:8080/login/oauth2/code/kakao, response_type='code')}">
        <img src="/images/kakao_login_medium_narrow.png" alt="Kakao 로그인">
    </a>
</div>
</body>
</html>
