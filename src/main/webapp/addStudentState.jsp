<%--
  Created by IntelliJ IDEA.
  User: y
  Date: 2020/11/28
  Time: 9:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="layui/css/layui.css">
</head>
<body>
<div class="layui-container">
    <blockquote class="layui-elem-quote layui-text">
        <%
            Integer rows = Integer.parseInt(request.getParameter("rows"));
        %>
        <%=rows!=0? "添加成功":"添加失败"%>
    </blockquote>

</div>
<script src="layui/layui.all.js"></script>
</body>
</html>
