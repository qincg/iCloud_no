<%@ page import="java.util.List" %>
<%@ page import="qcg.icloud.util.FileUtil" %><%--
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
<%
    String userName = (String) request.getSession().getAttribute("userName");
    String test = request.getAttribute("fileCount").toString();
    int fileCount = Integer.parseInt(test);
    List<Object[]> fileList = (List<Object[]>) request.getAttribute("fileList");
%>
<body>
欢迎<b><%=userName%></b>!
<form action="<%=request.getContextPath()%>/fileUploadServlet" method="post" enctype="multipart/form-data">
    上传文件：<input type="file" id="uploadFile" name="uploadFile"/><br/>
    <input type="submit" value="upload"/>
</form>
<table id="fileTable">
    <thead>
        <tr>
            <td>序号</td>
            <td>文件名称</td>
            <td>大小</td>
        </tr>
    <%
        if (fileList.size() != 0){
            for (int i = 0;i <= fileList.size();i ++) {
    %>
        <tr>
            <td><%=i+1%></td>
            <td><%=fileList.get(i)[1]%></td>
            <%
                long size = Long.parseLong((String)fileList.get(i)[2]);
                String fileName = (String)fileList.get(i)[3];
            %>
            <td><%=fileName%></td>
            <td><%=FileUtil.sizeType(size)%></td>
        </tr>
     <%
            }
        }
    %>
    </thead>
    <tbody>
    </tbody>

</table>
共<%=fileCount%>条记录
</body>
</html>
