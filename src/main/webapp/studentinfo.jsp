    <%@ page import="org.example.maven.entity.Student" %>
<%@ page import="org.example.maven.dao.StudentDao" %><%--
  Created by IntelliJ IDEA.
  User: y
  Date: 2020/11/20
  Time: 9:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%--isELIgnored="false"设置不忽略el表达式--%>
<html>
<head>
    <title>学生信息</title>
    <link rel="stylesheet" href="layui/css/layui.css">
</head>
<body>
<div class="layui-container">
    <blockquote class="layui-elem-quote layui-text">
        Student查询结果(只读)：
    </blockquote>

    <form class="layui-form" action="" >

        <div class="layui-form-item">

            <div class="layui-inline">
                <label class="layui-form-label">学号</label>
                <div class="layui-input-inline">
                    <label>
                        <input type="text" name="stuNo"  class="layui-input" value=${empty student? "请输入学号查询" : student.stuNo}  readonly>
                    </label>
                </div>
            </div>

            <div class="layui-inline">
                <label class="layui-form-label">姓名</label>
                <div class="layui-input-inline">
                    <label>
                        <input type="tel" name="stuName" class="layui-input" value=${empty student? "请输入学号查询" : student.stuName} readonly>
                    </label>
                </div>
            </div>

            <div class="layui-inline">
                <label class="layui-form-label">性别</label>
                <div class="layui-input-inline">
                    <label>
                        <input type="tel" name="stuSex" class="layui-input" value=${empty student? "请输入学号查询" : student.stuSex} readonly>
                    </label>
                </div>
            </div>

           </div>
    </form>

</div>
<table align="center">
    <tr>
        <td>    <button type="button" class="layui-btn layui-btn-fluid"   onclick="window.location.href='searchstudent.jsp'">重新查询</button></td>
    </tr>
<br/>
<br/>
<br/>
</table>
<script src="layui/layui.all.js"></script>
</body>
</html>
</html>