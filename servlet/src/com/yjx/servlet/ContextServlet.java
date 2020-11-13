package com.yjx.servlet;

import javax.naming.Context;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ContextServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//       1.获取web.xml中配置的上下文参数context-param
//        获取servletContext对象的两种方法
        ServletContext servletContext = getServletConfig().getServletContext();
//        ServletContext servletContext = getServletContext();
        String username = servletContext.getInitParameter("username");
        System.out.println("context-param参数username的值是: " + username);
        System.out.println("context-param参数password的值是: " + servletContext.getInitParameter("password"));
//       2.获取当前的工程路径，格式：/工程路径
        System.out.println("当前的工程路径是 :" + servletContext.getContextPath());
//      3.获取工程部署后在服务器上硬盘上的绝对路径
        /**
         * /斜杠在服务器解析的时候，表示地址为：http://ip:端口/工程路径，映射到IDEA代码的web目录
         */
        System.out.println("工程部署的路径是 ：" +  servletContext.getRealPath("/"));
        System.out.println("img1.png部署的路径是 ：" +  servletContext.getRealPath("/img/img1.png"));
        System.out.println();
    }
}
