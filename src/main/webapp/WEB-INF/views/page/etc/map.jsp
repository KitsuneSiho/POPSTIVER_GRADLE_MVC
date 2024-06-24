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
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9441e4fcdaf29ae0ef64a498fa8c752d&libraries=services"></script>


</head>

<body>
<jsp:include page="/WEB-INF/views/page/fix/header.jsp" />

<div class="map">
    <h3>모든 행사 위치 (카카오 지도)</h3>
    <div id="multiMap">
        <button id="currentLocationButton"><%= request.getContextPath() %>현재 위치</button>
    </div>

</div>


<div class="searchList">
    <article>
        <div class="searchListOpen" onclick="toggleSearchList(this)">
            <p>진행 중</p>
            <img src="${root}/resources/asset/화살표.svg" class="arrow" alt="화살표">
        </div>
        <div class="popupFestivalInfo">
            <div class="carousel">
                <div class="carousel-content" id="carousel-content">
                    <div class="card">
                        <div class="card-content">
                            <img src="${root}/resources/asset/포스터이미지/흠뻑쇼6.gif" alt="포스터1">
                            <img src="${root}/resources/asset/좋아요.svg" class="bookmark" alt="">
                            <h3>It's Your Day: 이번 광고, 생일 카페 주인공은 바로 너!</h3>
                            <p>
                                <img src="${root}/resources/asset/위치표시.svg" class="cardAddress" alt="">
                                서울특별시 마포구
                            </p>
                            <p>
                                <img src="${root}/resources/asset/날짜.svg" class="cardDate" alt="">
                                24.05.02 - 24.06.30
                            </p>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-content">
                            <img src="${root}/resources/asset/포스터이미지/흠뻑쇼6.gif" alt="포스터1">
                            <img src="${root}/resources/asset/좋아요.svg" class="bookmark" alt="">
                            <h3>It's Your Day: 이번 광고, 생일 카페 주인공은 바로 너!</h3>
                            <p>
                                <img src="${root}/resources/asset/위치표시.svg" class="cardAddress" alt="">
                                서울특별시 마포구
                            </p>
                            <p>
                                <img src="${root}/resources/asset/날짜.svg" class="cardDate" alt="">
                                24.05.02 - 24.06.30
                            </p>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-content">
                            <img src="${root}/resources/asset/포스터이미지/흠뻑쇼6.gif" alt="포스터1">
                            <img src="${root}/resources/asset/좋아요.svg" class="bookmark" alt="">
                            <h3>It's Your Day: 이번 광고, 생일 카페 주인공은 바로 너!</h3>
                            <p>
                                <img src="${root}/resources/asset/위치표시.svg" class="cardAddress" alt="">
                                서울특별시 마포구
                            </p>
                            <p>
                                <img src="${root}/resources/asset/날짜.svg" class="cardDate" alt="">
                                24.05.02 - 24.06.30
                            </p>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-content">
                            <img src="${root}/resources/asset/포스터이미지/흠뻑쇼6.gif" alt="포스터1">
                            <img src="${root}/resources/asset/좋아요.svg" class="bookmark" alt="">
                            <h3>It's Your Day: 이번 광고, 생일 카페 주인공은 바로 너!</h3>
                            <p>
                                <img src="${root}/resources/asset/위치표시.svg" class="cardAddress" alt="">
                                서울특별시 마포구
                            </p>
                            <p>
                                <img src="${root}/resources/asset/날짜.svg" class="cardDate" alt="">
                                24.05.02 - 24.06.30
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </article>

</div>

<jsp:include page="/WEB-INF/views/page/fix/footer.jsp" />

<script src="${root}/resources/js/searchResult.js"></script>
<script src="${root}/resources/js/bookmarkToggle.js"></script>
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
