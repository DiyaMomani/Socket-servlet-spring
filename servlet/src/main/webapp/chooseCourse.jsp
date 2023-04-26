<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>choose</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
<form action="<%=(String)request.getAttribute("action")%>" method="post">
    <label for="course"> Choose Course </label>
    <select name="course" id="course">
        <option value="cpp"> CPP </option>
        <option value="java"> JAVA </option>
        <option value="ruby"> Ruby </option>
    </select>
    <p>
        <input type="submit" value="Submit" />
    </p>
</form>
</body>
</html>