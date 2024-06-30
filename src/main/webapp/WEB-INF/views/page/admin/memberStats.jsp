<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원 및 SNS 가입 통계</title>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300;400;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <link rel="stylesheet" href="<c:url value='/resources/css/adminCss/admin.css' />">
    <link rel="stylesheet" href="<c:url value='/resources/css/adminCss/memberStats.css' />">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
<header>
    <jsp:include page="/WEB-INF/views/page/admin/layout/header.jsp"/>
</header>
<div class="container-fluid">
    <div class="row">
        <!-- 사이드바 -->
        <nav class="col-md-2 d-none d-md-block bg-light sidebar">
            <jsp:include page="/WEB-INF/views/page/admin/layout/sidebar.jsp"/>
        </nav>

        <!-- 메인 콘텐츠 -->
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
            <div class="main-content">
                <h2>회원 통계</h2>
                <div class="total-members">
                    <div class="stat-label">총 회원 수</div>
                    <div class="stat-value" id="totalMembers"></div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="chart-container">
                            <canvas id="genderChart"></canvas>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="stat-summary row">
                            <div class="col-md-6">
                                <div class="stat-item">
                                    <div class="stat-label">남성</div>
                                    <div class="stat-value" id="maleCount"></div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="stat-item">
                                    <div class="stat-label">여성</div>
                                    <div class="stat-value" id="femaleCount"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <h2 class="mt-5">SNS 가입 분류 통계</h2>
                <div class="stats-container row">
                    <div class="col-md-4">
                        <div class="stat-item">
                            <div class="stat-label">총 가입자 수</div>
                            <div class="stat-value" id="totalUsers"></div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="stat-item">
                            <div class="stat-label">가장 많은 가입 SNS</div>
                            <div class="stat-value" id="topSNS"></div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="stat-item">
                            <div class="stat-label">최근 30일 신규 가입자</div>
                            <div class="stat-value" id="newUsers"></div>
                        </div>
                    </div>
                </div>

                <div class="row mt-4">
                    <div class="col-md-6">
                        <div class="chart-container">
                            <canvas id="snsChart"></canvas>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="table-container">
                            <table>
                                <thead>
                                <tr>
                                    <th>SNS</th>
                                    <th>가입자 수</th>
                                    <th>비율</th>
                                </tr>
                                </thead>
                                <tbody id="snsTableBody">
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

                <h2 class="mt-5">나이대별 통계</h2>
                <div class="row">
                    <div class="col-md-12">
                        <div class="chart-container">
                            <canvas id="ageChart"></canvas>
                        </div>
                    </div>
                </div>

                <h2 class="mt-5">인기 관심태그</h2>
                <div class="row">
                    <div class="col-md-12">
                        <div class="chart-container">
                            <canvas id="tagChart"></canvas>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </div>
</div>
<!-- 푸터 -->
<footer class="mt-auto">
    <jsp:include page="/WEB-INF/views/page/admin/layout/footer.jsp"/>
</footer>
<script>
    // JSON 데이터를 직접 JS 변수에 포함시킵니다.
    var genderStatsJson = '${genderStatsJson}';
    var snsStatsJson = '${snsStatsJson}';
    var ageStatsJson = '${ageStatsJson}';
    var tagStatsJson = '${tagStatsJson}';
</script>
<script src="<c:url value='/resources/js/memberStats.js' />"></script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
