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
    <script src="${root}/resources/js/reportEdit.js"></script>
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
    <a class="on" href="report">
        <h2>제보하기</h2>
    </a>
    <a href="together">
        <h2>동행구하기</h2>
    </a>
    <a href="free">
        <h2>자유게시판</h2>
    </a>
</div>


<div class="business">
    <form method="post" onsubmit="submitForm(event)">
        <ul class="businessList">
            <li>
                <span>제목</span>
                <label class="title">
                    <input type="text" name="report_title" placeholder="${current_report.report_title}" value="${current_report.report_title}">
                </label>
            </li>
            <li>
                <span>유형</span>
                <input type="radio" id="culture" name="event_type" value="1" checked>
                <label for="culture">문화·전통체험</label>
                <input type="radio" id="festival" name="event_type" value="2">
                <label for="festival">지역 페스티벌</label>
                <input type="radio" id="popup-store" name="event_type" value="3">
                <label for="popup-store">팝업 스토어</label>

            </li>
            <li>
                <span>행사 내용</span>
                <label class="infoTextarea">
                    <textarea name="report_content" placeholder="${current_report.report_content}" rows="10" style="resize: none">${current_report.report_content}</textarea>
                </label>
            </li>
            <li>
                <span>행사주소</span>
                <label class="addressLabel">
                    <input type="text" name="report_dist" placeholder="${current_report.report_dist}" value="${current_report.report_dist}">
                    <input type="text" name="report_subdist" placeholder="${current_report.report_subdist}" value="${current_report.report_subdist}">
                    <input type="text" name="report_location" placeholder="${current_report.report_location}" value="${current_report.report_location}">
                </label>
                <button class="searchAddress" type="button" onclick="checkPost()">주소 검색</button>
            </li>
            <li>
                <span>행사기간</span>
                <label class="dateLabel">
                    <input type="date" class="date" name="report_start" placeholder="${current_report.report_start}" value="${current_report.report_start}">
                </label>
                <p>부터</p>
                <label class="dateLabel">
                    <input type="date" class="date" name="report_end" placeholder="${current_report.report_end}" value="${current_report.report_end}">
                </label>
                <p>까지</p>
            </li>
            <li>
                <span>운영시간</span>
                <label class="infoTextarea">
                    <textarea name="open_time" placeholder="${current_report.open_time}" rows="7" style="resize: none">${current_report.open_time}</textarea>
                </label>
            </li>
            <li>
                <span>주최하는 곳</span>
                <input type="text" class="host" name="report_host" placeholder="${current_report.report_host}" value="${current_report.report_host}">
            </li>
            <li>
                <span>링크</span>
                <p>공식 홈페이지</p>
                <input type="text" class="brand_link" name="brand_link" placeholder="${current_report.brand_link}" value="${current_report.brand_link}">
                <p>공식 SNS</p>
                <input type="text" class="brand_sns" name="brand_sns" placeholder="${current_report.brand_sns}" value="${current_report.brand_sns}">
                <input type="hidden" name="report_no" value="${current_report.report_no}">

            </li>
        </ul>
        <div class="updateButton">
            <button type="submit">수정하기</button>
            <button type="reset" onclick="window.location.href='${root}/report'">취소</button>
        </div>
    </form>
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
<script src="${root}/resources/js/chatModal.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1.5.1/dist/sockjs.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/stompjs@2.3.3/lib/stomp.min.js"></script>
</body>
</html>