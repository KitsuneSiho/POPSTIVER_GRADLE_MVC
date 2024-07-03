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

        .tag-button {
            margin: 5px;
        }

        .tagButton .selected {
            background-color: dodgerblue; /* 선택된 태그 버튼의 배경색 변경 */
        }

        .tag-button.active {
            cursor: pointer;
        }

    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script>
        function toggleTagSelection(button) {
            button.classList.toggle('selected');
            console.log('Tag selected:', button.getAttribute('data-tag-no')); // 태그 선택 이벤트 확인용 로그
        }

        function setTags() {
            document.querySelectorAll('.tag-button').forEach(button => {
                button.addEventListener('click', function() {
                    this.classList.toggle('selected');
                    console.log(`Button ${button.getAttribute('data-tag-no')} clicked`); // 클릭 이벤트 확인용 로그
                });
            });
        }

        function saveUserTags(event) {
            event.preventDefault(); // 폼의 기본 제출 방지
            const selectedTags = Array.from(document.querySelectorAll('.tag-button.selected')).map(button => button.getAttribute('data-tag-no'));
            const tagsField = document.getElementById('tags');
            tagsField.value = selectedTags.join(',');

            const form = document.getElementById('userInfoForm');
            const formData = new FormData(form);
            const data = Object.fromEntries(formData.entries());

            fetch('/member/updateUser', {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            })
                .then(response => {
                    if (response.ok) {
                        alert('내 정보 업데이트에 성공했습니다.');
                        window.location.reload(); // 페이지 새로고침
                    } else {
                        return response.json().then(err => {
                            throw new Error(err.message || '내 정보 업데이트에 실패했습니다.');
                        });
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                    alert(error.message);
                });
        }

        document.addEventListener('DOMContentLoaded', function() {
            // 선택된 태그를 표시하기 위해 selected 클래스를 추가
            const selectedTagIds = ${selectedTagIds};  // 모델에서 제공된 선택된 태그 ID 목록
            document.querySelectorAll('.tag-button').forEach(button => {
                if (selectedTagIds.includes(parseInt(button.getAttribute('data-tag-no')))) {
                    button.classList.add('selected');
                }
            });
        });

        function enableEdit() {
            document.querySelectorAll('.tag-button').forEach(button => {
                button.classList.add('active'); // 태그 버튼 활성화
                button.style.cursor = 'pointer'; // 커서 변경
            });

            // 닉네임 필드 활성화
            document.getElementById('user_nickName').removeAttribute('readonly');

            document.getElementById('editButton').style.display = 'none';
            document.getElementById('saveButton').style.display = 'block';
        }
    </script>
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

<form id="userInfoForm" method="post" onsubmit="saveUserTags(event)">
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
                    <input type="text" id="user_nickName" name="user_nickName" value="${user.user_nickname}" readonly>
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
                    <input type="date" class="birthday" name="user_birth" value="${user.user_birth}" readonly>
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
