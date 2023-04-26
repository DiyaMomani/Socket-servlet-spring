<%@ page import="database.Mark" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>analytics</title>
</head>
<body>
<h1>
    Min MarK:
    <%= ((Double)request.getAttribute("minMark")).toString() %>
</h1>
<h1>
    Max Mark:
    <%= ((Double)request.getAttribute("maxMark")).toString() %>
</h1>
<h1>
    Average:
    <%= ((Double)request.getAttribute("average")).toString() %>
</h1>
<h1>
    Median:
    <%= ((Double)request.getAttribute("median")).toString() %>
</h1>
</body>
</html>