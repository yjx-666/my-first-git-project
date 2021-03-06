package org.example.maven.servlet.studentServlet;

import com.google.gson.Gson;
import org.example.maven.dao.StudentDao;
import org.example.maven.entity.BaseResponse;
import org.example.maven.entity.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
@WebServlet("/all/students")
public class StudentAllServlet extends HttpServlet {
    public StudentAllServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        List<Student> students = StudentDao.getStudents();
        BaseResponse<List<Student>> baseResponse = new BaseResponse<List<Student>>();
        baseResponse.setCode(200);
        baseResponse.setMsg("请求成功");
        baseResponse.setData(students);

        Gson gson = new Gson();
        String json = gson.toJson(baseResponse);
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.print(json);
        out.flush();
    }
}
