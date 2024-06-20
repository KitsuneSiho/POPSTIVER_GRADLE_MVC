<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:set var="root" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${root}/resources/css/myPageCss/myPage.css">
    <link rel="stylesheet" href="${root}/resources/css/fixCss/header.css">
    <link rel="stylesheet" href="${root}/resources/css/fixCss/footer.css">
    <link rel="stylesheet" href="${root}/resources/css/fixCss/menuModal.css">
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
    <script>
        $(document).ready(function() {
            // 문서 로드 시 사용자 정보 로드
            loadUserInfo();
        });


        function loadUserInfo() {

            $.ajax({
                type: "GET",
                url: "member/getUserInfo",
                success: function(response) {
                    // 사용자 정보가 성공적으로 로드되면 폼에 데이터 설정
                    var user = response;
                    $("input[name='user_name']").val(user.user_name);
                    $("input[name='user_email']").val(user.user_email);
                    $("input[name='user_birth']").val(user.user_birth);
                    $("input[name='user_gender'][value='" + user.user_gender + "']").prop('checked', true);
                    $("input[name='user_type'][value='" + user.user_type + "']").prop('checked', true);
                },
                error: function(xhr, status, error) {
                    console.error("사용자 정보를 가져오는 중 오류 발생: " + error);
                }
            });
        }
        // 수정하기 버튼을 클릭할 시
        function enableEdit(){
            $("#editButton").css("display", "none"); // 수정 버튼은 안 보이게
            $("#saveButton").css("display", "block"); // 저장 버튼은 보이게

            // 입력 필드들의 readonly 속성 해제
            $("input[type='text']").prop("readonly", false);
            $("input[type='date']").prop("readonly", false);

            // 라디오 버튼들의 disabled 속성 해제
            $("input[type='radio']").prop("disabled", false);
        }

        // 저장하기 버튼을 누르면
        function submitForm(event){
            // 폼 데이터 변수로 가져오기
            var userEmail = $("input[name='user_email']").val();

            event.preventDefault(); // 폼 제출 방지

            $.ajax({
                type: "put",
                url: "member/updateUser",
                contentType: 'application/json;charset=utf-8',
                // StudentAndInfo 객체를 JSON 문자열로 변환하여 전송
                data: JSON.stringify({
                    "user_email" : userEmail
                }),
                success: function(response) {
                    // 업데이트 성공 시 처리할 코드
                    alert("회원 정보가 업데이트되었습니다!");
                    // 필요한 경우 추가적인 UI 업데이트 등을 수행할 수 있음
                },
                error: function(xhr, status, error) {
                    // 실패 시 처리할 코드
                    alert("회원 정보 업데이트 중 오류가 발생했습니다.");
                }
            });

            $("#editButton").css("display", "block"); // 수정 버튼은 보이게
            $("#saveButton").css("display", "none"); // 저장 버튼은 안 보이게

            // 입력 필드들의 readonly 속성 적용
            $("input[type='text']").prop("readonly", true);
            $("input[type='date']").prop("readonly", true);

        }

    </script>
</head>

<body>
<header class="mainTop">
    <div class="mainTopLogo">
        <h1><a href="main">POPSTIVER</a></h1>
    </div>

    <div class="mainTopSearch">
        <div class="mainTopSearchContainer">
            <label>
                <input type="text" placeholder="팝업스토어, 페스티벌 검색">
                <button type="submit" class="searchButton" onclick="window.location.href='searchResult'">
                    <img src="${root}/resources/asset/메인검색창검색버튼.svg" alt="">
                </button>
            </label>
        </div>
    </div>



    <div class="mainTopButton">
        <button class="loginButton" onclick="window.location.href='login'">
            로그인
        </button>
        <button class="menuButton">
            <img src="${root}/resources/asset/메인메뉴버튼.svg" alt="">
        </button>
    </div>


</header>
<div id="menuModal" class="modal">
    <div class="modal-content">
        <ul>
            <li><a href="login">로그인</a></li>
            <li><a href="map">근처 행사</a></li>
            <li><a href="bookmark">관심 행사</a></li>
            <li><a href="calendar">행사 일정</a></li>
            <li><a href="contact">게시판</a></li>
        </ul>
    </div>
</div>

<div class="myPage">
    <a class="on" href="myPage">
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
                <input type="radio" id="host" name="user_type" value="1" ${user.user_type == 1 ? 'checked' : ''}>
                <label for="host">주최자</label>
                <input type="radio" id="user" name="user_type" value="2" ${user.user_type == 2 ? 'checked' : ''}>
                <label for="user">사용자</label>
            </li>
            <li>
                <span>이름</span><br>
                <input type="text" name="user_name" value="${user.user_name}" readonly>
            </li>
            <li>
                <span>이메일</span><br>
                <input type="text" name="user_email" value="${user.user_email}" readonly>
            </li>
            <li>
                <span>생일</span><br>
                <input type="text" name="user_birth" value="${user.user_birth}" readonly>
            </li>
            <li>
                <span>성별</span><br>
                <input type="radio" id="male" name="user_gender" value="male" ${user.user_gender == 'male' ? 'checked' : ''}>
                <label for="male">남</label>
                <input type="radio" id="female" name="user_gender" value="female" ${user.user_gender == 'female' ? 'checked' : ''}>
                <label for="female">여</label>
            </li>
        </ul>
    </div>
    </div>

    <div class="updateButton">
        <button type="button" id="editButton" onclick="enableEdit()">수정하기</button>
        <button type="submit" id="saveButton" style="display:none">저장하기</button>
    </div>
</form>

<footer>
    ㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇ<br>
    ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ
</footer>

<script src="${root}/resources/js/menuModal.js"></script>
</body>

</html>