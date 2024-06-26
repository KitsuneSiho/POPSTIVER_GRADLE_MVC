<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="root" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${root}/resources/css/boardCss/freeWrite.css">
    <link rel="stylesheet" href="${root}/resources/css/boardCss/chatModal.css">
    <title>Free Write</title>
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
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>

        $(document).ready(function() {
            getUserInfoAndSetUserId();
        });

        function getUserInfoAndSetUserId() {
            $.ajax({
                type: "GET",
                url: ${root} + "/member/getUserInfo",
                success: function(response) {
                    if (response && response.user_id && response.user_nickname) {
                        // Set the user_id and user_nickname in the hidden input fields
                        $("#user_id").val(response.user_id);
                        $("#user_name").val(response.user_nickname);
                    } else {
                        console.error("사용자 정보를 가져오는 데 실패했습니다.");
                    }
                },
                error: function(xhr, status, error) {
                    console.error("사용자 정보를 가져오는 중 오류 발생: " + error);
                }
            });
        }
    </script>
</head>
<body>
<jsp:include page="/WEB-INF/views/page/fix/header.jsp" />

<div class="contactMenu">
    <a href="contact">
        <h2>공지사항</h2>
    </a>
    <a href="money">
        <h2>비즈니스 문의</h2>
    </a>
    <a href="report">
        <h2>제보하기</h2>
    </a>
    <a href="together">
        <h2>동행구하기</h2>
    </a>
    <a class="on" href="free">
        <h2>자유게시판</h2>
    </a>
</div>

<div class="business">
    <form action="${root}/communityForm" method="post" enctype="multipart/form-data">
        <ul class="businessList">
            <li>
                <span>제목</span>
                <label class="title">
                    <input type="text" name="board_title" placeholder="30자 이내로 입력해주세요">
                </label>
            </li>
            <li>
                <span>내용</span>
                <label class="infoTextarea">
                    <textarea name="board_content" placeholder="자유롭게 작성해주세요" rows="10"></textarea>
                </label>
            </li>
            <li>
                <span>사진</span>
                <input type="file" name="board_attachment" class="attachment" alt="">
            </li>
            <!-- Hidden inputs for user_id and user_nickname -->
            <input type="hidden" id="user_id" name="user_id" value="">
            <input type="hidden" id="user_name" name="user_name" value="">
            <input type="hidden" id="views" name="views" value="0">

        </ul>
        <div class="updateButton">
            <button type="submit">등록하기</button>
            <button type="reset">취소</button>
        </div>
    </form>
    <img src="${root}/resources/asset/채팅버튼.svg" id="chatButton" class="chatButton" alt="">


    <div id="chatModal" class="chatModal">
        <div class="chatModalContent">
            <span class="closeChatModal">&times;</span>
            <h2>1:1 채팅</h2>
            <div class="chatBox">
                <!-- Chat messages will go here -->
            </div>
            <label for="chatInput"></label><input type="text" id="chatInput" placeholder="메시지를 입력해주세요" />
            <button id="sendChatButton">보내기</button>
        </div>
    </div>

    <jsp:include page="/WEB-INF/views/page/fix/footer.jsp" />

    <script src="${root}/resources/js/chatModal.js"></script>
</body>

</html>
