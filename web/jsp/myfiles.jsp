<%--
  Created by IntelliJ IDEA.
  User: qcg
  Date: 2018/3/26
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="content-type" charset="UTF-8" content="text/html">
    <title>我的文件</title>
</head>
<body>
欢迎<b><%=request.getSession().getAttribute("userName")%></b>!
<form action="<%=request.getContextPath()%>/fileUploadServlet" method="post" enctype="multipart/form-data">
    上传文件：<input type="file" id="uploadFile" name="uploadFile"/><br/>
    测试:<input type="text" id="test" name="test" value="111"/><br/>
    <input type="submit" value="upload"/>
</form>
<table id="fileTable">
    <thead>
        <tr>
            <td>序号</td>
            <td>文件名称</td>
            <td>大小</td>
        </tr>
    </thead>
    <tbody>
    </tbody>

</table>
</body>
</html>
