<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
    <h1>登录</h1>

    <form action="/LoginServlet" method="post">
        <input name="name_field" type="text"/>
        <input name="password_field" type="text"/>
        <button type="submit">登录</button>
    </form>
</body>
</html>