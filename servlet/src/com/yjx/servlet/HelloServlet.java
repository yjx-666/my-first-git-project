package com.yjx.servlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class HelloServlet implements Servlet {
    private int i;

    public HelloServlet() {
        System.out.println("1 构造器方法被调用了");
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("2 初始化方法init被调用了");
//可以获取Servlet程序的别名servlet-name的值
        System.out.println("HelloServlet程序的别名是： " + servletConfig.getServletName());
//获取初始化参数init-param
        System.out.println("初始化参数username的值是： " + servletConfig.getInitParameter("username"));
        System.out.println("初始化参数tomcat-url的值是： " + servletConfig.getInitParameter("tomcat-url"));
//获取ServletContext对象
        System.out.println("获取的ServletContext对象是： " + servletConfig.getServletContext());
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    //service专门来处理请求和响应的
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        //类型转换，因为HttpServletRequest里有getMethod方法
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        System.out.println("3 HelloServlet类的service方法被调用了" + (++i) + "次");
        //客户端调用后服务端返回被调用的信息
        String method = httpServletRequest.getMethod(); //获取请求的方式
        if("GET".equals(method)){
            doGet();
        }else if ("POST".equals(method)){
            doPost();
        }
    }

    //做get的请求操作
    public void doGet(){
        System.out.println("get请求");
    }

    //做post的请求操作
    public void doPost(){
        System.out.println("post请求");
    }
    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println();
        System.out.println("4 销毁方法被调用了");
        System.out.println();
    }
}
