package com.yjx.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloServlet3 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("HelloServlet3的post请求");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        获取servletContext对象
        ServletContext servletContext = getServletContext();

        System.out.println("HelloServlet3的get请求");
        System.out.println("context1中获取域数据key1的值是: " + servletContext.getAttribute("key1"));
        System.out.println("context1中获取域数据key2的值是: " + servletContext.getAttribute("key2"));
        System.out.println();
    }
}
