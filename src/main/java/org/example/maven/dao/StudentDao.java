package org.example.maven.dao;

//dao专门用来存储对数据库的操作

import org.example.maven.entity.Student;
import org.example.maven.utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.example.maven.utils.DBUtil.close;
import static org.example.maven.utils.DBUtil.getConnection;

public class StudentDao {
    private static Connection connection = null;
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;

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
            connection = getConnection();
            String  sql = "select * from stu_info where stu_no = ?";//防止sql注入

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



    public static List<Student> getStudents(){
        /*返回Student类型的对象的列表*/
        List<Student> students = new ArrayList<Student>();
        connection = getConnection();
        preparedStatement = null;
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
        close(resultSet, preparedStatement, connection);
        return students;
    }

    public static int deleteStudentById(String no) {
        int state = 0;
        try{
            connection = getConnection();
            String sql = "delete from Stu_info where stu_no = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,no);
            state = preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }

        if(state!=0){
            System.out.println("删除学号为 " + no + " 的学生成功");
        }else
            System.out.println("不存在学号为 " + no + " 的学生");
        return state;
    }


    public static int addStudent(String name,String sex,String stuClass,String stuAddress,String stuPhoneNUmber){
        connection = getConnection();
        int result = 0;
        try{
            String sql = "insert into stu_info(stu_name,stu_sex,stu_class,stu_address,stu_phonenumber) values (?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,sex);
            preparedStatement.setString(3,stuClass);
            preparedStatement.setString(4,stuAddress);
            preparedStatement.setString(5,stuPhoneNUmber);
            result = preparedStatement. executeUpdate();
            if(result != 0){
                System.out.println("学生 " + name + " 的数据插入数据库成功！");
            }else
                System.out.println("学生 " + name + " 的数据插入数据库失败！");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(resultSet,preparedStatement,connection);
        }

        return result;
    }

   /* public static void alterStudent(String no, String name, String sex, String stuClass){
        connection = getConnection();
        try{
            String sql = "update stu_info_3 set stu_name = ?,stu_sex = ? ,stu_class = ?  where stu_no = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,sex);
            preparedStatement.setString(3,stuClass);
            preparedStatement.setString(4,no);
            int result = preparedStatement. executeUpdate();
            if(result != 0){
                System.out.println("修改学生 " + no +" 的数据成功！");
            }else
                System.out.println("不存在学号为 " + no + " 的学生，修改数据失败！");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(resultSet,preparedStatement,connection);
        }
    }*/

    public static void main(String[] args){
//        Student studentById = getStudentById("1877000152");
//        System.out.println(studentById);

/*        int i = deleteStudentById("1877000072");
        System.out.println(i);*/

//        System.out.println(addStudent("22","w2","22","22","22"));


    }
}

