<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:set var="root" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${root}/resources/css/myPageCss/join_information.css">
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

        .tag-button {
            margin: 5px;
        }

        .tagButton .selected {
            background-color: dodgerblue; /* 선택된 태그 버튼의 배경색 변경 */
        }

        .type-button {
            margin: 5px;
        }

        .typeButton .selected {
            background-color: dodgerblue; /* 선택된 태그 버튼의 배경색 변경 */
        }

        .gender-button {
            margin: 5px;
        }

        .genderButton .selected {
            background-color: dodgerblue; /* 선택된 태그 버튼의 배경색 변경 */
        }


    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="${root}/resources/js/formValidation.js"></script>
</head>

<body>
<jsp:include page="/WEB-INF/views/page/fix/header.jsp" />

<div class="myPage">
    <a class="on" href="">
        <h2>내 정보</h2>
    </a>
    <a href="">
        <h2>관심 행사</h2>
    </a>
    <a href="">
        <h2>회원 탈퇴</h2>
    </a>
</div>

<form id="userForm" action="${pageContext.request.contextPath}/member/saveUser" method="post" onsubmit="return validateForm()">
    <div class="userInfo">
        <ul class="info">
            <li>
                <span>회원 유형</span><br>
                <div class="typeButton">
                    <button type="button" class="type-button" id="host" name="user_type" value="ROLE_HOST" onclick="toggleTypeSelection(this)">주최자</button>
                    <button type="button" class="type-button" id="user" name="user_type" value="ROLE_USER" onclick="toggleTypeSelection(this)">사용자</button>
                </div>
                <input type="hidden" name="user_type" id="user_type">
            </li>
            <li>
                <span>이름</span><br>
                <label>
                    <input type="text" name="user_name" value="${name}" id="user_name" readonly>
                </label>
            </li>
            <li>
                <span>닉네임</span><br>
                <div class="nickname-container">
                    <input type="text" name="user_nickName" value="${nickName}" id="user_nickName">
                    <button type="button" onclick="checkNickname()">중복 확인</button>
                </div>
                <span id="nicknameCheckResult"></span>
            </li>
            <li>
                <span>이메일</span><br>
                <label>
                    <input type="text" name="user_email" value="${email}" id="user_email" readonly>
                </label>
            </li>
            <li>
                <span>생일</span><br>
                <label>
                    <input type="date" class="user_birth" name="user_birth" value="${birthday}" id="user_birth">
                </label>
            </li>
            <li>
                <span>성별</span><br>
                <div class="genderButton">
                    <button type="button" class="gender-button" id="male" name="gender" value="male" onclick="toggleGenderSelection(this)">남</button>
                    <button type="button" class="gender-button" id="female" name="gender" value="female" onclick="toggleGenderSelection(this)">여</button>
                </div>
                <input type="hidden" name="user_gender" id="user_gender" value="${gender}">
            </li>
            <li>
                <h1>관심 태그</h1>
                <div class="tagButton">
                    <c:forEach var="tag" items="${tags}">
                        <button type="button" class="tag-button" data-tag-no="${tag.tag_no}" onclick="toggleTagSelection(this)">${tag.tag_name}</button>
                    </c:forEach>
                </div>
                <input type="hidden" name="tags" id="tags">
            </li>
        </ul>
    </div>


    <div class="updateButton">
        <button type="submit">가입하기</button>
        <button type="reset">취소</button>
    </div>
</form>

<!-- 모달 -->
<div id="myModal" class="infoModal">
    <div class="infoModal-content">
        <p>회원가입을 축하드립니다. <br>
            추가 정보를 입력해주세요</p>
        <button onclick="closeModal()">확인</button>
    </div>
</div>

<jsp:include page="/WEB-INF/views/page/fix/footer.jsp" />



</body>

</html>
