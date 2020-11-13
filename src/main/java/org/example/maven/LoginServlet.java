package org.example.maven;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String name = req.getParameter("username");
        String password = req.getParameter("password");
        //获取session对象
        if(name != null && password != null){
            HttpSession session = req.getSession();
            session.setAttribute("name",name);
            session.setAttribute("password",password);
        //登录成功则跳转到某一个页面
            resp.sendRedirect(req.getContextPath() + "/admini.jsp");
        }
    }
}
