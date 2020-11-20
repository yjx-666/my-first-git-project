package org.example.maven.servlet.studentServlet;

import org.example.maven.dao.StudentDao;
import org.example.maven.entity.Student;
import org.example.maven.utils.DBUtil;

import java.sql.*;
import java.util.List;

public class JDBCTest {
    public static void jdbcTest(){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        //加载数据库驱动
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");//反射
            //?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8避免时区问题和乱码
            //student_info为数据库名
            String url = "jdbc:mysql://localhost:3306/student_info?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8";
            String username = "root";
            String password = "3431";
            connection = DriverManager.getConnection(url,username,password);
            String sql = "select * from stu_info";
            statement = connection.prepareCall(sql);
            //int i = statement.executeUpdate();仅用于Delete，Update，Insert，执行成功返回1
            resultSet = statement.executeQuery();//仅用于Select，ResultSet executeQuery() throws SQLException;
            while (resultSet.next()){
                //column：列，zong
                String no = resultSet.getString(1);
                String name = resultSet.getString("stu_name");
                String sex = resultSet.getString("stu_sex");

                System.out.println("Student{" +
                        "stuNo='" + no + '\'' +
                        ", stuName='" + name + '\'' +
                        ", stuSex='" + sex + '\'' +
                        '}');
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //关闭所有连接，倒序关闭,顺序不能倒
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            }  catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    //jdbcTest2与jdbcTest效果一样但是更加健壮简洁
    private static void jdbcTest2() throws SQLException {
        Connection connection = DBUtil.getConnection();
        PreparedStatement statement = connection.prepareCall("select * from stu_info ");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next())
        {
            //相当于String no = resultSet.getString("stu_no");打印第一列
            String no = resultSet.getString(1);
            String name = resultSet.getString("stu_name");
            String sex = resultSet.getString("stu_sex");

            System.out.println("Student{" +
                    "stuNo='" + no + '\'' +
                    ", stuName='" + name + '\'' +
                    ", stuSex='" + sex + '\'' +
                    '}');
        }
        DBUtil.close(resultSet, statement, connection);
    }

    public static void main(String[] args) throws SQLException{
        //第一种方法
        jdbcTest();
        //第二种方法，异常通过throws上抛
      //jdbcTest2();
        //第三种方法
        List<Student> students = StudentDao.getStudents();//获得Student类型的对象的列表
        for (Student student : students){
            System.out.println("Student{" +
                    "stuNo='" + student.getStuNo() + '\'' +
                    ", stuName='" + student.getStuName() + '\'' +
                    ", stuSex='" + student.getStuSex() + '\'' +
                    '}');
        }
    }
}
