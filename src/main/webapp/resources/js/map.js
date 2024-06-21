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