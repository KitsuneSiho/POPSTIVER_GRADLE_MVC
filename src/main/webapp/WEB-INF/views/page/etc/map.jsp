<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="root" value="${pageContext.request.contextPath }" />

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${root}/resources/css/etcCss/map.css">
    <link rel="stylesheet" href="${root}/resources/css/fixCss/header.css">
    <link rel="stylesheet" href="${root}/resources/css/fixCss/footer.css">
    <link rel="stylesheet" href="${root}/resources/css/fixCss/menuModal.css">
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
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="${root}/resources/js/loginName.js"></script>
</head>

<body>
<header class="mainTop">
    <div class="mainTopLogo">
        <h1><a href="../main">POPSTIVER</a></h1>
    </div>

    <div class="mainTopSearch">
        <div class="mainTopSearchContainer">
            <label>
                <input type="text" placeholder="팝업스토어, 페스티벌 검색">
            </label>
            <button type="submit" class="searchButton" onclick="window.location.href='searchResult'">
                <img src="${root}/resources/asset/메인검색창검색버튼.svg" alt="">
            </button>
        </div>
    </div>



    <div class="mainTopButton">
        <sec:authorize access="!isAuthenticated()">
            <button class="loginButton" onclick="window.location.href='login'">
                로그인
            </button>
        </sec:authorize>


        <sec:authorize access="isAuthenticated()">
            <button class="logoutButton" onclick="window.location.href='logout'">
                로그아웃
            </button>
        </sec:authorize>

        <button class="menuButton">
            <img src="${root}/resources/asset/메인메뉴버튼.svg" alt="">
        </button>
    </div>

</header>
<div id="menuModal" class="modal">
    <div class="modal-content">
        <ul>
            <li><a href="${root}/myPage">마이페이지</a></li>
            <li><a href="${root}/map">근처 행사</a></li>
            <li><a href="${root}/bookmark">관심 행사</a></li>
            <li><a href="${root}/calendar">행사 일정</a></li>
            <li><a href="${root}/contact">게시판</a></li>
        </ul>
    </div>
</div>
<div class="map">
    <h1>근처 행사</h1>
    <div id="mapView" style="width:1000px;height:900px;"></div>
</div>




<footer>
    ㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇ<br>
    ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ
</footer>

<script src="${root}/resources/js/menuModal.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9441e4fcdaf29ae0ef64a498fa8c752d"></script>
<script>
    // 현재 위치를 가져와서 지도 중심 설정
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function(position) {
            var lat = position.coords.latitude; // 위도
            var lon = position.coords.longitude; // 경도

            var mapContainer = document.getElementById('mapView'), // 지도를 표시할 div
                mapOption = {
                    center: new kakao.maps.LatLng(lat, lon), // 지도의 중심좌표
                    level: 3 // 지도의 확대 레벨
                };

            var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

            // 마커가 표시될 위치입니다
            var markerPosition  = new kakao.maps.LatLng(lat, lon);

            // 마커를 생성합니다
            var marker = new kakao.maps.Marker({
                position: markerPosition
            });

            // 마커가 지도 위에 표시되도록 설정합니다
            marker.setMap(map);
        }, function(error) {
            console.error('Error occurred. Error code: ' + error.code);
        });
    } else {
        console.error('Geolocation is not supported by this browser.');
    }
</script>
</body>

</html>