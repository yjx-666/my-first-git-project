<%--
  Created by IntelliJ IDEA.
  User: y
  Date: 2020/10/30
  Time: 9:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh_CN">
<head>
    <title>Never,Never Give Up</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<h1>inevitable</h1>


<%
    String s1 = "Never,Never Give Up ";
    String s2 = "Better late than never ";
    out.print("    <h2>" + s1 + s2 + "</h2>");
%>
<a href="https://www.runoob.com/" target="_blank">访问菜鸟教程!</a>

<p>如果你将 target 属性设置为 &quot;_blank&quot;, 链接将在新窗口打开。</p>

<jsp:include page="footer.jsp"/>
</body>
</html>
