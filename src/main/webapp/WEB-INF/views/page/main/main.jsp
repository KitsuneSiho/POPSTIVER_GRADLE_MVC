<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:set var="root" value="${pageContext.request.contextPath }" />

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${root}/resources/css/mainCss/main.css">
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

</head>

<body>

<div class="img-background">
    <img src="${root}/resources/asset/메인배경.jpg" id="bg-img" alt="">
</div>

<div class="container">
    <div class="popstiver">
        <div class="title">
            <h1><span>POP</span>UP</h1>
            <h1>FE<span>STI</span>VAL</h1>
            <h1>LO<span>VER</span></h1>
        </div>
        <div class="titleInfo">
            <p>팝스티버는 POP-UP과 FESTIVAL 그리고 LOVER를 조합한 단어입니다.</p>
            <p>팝스티버는 팝업 이벤트와 페스티벌을 사랑하는 이들을 위한 커뮤니티 입니다.</p>
            <p>우리는 다양한 팝업 쇼와 페스티벌을 즐기며 함께 모여 새로운 경험을 나누고자 합니다.</p>
            <p>우리와 함께 도시의 다채로운 문화를 즐기고 새로운 친구들을 만나보세요!</p>
            <p>함께 즐기는 이들과 행복한 순간들을 만들어봐요</p>
        </div>
    </div>
    <div class="scroll">
        <p>SCROLL</p>
        <img src="${root}/resources/asset/아래로.svg" alt="">
    </div>
</div>



<div class="enterContainer">
    <div class="enterPopup" onclick="window.location.href='mainPopup'">
        <img src="" alt="">
        <p>팝업스토어알라라라라라라</p>
        <button>POP-UP 보러가기</button>
    </div>
    <div class="enterFestival" onclick="window.location.href='mainFestival'">
        <img src="" alt="">
        <p>축제알라라라라라라</p>
        <button>FESTIVAL 보러가기</button>
    </div>
</div>
</body>
</html>