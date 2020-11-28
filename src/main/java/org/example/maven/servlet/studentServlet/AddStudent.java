package org.example.maven.servlet.studentServlet;

import com.mysql.cj.util.StringUtils;
import org.example.maven.dao.StudentDao;
import org.example.maven.entity.Student;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addStudent")
public class AddStudent extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

//        String no = req.getParameter("no");//绑定的是input标签里面的name属性
        String name = req.getParameter("name");
        String sex = req.getParameter("sex");
        String stuClass = req.getParameter("class");
        String address = req.getParameter("address");
        String phone = req.getParameter("phone");

        Student student  = new Student();

        if(!StringUtils.isNullOrEmpty(name)){
            student.setStuName(name);
            student.setStuAddress(address);
            student.setStuClass(stuClass);
            student.setStuSex(sex);
            student.setStuPhoneNumber(phone);
        }

        int rows = StudentDao.addStudent(student);
        resp.setContentType("text/html");
        resp.sendRedirect("addStudentState.jsp?rows=" + rows);
    }
}
