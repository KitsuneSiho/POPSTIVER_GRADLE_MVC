<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="root" value="${pageContext.request.contextPath }" />

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${root}/resources/css/searchResultCss/posterInfo.css">
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

        @font-face {
            font-family: Pre;
            src: url('${root}/resources/font/Pre.ttf');
        }
    </style>
</head>

<body>
<jsp:include page="/WEB-INF/views/page/fix/header.jsp" />

<div class="mainPoster">
    <img src="${root}/resources/asset/포스터이미지/워터밤가로.webp" alt="">
</div>

<div class="detailInfo">
    <div class="detailInfoTitle">
        <ul>
            <li><button>태그</button></li>
            <li><button>태그</button></li>
            <li><button>태그</button></li>
            <li><button>태그</button></li>
            <li><button>태그</button></li>
            <li><img src="${root}/resources/asset/조회수.svg" alt=""><p>123</p></li>
            <li><img src="${root}/resources/asset/좋아요.svg" class="bookmark" alt=""></li>
            <li><img src="${root}/resources/asset/공유버튼.svg" alt=""></li>
        </ul>
        <h1 class="title">흠뻑쇼</h1>
        <p class="detailDate">2024.06.13 ~ 2024.06.14</p>
        <p class="detailAddress">
            <img src="${root}/resources/asset/위치표시.svg" alt="">
            서울특별시 서초구 강남대로 55 빌딩 플래그십 스토어
        </p>

        <div class="detailInfoTime">
            <p class="detailInfoTimeTitle">운영시간</p>
            <p class="detailInfoTimeInfo">
            <p>월 : 09:00 ~ 20:00</p>
            <p>화 : 09:00 ~ 20:00</p>
            <p>수 : 09:00 ~ 20:00</p>
            <p>목 : 09:00 ~ 20:00</p>
            <p>금 : 09:00 ~ 20:00</p>
            <p>토 : 09:00 ~ 20:00</p>
            <p>일 : 09:00 ~ 20:00</p>
        </div>
        <p class="detailIntroduceTitle">팝업스토어, 페스티벌 소개</p>
        <div class="detailIntroduce">
            <p>저희 흠뻑쇼는 어쩌구저쩌구 얄리얄리얄라숑 얄라리얄라</p>
            <p>저희 흠뻑쇼는 어쩌구저쩌구 얄리얄리얄라숑 얄라리얄라</p>
            <p>저희 흠뻑쇼는 어쩌구저쩌구 얄리얄리얄라숑 얄라리얄라</p>
            <p>저희 흠뻑쇼는 어쩌구저쩌구 얄리얄리얄라숑 얄라리얄라</p>
        </div>
        <p class="detailInfoMapTitle">지도</p>
        <div class="detailInfoMap">
            <img src="${root}/resources/asset/포스터이미지/testimage1.JPG" alt="">
        </div>
        <div class="detailInfoReview">
            <p class="detailInfoReviewTitle">후기</p>
            <p>댓글 1,234개
                <label>
                    <input type="text" placeholder="후기를 입력해주세요.">
                </label>
                <button type="submit">등록</button>
            </p>
            <div class="detailInfoReviewTable">
                <table>
                    <tr>
                        <td>
                            <div class="comment-header">
                                <div class="name">제니</div>
                                <div class="date">2024.06.01 오전 8:00</div>
                            </div>
                        </td>
                        <td rowspan="3">
                            <img src="${root}/resources/asset/포스터이미지/흠뻑쇼3.gif" alt="댓글 이미지" class="comment-img">
                        </td>
                        <td>
                            <div class="comment-text">안녕하세요 제니입니다.
                            </div>
                        </td>
                        <td>
                            <div class="stars">
                                <img src="${root}/resources/asset/별점채워진별.svg" alt="">
                                <img src="${root}/resources/asset/별점채워진별.svg" alt="">
                                <img src="${root}/resources/asset/별점채워진별.svg" alt="">
                                <img src="${root}/resources/asset/별점빈별.svg" alt="">
                                <img src="${root}/resources/asset/별점빈별.svg" alt="">
                            </div>
                        </td>
                        <td>
                            <div class="delete">
                                <img src="${root}/resources/asset/삭제버튼.svg" alt="">
                            </div>
                        </td>

                    </tr>


                </table>

            </div>

        </div>
    </div>


</div>

<jsp:include page="/WEB-INF/views/page/fix/footer.jsp" />
<script src="${root}/resources/js/bookmarkToggle.js"></script>
</body>

</html>