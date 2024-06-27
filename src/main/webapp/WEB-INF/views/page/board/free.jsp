<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="root" value="${pageContext.request.contextPath }" />

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${root}/resources/css/boardCss/free.css">
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
    </style>
</head>

<body>
<% if (request.getAttribute("message") != null) { %>
<script>
    alert('<%= request.getAttribute("message") %>');
</script>
<% } %>


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

<div class="board">
    <table class="boardTable">
        <thead>
        <tr>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
        </tr>
        </thead>
        <tbody id="boardBody">
        <c:choose>
            <%-- 만약 model에 담긴 list의 value값이 비어있다면 --%>
            <c:when test="${empty list}">
                <%-- 아래의 메시지를 출력한다. --%>
                <tr>
                    <td colspan=15>
                        <spring:message code="common.listEmpty"/>
                    </td>
                </tr>
            </c:when>
            <c:otherwise>
                <%-- 그렇지 않다면 foreach문으로 list를 출력한다. --%>
                <c:forEach items="${list}" var="notice">
                    <tr>
                            <%-- 공지제목. a링크를 걸어 클릭시 '공지/파라메터 값(글번호)' 형식으로 보낸다. --%>
                        <td><p><a href="notice_Details/${notice.notice_no}">${notice.notice_title}</a></p></td>
                        <td ><p>관리자</p></td>
                        <td ><p>${notice.notice_date}</p></td>

                    </tr>
                </c:forEach>
            </c:otherwise>
        </c:choose>
        </tbody>
    </table>
</div>

<div class="write">
    <button class="writeButton" onclick="window.location.href='freeWrite'">
        <img src="${root}/resources/asset/글쓰기.svg" alt="">
        글쓰기</button>
</div>

<div class="pageNumber">
    <ul id="pageNumberList">
        <li><a class="pageOn"></a></li>
    </ul>
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

<script src="${root}/resources/js/free.js"></script>
<script src="${root}/resources/js/chatModal.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1.5.1/dist/sockjs.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/stompjs@2.3.3/lib/stomp.min.js"></script>
</body>
</html>