<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Business Contents</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="<c:url value='/resources/css/adminCss/admin.css' />">
    <style>
        :root {
            --primary-color: #3498db;
            --secondary-color: #2c3e50;
            --background-color: #ecf0f1;
            --text-color: #34495e;
        }
        body {
            font-family: 'Roboto', sans-serif;
            background-color: var(--background-color);
            color: var(--text-color);
        }
        .main-content {
            padding: 20px;
            margin-bottom: 50px;

        }
        h2 {
            text-align: center;
            background: var(--secondary-color);
            color: #fff;
            padding: 20px 0;
            margin: 0 0 20px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
        }
        .search-box {
            background: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
            margin-bottom: 20px;
        }
        .search-box input[type="text"] {
            width: 70%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            margin-right: 10px;
        }
        .search-box button {
            padding: 10px 20px;
            border: none;
            background: var(--primary-color);
            color: #fff;
            border-radius: 4px;
            cursor: pointer;
            transition: background 0.3s ease;
        }
        .search-box button:hover {
            background: #2980b9;
        }
        .card {
            margin-bottom: 20px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
        }
        .card-header {
            background-color: var(--primary-color);
            /*color: #fff;*/
        }
        .card-body {
            padding: 15px;
        }
        .details-btn {
            color: var(--primary-color);
            cursor: pointer;
        }
        .pagination {
            justify-content: center;
        }
        @media (max-width: 768px) {
            .search-box input[type="text"] {
                width: 100%;
                margin-bottom: 10px;
            }
            .search-box button {
                width: 100%;
            }
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
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4 main-content">
            <jsp:include page="/WEB-INF/views/page/admin/layout/header.jsp"/>
            <div class="container">
                <h2><i class="fas fa-clipboard-list"></i> Business Contents</h2>
                <div class="search-box">
                    <input type="text" id="search" placeholder="Search for contents...">
                    <button><i class="fas fa-search"></i> Search</button>
                </div>
                <div id="businessContentsCards">
                    <c:forEach var="post" items="${posts}" varStatus="status">
                        <div class="card">
                            <div class="card-header">
                                <h5 class="mb-0">
                                    <span class="mr-2">${post.tempNo}</span>
                                        ${post.tempTitle}
                                </h5>
                            </div>
                            <div class="card-body">
                                <p><strong>Host:</strong> ${post.tempHost}</p>
                                <p><strong>Location:</strong> ${post.tempLocation}</p>
                                <p><strong>Date:</strong> ${post.tempStart} - ${post.tempEnd}</p>
                                <a class="details-btn" data-toggle="collapse" href="#details${status.index}">
                                    Show Details <i class="fas fa-chevron-down"></i>
                                </a>
                                <div class="collapse mt-3" id="details${status.index}">
                                    <p><strong>Content:</strong> ${post.tempContent}</p>
                                    <p><strong>District:</strong> ${post.tempDist}</p>
                                    <p><strong>Subdistrict:</strong> ${post.tempSubdist}</p>
                                    <p><strong>Open Time:</strong> ${post.openTime}</p>
                                    <p><strong>Attachment:</strong> ${post.tempAttachment}</p>
                                    <p><strong>Event Type:</strong> ${post.eventType}</p>
                                    <p><strong>Brand Link:</strong> ${post.brandLink}</p>
                                    <p><strong>Brand SNS:</strong> ${post.brandSns}</p>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <nav aria-label="Page navigation">
                    <ul class="pagination">
                        <li class="page-item"><a class="page-link" href="#">Previous</a></li>
                        <li class="page-item"><a class="page-link" href="#">1</a></li>
                        <li class="page-item"><a class="page-link" href="#">2</a></li>
                        <li class="page-item"><a class="page-link" href="#">3</a></li>
                        <li class="page-item"><a class="page-link" href="#">Next</a></li>
                    </ul>
                </nav>
            </div>
            <jsp:include page="/WEB-INF/views/page/admin/layout/footer.jsp"/>
        </main>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
    $(document).ready(function() {
        $("#search").on("keyup", function() {
            var value = $(this).val().toLowerCase();
            $("#businessContentsCards .card").filter(function() {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });
    });
</script>
</body>
</html>