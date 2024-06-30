<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원 리스트</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value='/resources/css/adminCss/admin.css' />">
    <style>
        body {
            font-family: 'Noto Sans KR', sans-serif;
            background-color: #f8f9fa;
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
        main {
            padding: 20px;
        }
        .table {
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        .table thead {
            background-color: #007bff;
            color: #fff;
        }
        .delete-button {
            color: #dc3545;
            cursor: pointer;
            transition: 0.3s;
        }
        .delete-button:hover {
            color: #c82333;
        }
        .card {
            border: none;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        .card-header {
            background-color: #007bff;
            border-radius: 8px 8px 0 0;
        }
    </style>
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
            <div class="card mt-4">
                <div class="card-header">
                    <h2 class="mb-0">회원 리스트</h2>
                </div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-hover">
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
            </div>
            <jsp:include page="/WEB-INF/views/page/admin/layout/footer.jsp"/>
        </main>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
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