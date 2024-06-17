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
<div class="text-center">
    <a href="https://accounts.google.com/o/oauth2/v2/auth?client_id=936690125897-3ckd20rldril6je2gn3p6575la97vhj9.apps.googleusercontent.com&redirect_uri=http://localhost:8080/auth/google/callback&response_type=code&scope=email%20profile%20openid">
        <img src="/images/google_login_button.png" alt="Google 로그인">
    </a>
</div>

<div class="text-center">
    <a href="https://nid.naver.com/oauth2.0/authorize?client_id=jUYpMer5eMaFflJnxZX6&redirect_uri=http://localhost:8080/auth/naver/callback&response_type=code&scope=name%20email%20profile_image"
       th:href="@{https://nid.naver.com/oauth2.0/authorize(client_id='jUYpMer5eMaFflJnxZX6', redirect_uri='http://localhost:8080/auth/naver/callback', response_type='code', scope='name%20email%20profile_image')}">
        <img src="/images/naver_login_button.png" alt="Naver 로그인">
    </a>
</div>

</body>
</html>
