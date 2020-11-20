package org.example.maven.dao;

//dao专门用来存储对数据库的操作

import org.example.maven.entity.Student;
import org.example.maven.utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {

    public static Student getStudent(ResultSet resultSet) throws SQLException{
            String no = resultSet.getString("stu_no");
            String name = resultSet.getString("stu_name");
            Student student = new Student(no,name);
            return student;
    }

        public static Student getStudentById(String no) throws SQLException {
            Connection connection = DBUtil.getConnection();
            String  sql = "select * from stu_info where stu_no = ?";
            PreparedStatement preparedStatement;
            ResultSet resultSet = null;

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,no);
            resultSet = preparedStatement.executeQuery();//    ResultSet executeQuery() throws SQLException;

            Student student = null;
            if(resultSet != null && resultSet.next()){
                student = getStudent(resultSet);
            }
            return student;
    }

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

    public static void main(String[] args) throws SQLException {
        Student student = getStudentById("1877000032ddd");
        System.out.println(student);
    }
}

