<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>로그인 성공</title>
</head>
<body>
<h2>로그인 성공!</h2>
<h3>사용자 정보</h3>
<p>사용자 ID: ${userId}</p>
<p>닉네임: ${userNickname}</p>
<p>프로필 이미지: ${userProfileImage}</p>
<p>이메일: ${userEmail}</p>
<p>이름: ${userName}</p>
<p>성별: ${userGender}</p>
<p>연령대: ${userAgeRange}</p>
<p>생일: ${userBirthday}</p>
<p>출생 연도: ${userBirthyear}</p>

</body>
</html>
