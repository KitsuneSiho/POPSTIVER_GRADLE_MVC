<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Choose Your Tags</title>
    <style>
        .tag-disabled {
            color: gray;
            background-color: #f0f0f0;
        }
    </style>
    <script>
        function validateForm() {
            var checkboxes = document.querySelectorAll('input[type="checkbox"]:checked');
            if (checkboxes.length > 5) {
                alert("You can select up to 5 tags only.");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
<h1>Choose Your Tags</h1>
<form action="save_user_tags" method="post" onsubmit="return validateForm()">
    <%--@declare id="tags"--%><input type="hidden" name="userId" value="${userId}">
    <label for="tags">Select Tags:</label><br>
    <c:forEach var="tag" items="${tags}">
        <c:choose>
            <c:when test="${tag.selected}">
                <input type="checkbox" id="tag_${tag.tag_no}" name="tagIds" value="${tag.tag_no}" checked>
                <label for="tag_${tag.tag_no}">${tag.tag_name}</label><br>
            </c:when>
            <c:otherwise>
                <input type="checkbox" id="tag_${tag.tag_no}" name="tagIds" value="${tag.tag_no}" class="tag-disabled">
                <label for="tag_${tag.tag_no}" class="tag-disabled">${tag.tag_name}</label><br>
            </c:otherwise>
        </c:choose>
    </c:forEach>
    <br><br>
    <input type="submit" value="Save">
</form>
<p>${message}</p>
<p>${error}</p>
</body>
</html>
