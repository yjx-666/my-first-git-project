package com.yjx.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ContextServlet1 extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        获取servletContext对象的两种方法
//        ServletContext servletContext = getServletConfig().getServletContext();
//        servletContext对象是一个域对象（可以像map一样存取数据的对象）
//        域指的是存取数据的操作范围，是整个web工程
        ServletContext servletContext = getServletContext();

//重新部署和重启都会销毁servletContext对象对象
        System.out.println("部署前context1中获取域数据key1的值是: " + servletContext.getAttribute("key1"));
        servletContext.setAttribute("key1","value1");
        servletContext.setAttribute("key2","value2");
        System.out.println("context1中获取域数据key1的值是: " + servletContext.getAttribute("key1"));
        System.out.println("context1中获取域数据key1的值是: " + servletContext.getAttribute("key1"));
        System.out.println("context1中获取域数据key2的值是: " + servletContext.getAttribute("key2"));
        System.out.println();
    }
}
