<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="root" value="${pageContext.request.contextPath }" />

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${root}/resources/css/boardCss/reportWrite.css">
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
    <script src="${root}/resources/js/reportDetails.js"></script>
</head>

<body>
<jsp:include page="/WEB-INF/views/page/fix/header.jsp" />

<div class="contactMenu">
    <a href="/contact">
        <h2>공지사항</h2>
    </a>
    <a href="/money">
        <h2>비즈니스 문의</h2>
    </a>
    <a class="on" href="/report">
        <h2>제보하기</h2>
    </a>
    <a href="/together">
        <h2>동행구하기</h2>
    </a>
    <a href="/free">
        <h2>자유게시판</h2>
    </a>
</div>
<c:choose>
    <%-- 만약 model에 담긴 list의 value값이 비어있다면 --%>
    <c:when test="${empty report_detail}">
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
                        <input type="text" value="${report_detail.report_title}" readonly>
                    </label>
                </li>
                <li>
                    <span>유형</span>
                    <c:choose>
                        <c:when test="${report_detail.event_type == 1}">
                            <label>
                                <input type="text" value="문화·전통체험" readonly>
                            </label>
                        </c:when>
                        <c:when test="${report_detail.event_type == 2}">
                            <label>
                                <input type="text" value="지역 페스티벌" readonly>
                            </label>
                        </c:when>
                        <c:when test="${report_detail.event_type == 3}">
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
                    <span>행사 내용</span>
                    <label class="infoTextarea">
                        <textarea name="report_content" rows="10" readonly>${report_detail.report_content}</textarea>

                    </label>
                </li>
                <li>
                    <span>주소</span>
                    <label class="addressLabel">
                        <input type="text" name="report_location" value="${report_detail.report_location}" readonly>
                    </label>
                    <button class="searchAddress" type="button">주소 검색</button>
                </li>
                <li>
                    <span>행사기간</span>
                    <label class="dateLabel">
                        <input type="date" class="date" name="report_start" value="${report_detail.report_start}" readonly>
                    </label>
                    <p>부터</p>
                    <label class="dateLabel">
                        <input type="date" class="date" name="report_end" value="${report_detail.report_end}" readonly>
                    </label>
                    <p>까지</p>
                </li>
                <li>
                    <span>주최하는 곳</span>
                    <input type="text" class="host" name="report_host" value="${report_detail.report_host}" readonly>
                </li>
                <li>
                    <span>링크</span>
                    <p>공식홈페이지</p>
                    <input type="text" class="brand_link" name="brand_link" value="${report_detail.brand_link}" readonly>
                    <p>SNS</p>
                    <input type="text" class="brand_sns" name="brand_sns" value="${report_detail.brand_sns}" readonly>

                </li>
            </ul>
        </div>
        <%--        삭제버튼--%>
        <div id="deleteButton"></div>
        <%--        수정버튼--%>
        <div id="editButton"></div>
    </c:otherwise>
</c:choose>

    <div class="listButton">
        <button onclick="window.location.href='${root}/report'">목록으로</button>
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
<script>
    const boardUserId = '${report_detail.user_id}';
    const boardNo = '${report_detail.report_no}';
</script>
<script src="${root}/resources/js/chatModal.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1.5.1/dist/sockjs.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/stompjs@2.3.3/lib/stomp.min.js"></script>
</body>
</html>