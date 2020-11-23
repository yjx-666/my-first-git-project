package org.example.maven.dao;

//dao专门用来存储对数据库的操作

import org.example.maven.entity.Student;
import org.example.maven.utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {

    public static Student getStudent(ResultSet resultSet) throws SQLException{
        /*根据数据库中查询出来的信息获得一个学生对象*/
            String no = resultSet.getString("stu_no");
            String name = resultSet.getString("stu_name");
            String sex = resultSet.getString("stu_sex");
            String address = resultSet.getString("stu_address");
            String phone = resultSet.getString("stu_phonenumber");
            String stuClass = resultSet.getString("stu_class");
            Student student = new Student(no,name,sex,stuClass,address,phone);
            return student;
    }

        public static Student getStudentById(String no) throws SQLException {
        /*根据学生的学号查询学生的信息最后得到一个学生对象*/
            Connection connection = DBUtil.getConnection();
            String  sql = "select * from stu_info where stu_no = ?";//防止sql注入
            PreparedStatement preparedStatement;
            ResultSet resultSet = null;

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,no);//设置第一个问号为学号no
            resultSet = preparedStatement.executeQuery();
            /*
            *  ResultSet executeQuery() throws SQLException;
            * 注意返回值为一个结果集*/

            Student student = null;
            if(resultSet.next()){
                student = getStudent(resultSet);
            }
            return student;
    }

    public static void deleteStudentById(String no) throws SQLException {
        Connection connection = DBUtil.getConnection();
        String sql = "delete from Stu_info where stu_no = " + no;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        int i = preparedStatement.executeUpdate();
        if(i==1){
            System.out.println("删除学号为 " + no + " 的学生成功");
        }else
            System.out.println("不存在学号为 " + no + " 的学生");
    }


    public static List<Student> getStudents(){
        /*返回Student类型的对象的列表*/
        List<Student> students = new ArrayList<Student>();
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareCall("select * from stu_info");
            resultSet = preparedStatement.executeQuery();
            Student student = null;
            while (resultSet.next()){
                /*String no = resultSet.getString("stu_no");
                student = getStudentById(no);
                students.add(student);*/
                String no = resultSet.getString("stu_no");
                String name = resultSet.getString("stu_name");
                String sex = resultSet.getString("stu_sex");
                String address = resultSet.getString("stu_address");
                String phone = resultSet.getString("stu_phonenumber");
                String stuClass = resultSet.getString("stu_class");
                student = new Student(no,name,sex,stuClass,address,phone);
                students.add(student);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        DBUtil.close(resultSet, preparedStatement, connection);
        return students;
    }

    public static void main(String[] args) throws SQLException {
        deleteStudentById("1777000080");
    }
}

