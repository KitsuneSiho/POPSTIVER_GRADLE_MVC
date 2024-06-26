<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tag Extraction Form</title>
</head>
<body>
<h1>Tag Extraction Form</h1>
<form action="process_tag" method="post">
    <label for="title">Title:</label><br>
    <input type="text" id="title" name="title" required><br><br>
    <label for="content">Content:</label><br>
    <textarea id="content" name="content" rows="4" cols="50" required></textarea><br><br>
    <input type="submit" value="Submit">
</form>

<h2>Extracted Tags:</h2>
<p id="tags"></p>

<script>
    document.addEventListener("DOMContentLoaded", function() {
        // Check if there are tags in the query string
        const urlParams = new URLSearchParams(window.location.search);
        const tags = urlParams.get('tags');
        if (tags) {
            document.getElementById('tags').innerText = tags;
        }
    });
</script>
</body>
</html>
