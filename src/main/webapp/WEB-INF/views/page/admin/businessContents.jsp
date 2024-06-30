<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Business Contents</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <link rel="stylesheet" href="<c:url value='/resources/css/adminCss/admin.css' />">
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .container-fluid {
            padding: 0;
        }
        .main-content {
            padding: 20px;
        }
        .table-wrapper {
            overflow-x: auto;
        }
        h2 {
            text-align: center;
            background: #2c3e50;
            color: #fff;
            padding: 20px 0;
            margin: 0;
        }
        .table th, .table td {
            vertical-align: middle;
            white-space: nowrap;
        }
        .table th {
            background-color: #f8f9fa;
        }
        .search-box {
            padding: 20px;
            text-align: center;
            background: #ecf0f1;
        }
        .search-box input[type="text"] {
            width: 300px;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            margin-right: 10px;
        }
        .search-box button {
            padding: 10px 20px;
            border: none;
            background: #3498db;
            color: #fff;
            border-radius: 4px;
            cursor: pointer;
        }
        .search-box button:hover {
            background: #2980b9;
        }
    </style>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function() {
            $("#search").on("keyup", function() {
                var value = $(this).val().toLowerCase();
                $("#businessContentsTable tr").filter(function() {
                    $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
                });
            });
        });
    </script>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <!-- 사이드바 -->
        <nav class="col-md-2 d-none d-md-block bg-light sidebar">
            <jsp:include page="/WEB-INF/views/page/admin/layout/sidebar.jsp"/>
        </nav>

        <!-- 메인 콘텐츠 -->
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4 main-content">
            <jsp:include page="/WEB-INF/views/page/admin/layout/header.jsp"/>
            <div class="container">
                <h2><i class="fas fa-clipboard-list"></i> Business Contents</h2>
                <div class="search-box">
                    <input type="text" id="search" placeholder="Search for contents...">
                    <button><i class="fas fa-search"></i> Search</button>
                </div>
                <div class="table-wrapper">
                    <table class="table table-striped table-bordered">
                        <thead>
                        <tr>
                            <th>No</th>
                            <th>Title</th>
                            <th>Content</th>
                            <th>Host</th>
                            <th>District</th>
                            <th>Subdistrict</th>
                            <th>Location</th>
                            <th>Start Date</th>
                            <th>End Date</th>
                            <th>Open Time</th>
                            <th>Attachment</th>
                            <th>Event Type</th>
                            <th>Brand Link</th>
                            <th>Brand SNS</th>
                        </tr>
                        </thead>
                        <tbody id="businessContentsTable">
                        <c:forEach var="post" items="${posts}">
                            <tr>
                                <td>${post.tempNo}</td>
                                <td>${post.tempTitle}</td>
                                <td>${post.tempContent}</td>
                                <td>${post.tempHost}</td>
                                <td>${post.tempDist}</td>
                                <td>${post.tempSubdist}</td>
                                <td>${post.tempLocation}</td>
                                <td>${post.tempStart}</td>
                                <td>${post.tempEnd}</td>
                                <td>${post.openTime}</td>
                                <td>${post.tempAttachment}</td>
                                <td>${post.eventType}</td>
                                <td>${post.brandLink}</td>
                                <td>${post.brandSns}</td>
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