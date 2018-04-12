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
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.8.2.min.js"></script>
    <title>我的文件</title>
</head>
<%
    String userName = (String) request.getSession().getAttribute("userName");
    String test = request.getAttribute("fileCount").toString();
    int fileCount = Integer.parseInt(test);
    List<Object[]> fileList = (List<Object[]>) request.getAttribute("fileList");
%>
<body>
欢迎&nbsp;<b><%=userName%></b>!
<form action="<%=request.getContextPath()%>/fileUploadServlet" method="post" enctype="multipart/form-data">
    上传文件：<input type="file" id="uploadFile" name="uploadFile"/><br/>
    <input type="submit" value="upload"/>
</form>
<table id="fileTable" border="1" cellpadding="0" cellspacing="0">
    <thead>
        <tr>
            <td>序号</td>
            <td>文件名称</td>
            <td>大小</td>
            <td>是否共享</td>
            <td>操作</td>
        </tr>
    <%
        if (fileList.size() != 0){
            for (int i = 0;i < fileList.size();i ++) {
    %>
        <tr>
            <td><%=i+1%></td>
            <%
                long size = Long.parseLong((String)fileList.get(i)[2]);
                String fileName = (String)fileList.get(i)[1];
                int id = Integer.parseInt(fileList.get(i)[0].toString());
            %>
            <td><%=fileName%></td>
            <td><%=FileUtil.sizeType(size)%></td>
            <td id = "isShare<%=id%>">
                <%
                    boolean isShare = Boolean.parseBoolean(fileList.get(i)[3].toString());
                    String shareVal;
                    if (isShare){
                        shareVal = "是";
                    }else {
                        shareVal = "否";
                    }
                %>
                <%=shareVal%>
            </td>
            <td>
                <input type="button" value="变更共享" id="change<%=id%>" onclick="changegx(<%=id%>,<%=isShare%>)">
                <input type="button" value="删除" id="delete<%=id%>" onclick="deletewj(<%=id%>)">
            </td>
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
<script>
    function changegx(e,e1) {
        var temp = {};
        temp.fileId = e;
        temp.isShare = e1;
        $.ajax({
           url:'<%=request.getContextPath()%>/update',
           type:"post",
           data:{
               "temp":JSON.stringify(temp)
           },
            success:function(data,status){
               if(data.status == 1){
                   alert(data.msg);
                   //$("#isShare + e").html(!e1);
                   //console.log($("#isShare + e").html());
                   location.reload();
               }
            }
        });
    }
    function deletewj(e) {
        $.ajax({
            url:'<%=request.getContextPath()%>/delete',
            type:"post",
            data:{
                fileId:e,
            },
            dataType:'json',
            success:function(data,status){
                if (data.status == 1){
                    alert(data.msg);
                    location.reload();
                }
            }
        });
    }
</script>
</html>
