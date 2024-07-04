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

        @font-face {
            font-family: Pre;
            src: url('${root}/resources/font/Pre.ttf');
        }
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="${root}/resources/js/noticeWrite.js"></script>

</head>

<body>
<jsp:include page="/WEB-INF/views/page/fix/header.jsp" />

<div class="contactMenu">
    <a class="on" href="${root}/contact">
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
    <a href="${root}/free">
        <h2>자유게시판</h2>
    </a>
</div>


<div class="business">
    <form onsubmit="submitForm(event)" method="post" enctype="multipart/form-data">
        <ul class="businessList">
            <li>
                <span>공지사항 제목</span>
                <label class="title">
                    <input type="text" name="notice_title" placeholder="공지할 제목을 30자 이내로 작성해주세요">
                </label>
            </li>
            <li>
                <span>공지사항 내용</span>
                <label class="infoTextarea">
                    <textarea name="notice_content" placeholder="공지할 내용을 입력해주세요." rows="10" style="resize: none"></textarea>
                </label>
            </li>
            <li>
                <span>사진</span>
                <input type="file" name="board_attachment" class="attachment" alt="">
            </li>

        </ul>
        <div class="updateButton">
            <button type="submit">등록하기</button>
            <button type="reset" onclick="window.location.href='${root}/contact'">취소</button>
        </div>
    </form>

</div>



<jsp:include page="/WEB-INF/views/page/fix/footer.jsp" />

<script src="${root}/resources/js/chatModal.js"></script>
</body>

</html>