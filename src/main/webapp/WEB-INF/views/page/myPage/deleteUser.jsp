<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="root" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${root}/resources/css/myPageCss/deleteUser.css">
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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="${root}/resources/js/deleteUser.js"></script>
</head>



<body>
<jsp:include page="/WEB-INF/views/page/fix/header.jsp" />

<div class="myPage">
    <a  href="">
        <h2>내 정보</h2>
    </a>
    <a href="bookmark">
        <h2>관심 행사</h2>
    </a>
    <a class="on" href="deleteUser">
        <h2>회원 탈퇴</h2>
    </a>
</div>

<div class="deletePage">
    <form id="deleteUserForm">
        <div class="deleteUser">
            <span class="delete">회원 탈퇴</span><br>
            <span class="delete2">탈퇴 시 가입된 회원 정보가 모두 삭제됩니다.<br>
            정말 회원 탈퇴를 진행하시겠습니까?
        </span><br>
            <button class="deleteYes" type="submit">탈퇴하기</button>
            <button class="deleteNo" type="reset" onclick="window.location.href='main'">취소</button>
        </div>
    </form>
</div>

<!-- alert창 커스텀 모달 -->
<div id="customAlertModal" class="custom-alert-modal">
    <div class="custom-alert-content">
        <p id="customAlertMessage"></p>
        <button class="custom-alert-close" onclick="closeCustomAlert()">확인</button>
    </div>
</div>



<jsp:include page="/WEB-INF/views/page/fix/footer.jsp" />


</body>

</html>