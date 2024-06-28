<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="root" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${root}/resources/css/myPageCss/myPage.css">
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

        .nickname-container {
            display: flex;
            align-items: center;
        }

        .nickname-container input {
            flex: 1;
            margin-right: 10px;
        }

        .nickname-container button {
            white-space: nowrap;
        }

        #nicknameCheckResult {
            display: block;
            margin-top: 5px;
        }
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>

<body>
<jsp:include page="/WEB-INF/views/page/fix/header.jsp" />
<div class="myPage">
    <a class="on" href="">
        <h2>내 정보</h2>
    </a>
    <a href="bookmark">
        <h2>관심 행사</h2>
    </a>
    <a href="deleteUser">
        <h2>회원 탈퇴</h2>
    </a>
</div>

<form id="userInfoForm" method="post" onsubmit="submitForm(event)">
    <div class="userInfo">
        <ul class="info">
            <li>
                <span>회원 유형</span><br>
                <div class="userType">
                    <input type="radio" id="host" name="user_type" value="ROLE_HOST" <c:if test="${user.user_type == 'ROLE_HOST'}">checked</c:if>>
                    <label for="host">주최자</label>
                    <input type="radio" id="user" name="user_type" value="ROLE_USER" <c:if test="${user.user_type == 'ROLE_USER'}">checked</c:if>>
                    <label for="user">사용자</label>
                </div>
            </li>
            <li>
                <span>이름</span><br>
                <label>
                    <input type="text" name="user_name" value="${user.user_name}" readonly>
                </label>
            </li>
            <li>
                <span>닉네임</span><br>
                <div class="nickname-container">
                    <input type="text" id="user_nickName" name="user_nickName" value="${user.user_nickname}">
                    <button type="button" onclick="checkNickname()">중복 확인</button>
                </div>
                <span id="nicknameCheckResult"></span>
            </li>
            <li>
                <span>이메일</span><br>
                <label>
                    <input type="text" name="user_email" value="${user.user_email}" readonly>
                </label>
            </li>
            <li>
                <span>생일</span><br>
                <label>
                    <input type="text" name="user_birth" value="${user.user_birth}" readonly>
                </label>
            </li>
            <li>
                <span>성별</span><br>
                <div class="userGender">
                    <input type="radio" id="male" name="user_gender" value="male" <c:if test="${user.user_gender == 'male'}">checked</c:if>>
                    <label for="male">남</label>
                    <input type="radio" id="female" name="user_gender" value="female" <c:if test="${user.user_gender == 'female'}">checked</c:if>>
                    <label for="female">여</label>
                </div>
            </li>
        </ul>
    </div>

    <div class="updateButton">
        <button type="button" id="editButton" onclick="enableEdit()">수정하기</button>
        <button type="submit" id="saveButton" style="display:none">저장하기</button>
    </div>
</form>

<!-- alert창 커스텀 모달 -->
<div id="customAlertModal" class="custom-alert-modal">
    <div class="custom-alert-content">
        <p id="customAlertMessage"></p>
        <button class="custom-alert-close" onclick="closeCustomAlert()">확인</button>
    </div>
</div>

<jsp:include page="/WEB-INF/views/page/fix/footer.jsp" />

<script src="${root}/resources/js/myPage.js"></script>
</body>
</html>
