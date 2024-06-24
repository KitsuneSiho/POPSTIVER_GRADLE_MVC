<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>페스티벌 상세정보</title>
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
        .festival-image {
            width: 100%;
            height: auto;
            border-radius: 10px;
        }
        .info {
            margin: 20px 0;
        }
        .info h2 {
            margin: 0;
        }
        .info p {
            margin: 5px 0;
        }
        .map, .video, .reviews {
            margin-top: 20px;
        }
        .share {
            margin-top: 10px;
        }
        .reviews .review {
            border-bottom: 1px solid #ccc;
            padding: 10px 0;
        }
        .reviews .stars {
            color: gold;
        }
    </style>
</head>
<body>
<div class="container">
    <img class="festival-image" src="${festival.festival_attachment}" alt="Festival Image">

    <div class="info">
        <h2>${festival.festival_title}</h2>
        <p>${festival.festival_start} - ${festival.festival_end}</p>
        <p>${festival.festival_location}</p>
        <p>${festival.festival_content}</p>
    </div>

    <div class="map">
        <h3>행사 위치 (카카오 지도)</h3>
        <div id="singleMap" style="width:100%;height:350px;"></div>
    </div>

    <div class="share">
        <h3>공유</h3>
        <p><a href="https://www.instagram.com/?url=${festival.brand_link}" target="_blank">인스타그램으로 공유하기</a></p>
        <p><a href="https://sharer.kakao.com/talk/friends/picker/link?link_ver=4&url=${festival.brand_link}&text=${festival.festival_title}" target="_blank">카카오톡으로 공유하기</a></p>
    </div>

    <h3>브랜드 홈페이지</h3>
    <p><a href="${festival.brand_link}" target="_blank">${festival.brand_link}</a></p>

    <h3>브랜드 SNS</h3>
    <p><a href="${festival.brand_sns}" target="_blank">${festival.brand_sns}</a></p>

    <h3>운영시간</h3>
    <p>${festival.open_time}</p>

    <h3>후기</h3>
    <div class="reviews">
        <!-- 리뷰 목록을 표시하는 부분 -->
    </div>
</div>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9441e4fcdaf29ae0ef64a498fa8c752d&libraries=services"></script>
<script>
    var geocoder = new kakao.maps.services.Geocoder();

    // 첫 번째 지도: 특정 행사 위치
    var singleMapContainer = document.getElementById('singleMap'), // 지도를 표시할 div
        singleMapOption = {
            center: new kakao.maps.LatLng(37.5665, 126.9780), // 서울을 중심으로 기본값 설정
            level: 3 // 지도의 확대 레벨
        };

    var singleMap = new kakao.maps.Map(singleMapContainer, singleMapOption); // 지도를 생성합니다

    geocoder.addressSearch("${festival.festival_location}", function(result, status) {
        if (status === kakao.maps.services.Status.OK) {
            var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

            // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
            singleMap.setCenter(coords);

            // 결과값으로 받은 위치에 마커를 생성합니다
            var marker = new kakao.maps.Marker({
                map: singleMap,
                position: coords
            });
        } else {
            alert('주소를 찾을 수 없습니다.');
        }
    });

    // 모든 주소에 대해 마커를 추가하는 함수 호출
    addMarkers(multiMap, geocoder, festivals);
</script>
</body>
</html>
