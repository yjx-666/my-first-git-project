package org.example.maven.dao;

//dao专门用来存储对数据库的操作

import org.example.maven.entity.Student;
import org.example.maven.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {

    public static List<Student> getStudents(){
        List<Student> students = new ArrayList<Student>();
        Connection connection = DBUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareCall("select * from stu_info");
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                String no = resultSet.getString("stu_no");
                String name = resultSet.getString("stu_name");
                Student student = new Student();
                student.setStuNo(no);
                student.setStuName(name);
                students.add(student);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        DBUtil.close(resultSet, statement, connection);
        return students;
    }
}

