<%@ page contentType="text/html;charset=UTF-8" autoFlush="true" pageEncoding="utf-8" %>
<html lang="java">
<head>
    <meta charset="utf-8">
    <title>登入</title>
    <link rel="stylesheet" href="layui/css/layui.css">
    <link rel="stylesheet" href="css/login.css">
    <script src="layui/layui.js"></script>
</head>
<body>
<div class="login-main">
    <header class="layui-elip">登录</header>
    <%--    以下几种方法都行--%>
    <form class="layui-form" action="login" method="post">
        <%--  <form class="layui-form" action="http://localhost:8080/maven/login" method="post">--%>
        <%-- action="login"不能加上斜杠，否则跳到http://localhost:8080/login --%>
        <form class="layui-form" action="login" method="post">
            <div class="layui-input-inline">
                <label>
                    <input type="text" name="username" required placeholder="用户名" class="layui-input">
                </label>
            </div>
            <div class="layui-input-inline">
                <label>
                    <input type="password" name="password" required  placeholder="密码" class="layui-input">
                </label>
            </div>
            <div class="layui-input-inline login-btn">
                <button class="layui-btn">登录</button>
            </div>
            <hr/>
            <p><a href="" class="fl">立即注册</a><a href="" class="fr">忘记密码？</a></p>
        </form>
</div>



</body>
</html>
