<%--
  Created by IntelliJ IDEA.
  User: y
  Date: 2020/11/20
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" autoFlush="true" pageEncoding="utf-8" %>
<html lang="java">
<head>
    <meta charset="utf-8">
    <title>查询学生信息</title>
    <link rel="stylesheet" href="layui/css/layui.css">
    <link rel="stylesheet" href="css/login.css">
</head>
<body>

<div class="login-main">
    <header class="layui-elip">查询学生信息</header>
    <%--    以下几种方法都行--%>

        <form class="layui-form" action="searchstudent" method="post">
            <div class="layui-input-inline">
                <label>
                    <input type="text" name="stuNo" required placeholder="请输入学号" class="layui-input">
                </label>
            </div>

            <div class="layui-input-inline login-btn">
                <button class="layui-btn">查询</button>
            </div>
        </form>
</div>

<script src="layui/layui.js"></script>
</body>
</html>
