package org.example.maven.servlet.studentServlet;

import org.example.maven.dao.StudentDao;
import org.example.maven.entity.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/searchstudent")
public class SerachStudentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String stuNo = req.getParameter("stuNo");
        Student student = null;
        student = StudentDao.getStudentById(stuNo);

        if(student != null && stuNo.equals(student.getStuNo())){
            //stuNo都是字符串类型，双等号比较的是引用地址是否相同，字符串比较用equals方法，谨记！
                HttpSession session = req.getSession();//获取session对象
                session.setAttribute("student", student);
                //登录成功则跳转到某一个页面,通过session来存数据，转发和重定向都可以
                resp.sendRedirect("studentinfo.jsp");
        }else
            resp.sendRedirect("searchstudent.jsp");

    }
}
