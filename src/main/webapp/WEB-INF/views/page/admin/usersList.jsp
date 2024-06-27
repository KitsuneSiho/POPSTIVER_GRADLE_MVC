<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>회원 리스트</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="<c:url value='/css/styles.css' />">
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <!-- 사이드바 -->
        <nav class="col-md-2 d-none d-md-block bg-light sidebar">
            <jsp:include page="/WEB-INF/views/page/admin/layout/sidebar.jsp"/>
        </nav>

        <!-- 메인 콘텐츠 -->
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4">
            <jsp:include page="/WEB-INF/views/page/admin/layout/header.jsp"/>
            <h2>회원 리스트</h2>
            <div class="container">
                <!-- 회원 리스트 내용 -->
                <div class="table-responsive">
                    <table class="table table-striped table-sm">
                        <thead>
                        <tr>
                            <th>User No</th>
                            <th>User Type</th>
                            <th>User ID</th>
                            <th>User Name</th>
                            <th>Email</th>
                            <th>Birth</th>
                            <th>Gender</th>
                            <th>Nickname</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="user" items="${usersList}">
                            <tr>
                                <td>${user.user_no}</td>
                                <td>${user.user_type}</td>
                                <td>${user.user_id}</td>
                                <td>${user.user_name}</td>
                                <td>${user.user_email}</td>
                                <td>${user.user_birth}</td>
                                <td>${user.user_gender}</td>
                                <td>${user.user_nickname}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <jsp:include page="/WEB-INF/views/page/admin/layout/footer.jsp"/>
        </main>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
