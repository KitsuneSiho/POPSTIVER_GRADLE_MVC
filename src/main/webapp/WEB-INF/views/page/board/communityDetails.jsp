<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="root" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${root}/resources/css/boardCss/communityDetails.css">
    <link rel="stylesheet" href="${root}/resources/css/boardCss/chatModal.css">
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
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="${root}/resources/js/communityDetails.js"></script>
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
<%-- JSTL의 choose태그 로 조건문 실행 --%>
<div class="notice">
    <div class="noticeDetails">
<%-- 코드를 출력한다????? --%>
<%-- list를 출력한다.  --%>
<c:choose>
    <%-- 만약 model데 담긴 list의 값이 비어있다면 --%>
    <c:when test="${empty community}">
        <%-- 아래의 메시지를 출력한다. --%>
        <h1>본 요청은 정상적이지 않으며, 해킹의 가능성이 있어 당신의 IP는 경찰에 고발조치 되었습니다.</h1>

    </c:when>
    <%-- 그렇지 않으면  --%>
    <c:otherwise>
        <%-- 아래 내용을 출력한다. --%>
        <h1>${community.board_title}</h1>
        <div class="writerDate">
            <p>작성자 : ${community.user_name}<br>
                    ${community.board_post_date}</p>
            <p>조회수 : ${community.board_views}</p>
        </div>
        <div class="noticeText">
            <p>${community.board_content}</p>
                <%-- 이미지가 있는 경우에만 출력 --%>
            <img src="${root}${community.board_attachment}" alt="첨부 이미지">
        </div>

        <div class="listButton">
            <button onclick="window.location.href='${root}/free'">목록</button>
            <div id="deleteButton"></div>
            <div id="editButton"></div>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <div class="write">
                    <button class="writeButton" onclick="window.location.href='/deleteCommunity/${community.board_no}'">
                        <img src="${root}/resources/asset/글쓰기.svg" alt="">
                        [관리자]삭제</button>
                </div>
            </sec:authorize>
        </div>

    </c:otherwise>
</c:choose>



    </div>

</div>

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

<script src="${root}/resources/js/contact.js"></script>
<script src="${root}/resources/js/chatModal.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1.5.1/dist/sockjs.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/stompjs@2.3.3/lib/stomp.min.js"></script>




<script>
    const boardUserId = '${community.user_id}';
    const boardNo = '${community.board_no}';
</script>
</body>
</html>
