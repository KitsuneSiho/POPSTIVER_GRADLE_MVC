<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Recommendations</title>
</head>
<body>
<h1>Festival Recommendations</h1>
<c:forEach var="festival" items="${festivals}">
    <div>
        <h2>${festival.festival_title}</h2>
        <p>${festival.festival_content}</p>
        <a href="${festival.brand_link}">Visit</a>
        <img src="${festival.festival_attachment}" alt="${festival.festival_title}" width="200"/>
    </div>
</c:forEach>

<h1>Popup Recommendations</h1>
<c:forEach var="popup" items="${popups}">
    <div>
        <h2>${popup.popup_title}</h2>
        <p>${popup.popup_content}</p>
        <a href="${popup.brand_link}">Visit</a>
        <img src="${popup.popup_attachment}" alt="${popup.popup_title}" width="200"/>
    </div>
</c:forEach>
</body>
</html>
