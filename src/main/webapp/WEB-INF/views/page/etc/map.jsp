<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>페스티벌 지도</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #000;
            color: #fff;
        }
        .container {
            width: 800px;
            margin: 0 auto;
            padding: 20px;
            background-color: #333;
            border-radius: 10px;
        }
        .map {
            margin-top: 20px;
        }
    </style>
</head>
<body>
<div class="container">
    <h3>모든 행사 위치 (카카오 지도)</h3>
    <div id="multiMap" style="width:100%;height:600px;"></div>
</div>

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
    <div id="mapView" ></div>
</div>


<footer>
    ㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇ<br>
    ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ
</footer>

<script src="${root}/resources/js/menuModal.js"></script>
<script src="${root}/resources/js/map.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9441e4fcdaf29ae0ef64a498fa8c752d"></script>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9441e4fcdaf29ae0ef64a498fa8c752d&libraries=services"></script>
<script>
    var geocoder = new kakao.maps.services.Geocoder();

    // 사용자의 위치를 가져오는 함수
    function getUserLocation(callback) {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(function(position) {
                var userLocation = {
                    lat: position.coords.latitude,
                    lng: position.coords.longitude
                };
                callback(userLocation);
            });
        } else {
            console.error('Geolocation is not supported by this browser.');
            // 기본 위치 (서울)
            var defaultLocation = {
                lat: 37.5665,
                lng: 126.9780
            };
            callback(defaultLocation);
        }
    }

    // 카카오맵 API 로드 후 초기화
    kakao.maps.load(function() {
        var multiMapContainer = document.getElementById('multiMap');

        getUserLocation(function(userLocation) {
            var multiMapOption = {
                center: new kakao.maps.LatLng(userLocation.lat, userLocation.lng),
                level: 7
            };

            var multiMap = new kakao.maps.Map(multiMapContainer, multiMapOption);

            var festivals = [
                <c:forEach var="festival" items="${allFestivals}" varStatus="loop">
                {
                    location: "${festival.festival_location}",
                    link: "${pageContext.request.contextPath}/festival_Details/${festival.festival_no}"
                }<c:if test="${!loop.last}">,</c:if>
                </c:forEach>
            ];

            // 함수 내에서 마커를 비동기적으로 추가
            function addMarkers(map, geocoder, festivals) {
                festivals.forEach(function(festival) {
                    geocoder.addressSearch(festival.location, function(result, status) {
                        if (status === kakao.maps.services.Status.OK) {
                            var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

                            var marker = new kakao.maps.Marker({
                                map: map,
                                position: coords
                            });

                            kakao.maps.event.addListener(marker, 'click', function() {
                                window.location.href = festival.link;
                            });
                        } else {
                            console.error('주소를 찾을 수 없습니다:', festival.location);
                        }
                    });
                });
            }

            // 모든 주소에 대해 마커를 추가하는 함수 호출
            addMarkers(multiMap, geocoder, festivals);
        });
    });
</script>
</body>
</html>
