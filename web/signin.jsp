<%--
  Created by IntelliJ IDEA.
  User: qcg
  Date: 2018/3/26
  Time: 10:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta content="text/html" charset="UTF-8" http-equiv="content-type">
    <title>Sign in</title>
</head>
<body>
<form method="post" action="<%=request.getContextPath()%>/signInServlet">
    用户名：<input type="text" id="userName" name="userName" placeholder="请输入用户名"/><br/>
    密码: <input type="password" id="password" name="password" placeholder="请输入密码"/><br/>
    <input type="submit" value="Sign in"/>
    <input type="button" value="Sign up" onclick="window.location.href='<%=request.getContextPath()%>/signup.jsp'">
</form>
</body>

</html>
