<%--
  Created by IntelliJ IDEA.
  User: y
  Date: 2020/11/28
  Time: 8:31
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
        学生详情：
    </blockquote>

    <form class="layui-form" action="updateStudent" method="post">

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">学号</label>
                <div class="layui-input-inline">
                    <input id="stu_no" name="no" readonly class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">姓名</label>
                <div class="layui-input-inline">
                    <input id="stu_name" name="name"  class="layui-input">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">性别</label>
                <div class="layui-input-inline">
                    <input id="stu_sex" name="sex"  class="layui-input">
                </div>
            </div>

            <div class="layui-inline">
                <label class="layui-form-label">班级</label>
                <div class="layui-input-inline">
                    <input id="stu_class" name="class"  class="layui-input">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">地址</label>
                <div class="layui-input-inline">
                    <input id="stu_address" name="address"  class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">电话</label>
                <div class="layui-input-inline">
                    <input id="stu_phone" name="phone"  class="layui-input">
                </div>
            </div>
        </div>

        <div class="layui-input-inline login-btn">
            <button class="layui-btn">提交修改</button>
        </div>

    </form>

</div>
<script src="layui/layui.all.js"></script>
</body>
</html>

