<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>회원 리스트</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <link rel="stylesheet" href="<c:url value='/resources/css/adminCss/admin.css' />">
    <style>
        body {
            font-family: 'Helvetica Neue', Arial, sans-serif;
        }
        .sidebar {
            background-color: #343a40;
            color: #fff;
        }
        .sidebar a {
            color: #fff;
        }
        .sidebar a:hover {
            background-color: #495057;
        }
        .table {
            margin-top: 20px;
        }
        .delete-button {
            color: red;
            cursor: pointer;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <!-- 사이드바 -->
        <nav class="col-md-2 d-none d-md-block bg-dark sidebar">
            <jsp:include page="/WEB-INF/views/page/admin/layout/sidebar.jsp"/>
        </nav>

        <!-- 메인 콘텐츠 -->
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4">
            <jsp:include page="/WEB-INF/views/page/admin/layout/header.jsp"/>
            <h2 class="mt-4">회원 리스트</h2>
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
                            <th>Actions</th>
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
                                <td>
                                    <i class="fas fa-trash delete-button" data-user-id="${user.user_id}"></i>
                                </td>
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
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
    $(document).ready(function () {
        $('.delete-button').on('click', function () {
            var userId = $(this).data('user-id');
            if (confirm('정말로 회원을 탈퇴시키겠습니까?')) {
                $.ajax({
                    url: '/member/admin/deleteUser',
                    type: 'DELETE',
                    data: JSON.stringify({ userId: userId }),
                    contentType: 'application/json; charset=utf-8',
                    success: function (response) {
                        alert('회원 탈퇴가 완료되었습니다.');
                        location.reload();
                    },
                    error: function (xhr, status, error) {
                        alert('회원 탈퇴 중 오류가 발생했습니다.');
                    }
                });
            }
        });
    });
</script>
</body>
</html>
