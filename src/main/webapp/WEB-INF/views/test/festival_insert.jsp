<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import ="java.sql.*, javax.sql.*, java.io.*" %>
<%@ page import ="kr.bit.function.board.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>festival_insert</title>
</head>
<body>
<h3>데이터 Insert</h3>
<hr>
<!-- 기능은 이미 실행이 되고(데이터 삽입) 해당 페이지로 이동된다.  -->
<!-- model에 저장된 msg란 key값을 찾아 그 value값을 출력한다.(메시지) -->
${msg}
</body>
</html>