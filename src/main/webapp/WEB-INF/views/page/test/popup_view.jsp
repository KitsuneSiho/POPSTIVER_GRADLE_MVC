<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import ="java.sql.*, javax.sql.*, java.io.*" %>
<%@ page import ="kr.bit.function.board.*" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>팝업스토어 리스트</title>
</head>
<body>
<h3>전체 팝업스토어 데이터</h3>
<hr>
<%-- 전체 리스트가 출력되는 테이블 생성 --%>
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
        <td width=50><p align=center>태그1</p></td>
        <td width=50><p align=center>태그2</p></td>
        <td width=50><p align=center>태그3</p></td>
        <td width=50><p align=center>태그4</p></td>
        <td width=50><p align=center>태그5</p></td>

    </tr>
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
            <c:forEach items="${list}" var="p">
                <tr>
                        <%-- 팝업 번호. a링크를 걸어 클릭시 '팝업스토어/파라메터 값(글번호)' 형식으로 보낸다. --%>
                    <td width=50><p align=center><a href="popup_Details/${p.popup_no}">${p.popup_no}</a></p></td>
                        <%-- 행사명을 출력한다. a링크를 걸어 클릭시 '팝업스토어/파라메터 값(글번호)' 형식으로 보낸다. --%>
                    <td width=50><p align=center><a href="oneviewDB/${p.popup_no}">${p.popup_title}</a></p></td>
                    <td width=50><p align=center>${p.popup_content}</p></td>
                    <td width=50><p align=center>${p.host}</p></td>
                    <td width=50><p align=center>${p.popup_location}</p></td>
                    <td width=50><p align=center>${p.popup_start}</p></td>
                    <td width=50><p align=center>${p.popup_end}</p></td>
                    <td width=50><p align=center>${p.open_time}</p></td>
                    <td width=50><p align=center>${p.popup_post_date}</p></td>
                    <td width=50><p align=center>${p.popup_attachment}</p></td>
                    <td width=50><p align=center>${p.event_type}</p></td>
                    <td width=50><p align=center>${p.like_that}</p></td>
                    <td width=50><p align=center>${p.views}</p></td>
                    <td width=50><p align=center>${p.brand_link}</p></td>
                    <td width=50><p align=center>${p.brand_sns}</p></td>
                            <td width=50><p align=center>${p.popup_tag1}</p></td>
                            <td width=50><p align=center>${p.popup_tag2}</p></td>
                            <td width=50><p align=center>${p.popup_tag3}</p></td>
                            <td width=50><p align=center>${p.popup_tag4}</p></td>
                            <td width=50><p align=center>${p.popup_tag5}</p></td>

                </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</table>

</body>
</html>
