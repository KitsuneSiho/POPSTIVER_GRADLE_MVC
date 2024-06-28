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

        @font-face {
            font-family: Pre;
            src: url('${root}/resources/font/Pre.ttf');
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
            <c:when test="${empty community_list}">
                <%-- 아래의 메시지를 출력한다. --%>
                <tr>
                    <td colspan=15>
                        <spring:message code="common.listEmpty"/>
                    </td>
                </tr>
            </c:when>
            <c:otherwise>
                <%-- 그렇지 않다면 foreach문으로 list를 출력한다. --%>
                <c:forEach items="${community_list}" var="community">
                    <tr>
                        <td><p><a href="free/${community.board_no}">${community.board_title}</a></p></td>
                        <td ><p>${community.user_name}</p></td>
                        <td ><p>${community.board_post_date}</p></td>

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
<script>
    document.addEventListener('DOMContentLoaded', function() {
        // 페이지 로드 시 실행되는 초기화 함수
        setupPagination(); // 페이지네이션 설정 함수 호출
        displayPosts(1); // 초기 페이지의 게시글 표시

        // 페이지네이션 설정 함수
        function setupPagination() {
            const boardBody = document.getElementById('boardBody');
            const pageNumberList = document.getElementById('pageNumberList');
            const postsPerPage = 15; // 페이지당 게시글 수
            const postsCount = ${list.size()}; // 서버에서 가져온 게시글 수 (여기서는 임의로 ${list.size()} 사용)

            // 전체 페이지 수 계산
            const totalPages = Math.ceil(postsCount / postsPerPage);

            // 페이지네이션 목록 생성
            for (let i = 1; i <= totalPages; i++) {
                const pageItem = document.createElement('li');
                const pageLink = document.createElement('a');
                pageLink.href = "#";
                pageLink.textContent = i;
                pageLink.onclick = function() {
                    displayPosts(i); // 페이지 번호 클릭 시 해당 페이지의 게시글 표시
                    // 모든 페이지 번호 링크의 클래스 제거
                    const allPageLinks = document.querySelectorAll('.pageNumber ul li a');
                    allPageLinks.forEach(link => link.classList.remove('pageOn'));
                    // 현재 클릭된 페이지 번호 링크에 클래스 추가
                    this.classList.add('pageOn');
                };
                pageItem.appendChild(pageLink);
                pageNumberList.appendChild(pageItem);
            }
        }

        // 특정 페이지의 게시글을 화면에 표시하는 함수
        function displayPosts(page) {
            const boardBody = document.getElementById('boardBody');
            const postsPerPage = 15; // 페이지당 게시글 수
            const startIndex = (page - 1) * postsPerPage;
            const endIndex = startIndex + postsPerPage;

            // 게시글 데이터를 받아와서 표시 (서버에서 데이터를 가져오는 경우라면 AJAX 등의 방법을 사용할 수 있음)
            // 여기서는 임의의 데이터를 사용하므로 ${list}에서 자바스크립트 변수로 변환
            const posts = ${list}; // 이 부분을 실제로 서버에서 받은 데이터로 대체해야 함

            boardBody.innerHTML = ''; // 게시글 목록 초기화

            // 해당 페이지에 표시할 게시글만 선택하여 HTML에 추가
            for (let i = startIndex; i < endIndex && i < posts.length; i++) {
                const post = posts[i];
                const row = document.createElement('tr');
                row.innerHTML = `
                <td><p><a href="community_Details/${post.board_no}">${post.board_title}</a></p></td>
                <td><p>${post.user_name}</p></td>
                <td><p>${post.board_post_date}</p></td>
            `;
                boardBody.appendChild(row);
            }
        }
    });
</script>

<script src="${root}/resources/js/chatModal.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1.5.1/dist/sockjs.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/stompjs@2.3.3/lib/stomp.min.js"></script>
</body>
</html>
