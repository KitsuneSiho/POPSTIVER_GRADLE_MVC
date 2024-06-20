<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="root" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${root}/resources/css/deleteUser.css">
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
    $(document).ready(function() {
        $("#deleteUserForm").on("submit", function(event) {
            event.preventDefault(); // 기본 폼 제출 방지

            $.ajax({
                type: "DELETE",
                url: "/member/deleteUser",
                success: function(response) {
                    alert("회원 탈퇴가 완료되었습니다.");
                    window.location.href = "/main"; // 탈퇴 후 메인 페이지로 이동
                },
                error: function(xhr, status, error) {
                    alert("회원 탈퇴 중 오류가 발생했습니다.");
                }
            });
        });
    });
</script>
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
        <button class="myPageButton" onclick="window.location.href='myPage'">
            <img src="${root}/resources/asset/myPageButton.svg" alt="">
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
    <a  href="myPage">
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
            <button class="deleteNo" type="reset">취소</button>
        </div>
    </form>
</div>



<footer>
    ㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇ<br>
    ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ
</footer>

<script src="${root}/resources/js/menuModal.js"></script>


</body>

</html>