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
            position: relative; /* 추가: 버튼을 포함하는 컨테이너의 위치 지정 */
        }

        .map {
            margin-top: 20px;
        }

        .customoverlay {
            position: absolute;
            bottom: 80px; /* 예시로 조정한 값 */
            left: 50%;
            transform: translateX(-50%);
            border-radius: 6px;
            background-color: white;
            color: black;
            text-align: center;
            white-space: nowrap;
            padding: 5px;
            font-size: 20px;
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

        /* 추가: Bootstrap Icons를 통해 사용할 스타일 */
        .bi {
            vertical-align: middle;
            margin-bottom: 2px; /* 아이콘과 텍스트 간격 조정 */
        }

        /* 추가: 드롭다운 메뉴 스타일 */
        .dropdown {
            position: absolute;
            top: 80px;
            left: 20px;
            z-index: 1000; /* 다른 요소 위에 나타나도록 설정 */
            background-color: #fff;
            padding: 10px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
        }

    </style>
</head>
<body>
<div class="container">
    <h3>모든 행사 위치 (카카오 지도)</h3>
    <div class="dropdown">
        <label for="districtSelect">서울특별시 구 선택:</label>
        <select id="districtSelect">
            <option value="">구 선택</option>
            <option value="강남구">강남구</option>
            <option value="강동구">강동구</option>
            <option value="강서구">강서구</option>
            <!-- 다른 구들도 추가할 수 있음 -->
        </select>
    </div>
    <button id="currentLocationButton">내 위치로 돌아가기</button>
    <div id="multiMap" style="width:100%;height:600px;"></div>
</div>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9441e4fcdaf29ae0ef64a498fa8c752d&libraries=services"></script>
<!-- Bootstrap Icons CDN 추가 -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
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

            // 사용자 위치 마커 이미지 설정 (기본 마커 사용)
            var userMarkerImageSrc = 'https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css',
                userMarkerImageSize = new kakao.maps.Size(50, 50),
                userMarkerImageOption = { offset: new kakao.maps.Point(25, 50) };

            var userMarkerImage = new kakao.maps.MarkerImage(userMarkerImageSrc, userMarkerImageSize, userMarkerImageOption);

            // 사용자 위치 마커 생성 및 추가
            var userMarker = new kakao.maps.Marker({
                position: new kakao.maps.LatLng(userLocation.lat, userLocation.lng),
                map: multiMap
            });

            // 사용자 위치 마커 위에 텍스트 오버레이 추가
            var content = '<div class="customoverlay">내 위치</div>';
            var userOverlay = new kakao.maps.CustomOverlay({
                content: content,
                map: multiMap,
                position: userMarker.getPosition(),
                yAnchor: 1
            });

            var festivals = [
                <c:forEach var="festival" items="${allFestivals}" varStatus="loop">
                {
                    location: "${festival.festival_location}",
                    link: "${pageContext.request.contextPath}/festival_Details/${festival.festival_no}",
                    subdist: "${festival.festival_subdist}"
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

            // 구 선택 드롭다운 메뉴 이벤트 처리
            document.getElementById('districtSelect').addEventListener('change', function() {
                var selectedDistrict = this.value;

                // 모든 마커 숨기기
                multiMap.removeOverlayMapTypeId(kakao.maps.MapTypeId.MARKER);

                // 선택된 구에 해당하는 마커만 표시
                festivals.forEach(function(festival) {
                    if (festival.subdist === selectedDistrict) {
                        geocoder.addressSearch(festival.location, function(result, status) {
                            if (status === kakao.maps.services.Status.OK) {
                                var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

                                var marker = new kakao.maps.Marker({
                                    map: multiMap,
                                    position: coords,
                                    data: festival // 축제 데이터를 마커에 연결
                                });

                                kakao.maps.event.addListener(marker, 'click', function() {
                                    window.location.href = festival.link;
                                });
                            } else {
                                console.error('주소를 찾을 수 없습니다:', festival.location);
                            }
                        });
                    }
                });
            });

            // 내 위치로 돌아가는 버튼 클릭 시 지도 중심 이동
            document.getElementById('currentLocationButton').addEventListener('click', function() {
                getUserLocation(function(newUserLocation) {
                    multiMap.setCenter(new kakao.maps.LatLng(newUserLocation.lat, newUserLocation.lng));
                });
            });
        });
    });
</script>
</body>
</html>
