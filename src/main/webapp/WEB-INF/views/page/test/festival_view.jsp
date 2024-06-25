<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import ="java.sql.*, javax.sql.*, java.io.*" %>
<%@ page import ="kr.bit.function.board.*" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>페스티벌 리스트</title>
</head>
<body>
<h3>전체 데이터</h3>
<hr>
<%-- 전체 리스트가 출력되는 테이블 생성   --%>
<table cellspacing=1 width=600 border=1>
    <tr>
        <td width=50><p align=center>글 번호</p></td>
        <td width=50><p align=center>행사명</p></td>
        <td width=50><p align=center>내용</p></td>
        <td width=50><p align=center>주최자</p></td>
        <td width=50><p align=center>위치</p></td>
        <td width=50><p align=center>시작날짜</p></td>
        <td width=50><p align=center>종료날짜</p></td>
        <td width=50><p align=center>운영시간</p></td>
        <td width=50><p align=center>작성일</p></td>
        <td width=50><p align=center>첨부파일</p></td>
        <td width=50><p align=center>이벤트타입</p></td>
        <td width=50><p align=center>좋아요</p></td>
        <td width=50><p align=center>조회수</p></td>
        <td width=50><p align=center>공식홈피</p></td>
        <td width=50><p align=center>공식SNS</p></td>
    </tr>
    <%-- JSTL의 choose태그 로 조건문 실행 --%>
    <%-- JSTL 태크안에 주석을 다니 실행이 안되서 주석은  --%>
    <%-- 그렇지 않다면 foreach문으로 list를 출력한다.  --%>
    <%-- 제목쪽엔 a태그로 링크를 걸어주는대, URL의 뒤쪽은 파라메터 이름이다.  --%>
    <c:choose>
        <%-- 만약 model에 담긴 list의 value값이 비어있다면 --%>
        <c:when test="${empty list}">
            <%-- 아래의 메시지를 출력한다. --%>
            <tr>
                <td colspan=3>
                    <spring:message code="common.listEmpty"/>
                </td>
            </tr>
        </c:when>
        <%-- 그렇지 않다면 foreach문으로 list를 출력한다.  --%>
        <c:otherwise>
            <%-- model에 담긴 list갯수 만큼 반복해서 데이터를 출력한다. --%>
            <%-- item을 list로 한다는건 반복문에 list에 담긴 수만큼 반복한다는 것이고 --%>
            <%-- 이 list를 통해 값을 출력할 때 var를 'e'로 해서 e.데이터이름 으로 출력할 수 있게 한다. --%>
            <c:forEach items="${list}" var="f">
                <tr>
                        <%-- 게시글번호. a링크를 걸어 클릭시  '상세보기/파라메터 값(글번호)' 형식으로 보낸다 --%>
                    <td width=50><p align=center><a href="festival_Details/${f.festival_no}">${f.festival_no}</a></p></td>
                        <%-- 제목을 출력한다. a링크를 걸어 클릭시  '상세보기/파라메터 값(글번호)' 형식으로 보낸다 --%>
                    <td width=50><p align=center><a href="oneviewDB/${f.festival_no}">${f.festival_title}</a></p></td>
                    <td width=50><p align=center>${f.festival_content}</p></td>
                    <td width=50><p align=center>${f.host}</p></td>
                    <td width=50><p align=center>${f.festival_location}</p></td>
                    <td width=50><p align=center>${f.festival_start}</p></td>
                    <td width=50><p align=center>${f.festival_end}</p></td>
                    <td width=50><p align=center>${f.open_time}</p></td>
                    <td width=50><p align=center>${f.festival_post_date}</p></td>
                    <td width=50><p align=center>${f.festival_attachment}</p></td>
                    <td width=50><p align=center>${f.event_type}</p></td>
                    <td width=50><p align=center>${f.like_that}</p></td>
                    <td width=50><p align=center>${f.views}</p></td>
                    <td width=50><p align=center>${f.brand_link}</p></td>
                    <td width=50><p align=center>${f.brand_sns}</p></td>
                </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</table>

</body>
</html>