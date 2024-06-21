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

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9441e4fcdaf29ae0ef64a498fa8c752d&libraries=services"></script>
<script>
    var geocoder = new kakao.maps.services.Geocoder();

    var multiMapContainer = document.getElementById('multiMap'),
        multiMapOption = {
            center: new kakao.maps.LatLng(37.5665, 126.9780),
            level: 7
        };

    var multiMap = new kakao.maps.Map(multiMapContainer, multiMapOption);

    var festivals = [];
    <c:forEach var="festival" items="${allFestivals}">
    festivals.push({
        location: "${festival.festival_location}",
        link: "${pageContext.request.contextPath}/festival_Details/${festival.festival_no}"
    });
    </c:forEach>

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
</script>
</body>
</html>
