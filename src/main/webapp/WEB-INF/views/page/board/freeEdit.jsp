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
    <title>Free Edit</title>
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
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="${root}/resources/js/freeEdit.js"></script>

</head>

<body>
<jsp:include page="/WEB-INF/views/page/fix/header.jsp" />

<div class="contactMenu">
    <a href="${root}/contact">
        <h2>공지사항</h2>
    </a>
    <a href="${root}/money">
        <h2>비즈니스 문의</h2>
    </a>
    <a href="${root}/report">
        <h2>제보하기</h2>
    </a>
    <a href="${root}/together">
        <h2>동행구하기</h2>
    </a>
    <a class="on" href="${root}/free">
        <h2>자유게시판</h2>
    </a>
</div>


<div class="business">
    <form onsubmit="submitForm(event)" method="post" enctype="multipart/form-data">
        <ul class="businessList">
            <li>
                <span>제목</span>
                <label class="title">
                    <input type="text" name="board_title" placeholder="${current_community.board_title}" value="${current_community.board_title}">
                </label>
            </li>
            <li>
                <span>내용</span>
                <label class="infoTextarea">
                    <textarea name="board_content" placeholder="${current_community.board_content}" rows="10" style="resize: none">${current_community.board_content}</textarea>
                </label>
            </li>
            <li>
                <span>사진</span>
                <input type="file" name="board_attachment" class="attachment" alt="" value="${current_community.board_attachment}">
            </li>
            <!-- Hidden inputs for user_id and user_nickname -->
            <input type="hidden" id="user_id" name="user_id" value="">
            <input type="hidden" id="user_name" name="user_name" value="">
            <input type="hidden" id="board_views" name="board_views" value="0">
            <input type="hidden" id="board_no" name="board_no" value="${current_community.board_no}">

        </ul>
        <div class="updateButton">
            <button type="submit">수정하기</button>
            <button type="reset" onclick="window.location.href='${root}/free'">취소</button>
        </div>
    </form>

</div>




<jsp:include page="/WEB-INF/views/page/fix/footer.jsp" />

<script src="${root}/resources/js/chatModal.js"></script>
</body>

</html>