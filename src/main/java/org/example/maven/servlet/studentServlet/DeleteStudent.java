package org.example.maven.servlet.studentServlet;

import com.google.gson.Gson;
import org.example.maven.entity.BaseResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


import static org.example.maven.dao.StudentDao.deleteStudentById;

@WebServlet("/stu/delete")
public class DeleteStudent extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String no = req.getParameter("no");
        int rows = deleteStudentById(no);
        BaseResponse<Integer> baseResponse = new BaseResponse<Integer>();
        if(rows > 0){
            baseResponse.setCode(200);
            baseResponse.setMsg("操作成功");
        }else{
            baseResponse.setCode(600);
            baseResponse.setMsg("操作失败");
        }
        baseResponse.setData(rows);

        Gson gson = new Gson();
        String json = gson.toJson(baseResponse);
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.println(json);
        out.flush();
    }
}
