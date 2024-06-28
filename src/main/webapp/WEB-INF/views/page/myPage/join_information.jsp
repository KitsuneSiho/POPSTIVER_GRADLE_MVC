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

        .tagButton button {
            margin: 5px;
        }
        .selected {
            background-color: yellow; /* 선택된 태그 버튼의 배경색 변경 */
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

<form action="${pageContext.request.contextPath}/member/saveUser" method="post" onsubmit="return validateForm()">
    <div class="userInfo">
        <ul class="info">
            <li>
                <span>회원 유형</span><br>
                <div class="userType">
                    <input type="radio" id="host" name="user_type" value="ROLE_HOST">
                    <label for="host">주최자</label>
                    <input type="radio" id="user" name="user_type" value="ROLE_USER" checked>
                    <label for="user">사용자</label>
                </div>
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
                    <input type="text" name="user_birth" id="user_birth" placeholder="YYYYMMDD" maxlength="8">
                </label>
            </li>
            <li>
                <span>성별</span><br>
                <div class="userGender">
                    <input type="radio" id="male" name="user_gender" value="male" ${gender == 'M' ? 'checked' : ''}>
                    <label for="male">남</label>
                    <input type="radio" id="female" name="user_gender" value="female" ${gender == 'F' ? 'checked' : ''}>
                    <label for="female">여</label>
                </div>
            </li>
            <li>
                <h1>관심 태그</h1>
                <div class="tagButton">
                    <c:forEach var="tag" items="${tags}">
                        <button type="button" class="tag-button" data-tag-no="${tag.tagNo}">${tag.tagName}</button>
                    </c:forEach>
                </div>
                <button type="button" onclick="saveUserTags()">저장하기</button>

                <script>
                    function saveUserTags() {
                        const selectedTags = Array.from(document.querySelectorAll('.tag-button.selected')).map(button => button.getAttribute('data-tag-no'));

                        fetch('/userTags/save', {
                            method: 'POST',
                            headers: {
                                'Content-Type': 'application/json'
                            },
                            body: JSON.stringify({ tags: selectedTags })
                        })
                            .then(response => response.json())
                            .then(data => alert(data.message))
                            .catch(error => console.error('Error:', error));
                    }

                    document.querySelectorAll('.tag-button').forEach(button => {
                        button.addEventListener('click', function() {
                            this.classList.toggle('selected');
                        });
                    });
                </script>
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
