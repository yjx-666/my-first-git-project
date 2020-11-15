package org.example.maven.test;

import org.example.maven.utils.DBUtil;

import java.sql.*;

public class JDBCTest {
    public static void jdbcTest(){
        //加载数据库驱动
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");//反射
            //?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8避免时区问题和乱码
            //student_info为数据库名
            String url = "jdbc:mysql://localhost:3306/student_info?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8";
            String username = "root";
            String password = "3431";
            Connection connection = DriverManager.getConnection(url,username,password);
            String sql = "select * from stu_info";
            PreparedStatement statement = connection.prepareCall(sql);
            //int i = statement.executeUpdate();仅用于Delete，Update，Insert
            ResultSet resultSet = statement.executeQuery();//仅用于Select
            while (resultSet.next()){
                //column：列，zong
                String no = resultSet.getString(1);//相当于String no = resultSet.getString("stu_no");
                String name = resultSet.getString("stu_name");

                System.out.println("no : " + no + " \tname : " + name);
            }

            //关闭所有连接，倒序关闭,顺序不能倒
            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //jdbcTest2与jdbcTest效果一样但是更加健壮简洁
    private static void jdbcTest2() throws SQLException {
        Connection connection = DBUtil.getConnection();
        PreparedStatement statement = connection.prepareCall("select * from stu_info");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next())
        {
            String no = resultSet.getString(1);//相当于String no = resultSet.getString("stu_no");
            String name = resultSet.getString("stu_name");

            System.out.println("no : " + no + " \tname : " + name);
        }
        DBUtil.close(resultSet, statement, connection);
    }

    public static void main(String[] args){
        //jdbcTest();
        System.out.println();
        try {
            jdbcTest2();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
