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
    <meta http-equiv="content-type" charset="UTF-8" content="text/html">
    <title>注册</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/signUpServlet" method="post" onsubmit="return validate()">
    用户名:<input type="text" id="userName" name="userName" placeholder="请输入用户名"/><br/>
    密码:<input type="password" id="password" name="password" placeholder="请输入密码"/><br/>
    确认密码:<input type="password" id="passwordValidate" placeholder="请确认密码" onblur="validate()"/>
    <input type="text" style="display: none;font-size: 15px;color:red;border: 0px" readonly id="temp"/>
    <br/>

    <input type="submit" value="Sign up"/>
</form>
</body>
<script>
    function validate() {
        var password = document.getElementById("password");
        var password2 = document.getElementById("passwordValidate");
        if (password.value != password2.value){
            var validate = document.getElementById("temp");
            validate.style.display="";
            validate.value = "密码不一致";
            return false;
        }else {
            var validate = document.getElementById("temp");
            validate.style.display="none";
            validate.value = "密码不一致";
            return true;
        }
    }
</script>
</html>
