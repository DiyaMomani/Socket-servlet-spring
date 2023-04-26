<%@ page import="database.Mark" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3> Marks: </h3>
<ul>
    <%! ArrayList<Mark> marks = new ArrayList<>(); %>
    <% marks = (ArrayList<Mark>)request.getAttribute("marks"); %>
    <% for (Mark mark : marks) { %>
    <li>
        <%= mark.toString() %>
    </li>
    <% } %>
</ul>
</body>
</html>