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
            position: relative;
        }
        .map {
            margin-top: 20px;
        }

        #currentLocationButton {
            position: absolute;
            top: 20px;
            right: 20px;
            background-color: rgba(255, 255, 255, 0.8);
            border: none;
            border-radius: 5px;
            padding: 10px;
            cursor: pointer;
        }
        #currentLocationButton img {
            width: 45px;
            height: 45px;
        }



    </style>
</head>
<body>
<div class="container">
    <h3>모든 행사 위치 (카카오 지도)</h3>
    <button id="currentLocationButton"><%= request.getContextPath() %>현재 위치</button>
    <div id="multiMap" style="width:100%;height:600px;"></div>
</div>

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

    // 페스티벌 데이터
    var festivals = [
        <c:forEach var="festival" items="${allFestivals}" varStatus="loop">
        {
            location: "${festival.festival_location}",
            link: "${pageContext.request.contextPath}/festival_Details/${festival.festival_no}"
        }<c:if test="${!loop.last}">,</c:if>
        </c:forEach>
    ];

    // 카카오맵 API 로드 후 초기화
    kakao.maps.load(function() {
        var multiMapContainer = document.getElementById('multiMap');
        var userMarker = null;
        var userOverlay = null;

        getUserLocation(function(userLocation) {
            var multiMapOption = {
                center: new kakao.maps.LatLng(userLocation.lat, userLocation.lng),
                level: 7
            };

            var multiMap = new kakao.maps.Map(multiMapContainer, multiMapOption);

            function addUserMarker(location) {
                if (userMarker) {
                    userMarker.setMap(null);
                }

                var userMarkerImageSrc = 'https://img.icons8.com/color/48/000000/marker.png', // 적절한 마커 이미지 URL
                    userMarkerImageSize = new kakao.maps.Size(35, 35),
                    userMarkerImageOption = {offset: new kakao.maps.Point(12, 12)};

                var userMarkerImage = new kakao.maps.MarkerImage(userMarkerImageSrc, userMarkerImageSize, userMarkerImageOption);

                userMarker = new kakao.maps.Marker({
                    map: multiMap,
                    position: new kakao.maps.LatLng(location.lat, location.lng),
                    title: '현재 위치',
                    image: userMarkerImage
                });

                if (userOverlay) {
                    userOverlay.setMap(null);
                }

                var content = '<div class="customoverlay"></div>';
                userOverlay = new kakao.maps.CustomOverlay({
                    content: content,
                    map: multiMap,
                    position: userMarker.getPosition(),
                    yAnchor: 1
                });
            }

            addUserMarker(userLocation);

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




            addMarkers(multiMap, geocoder, festivals);

            document.getElementById('currentLocationButton').addEventListener('click', function() {
                console.log('현재 위치 버튼 클릭됨');
                getUserLocation(function(newUserLocation) {
                    console.log('새 사용자 위치:', newUserLocation);
                    multiMap.setCenter(new kakao.maps.LatLng(newUserLocation.lat, newUserLocation.lng));
                    addUserMarker(newUserLocation);
                });
            });
        });
    });
</script>
</body>
</html>
