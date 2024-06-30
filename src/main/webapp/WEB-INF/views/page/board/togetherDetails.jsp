<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="root" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${root}/resources/css/boardCss/togetherDetails.css">
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
    <a class="on" href="${root}/together">
        <h2>동행구하기</h2>
    </a>
    <a href="${root}/free">
        <h2>자유게시판</h2>
    </a>
</div>
<c:choose>
    <%-- 만약 model에 담긴 list의 value값이 비어있다면 --%>
    <c:when test="${empty together}">
        <%-- 아래의 메시지를 출력한다. --%>
        <tr>
            <td colspan=15>
                <h1>비 정상적인 접근이 감지되었습니다.</h1>
            </td>
        </tr>
    </c:when>
    <c:otherwise>
        <div class="business">
            <ul class="businessList">
                <li>
                    <span>제목</span>
                    <label class="title">
                        <input type="text" value="${together.comp_title}" readonly>
                    </label>
                </li>
                <li>
                    <span>유형</span>
                    <c:choose>
                        <c:when test="${together.event_type == 1}">
                            <label>
                                <input type="text" value="문화·전통체험" readonly>
                            </label>
                        </c:when>
                        <c:when test="${together.event_type == 2}">
                            <label>
                                <input type="text" value="지역 페스티벌" readonly>
                            </label>
                        </c:when>
                        <c:when test="${together.event_type == 3}">
                            <label>
                                <input type="text" value="팝업 스토어" readonly>
                            </label>
                        </c:when>
                        <c:otherwise>
                            <p>알 수 없음</p>
                        </c:otherwise>
                    </c:choose>
                </li>
                <li>
                    <span>동행기간</span>
                    <label class="dateLabel">
                        <input type="date" class="date" name="comp_date" value="${together.comp_date}" readonly>
                    </label>
                </li>
                <li>
                    <span>내용</span>
                    <label class="infoTextarea">
                        <textarea name="comp_content" readonly rows="10" style="resize: none" >${together.comp_link}</textarea>
                    </label>
                </li>
                <li>
                    <span>행사링크</span>
                    <label class="link">
                        <input type="text" name="comp_link" value="${together.comp_link}" readonly>
                    </label>
                </li>
            </ul>
        </div>
    </c:otherwise>
</c:choose>
        <div class="listButton">
            <button onclick="window.location.href='${root}/together'">목록</button>
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
<%--<script src="${root}/resources/js/contact.js"></script>--%>
<script src="${root}/resources/js/chatModal.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1.5.1/dist/sockjs.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/stompjs@2.3.3/lib/stomp.min.js"></script>
</body>
</html>
