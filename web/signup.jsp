<%--
  Created by IntelliJ IDEA.
  User: qcg
  Date: 2018/3/26
  Time: 10:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/signUpServlet" method="post">
    用户名:<input type="text" id="userName" name="userName" placeholder="请输入用户名"/>
    密码:<input type="password" id="password" name="password" placeholder="请输入密码"/>
    <input type="submit" value="Sign up"/>
</form>
</body>
</html>
