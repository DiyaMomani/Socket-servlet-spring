<!DOCTYPE html>
<html lang = "en">
<head>
    <title> home </title>
    <style>
        #container {
            display: flex;
            flex-wrap: wrap;
        }
        #container > a {
            border-radius: 15px;
            text-decoration: none;
            color: black;
            background-color: hotpink;
            width: auto;
            height: auto;
            margin: 10px;
            padding: 15px;
        }
        h3 {
            margin: 10px;
        }
    </style>
</head>
<body>
<h1> Welcome To Home Page </h1>
<h3 style = "color: hotpink"> Pick your service: </h3>
<div id = "container">
    <a href="viewMarks"> View All marks </a>
    <a href="viewSingle"> View Single mark </a>
    <a href="viewAnalytics"> View Analytics (Average, Min, Max, Median) </a>
</div>
</body>
</html>