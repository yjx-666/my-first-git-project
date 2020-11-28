package org.example.maven.dao;

//dao专门用来存储对数据库的操作

import com.mysql.cj.util.StringUtils;
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

    public static Student getStudent(ResultSet resultSet){
        /*根据数据库中查询出来的信息获得一个学生对象*/
        Student student = null;
        try{
            String no = resultSet.getString("stu_no");
            String name = resultSet.getString("stu_name");
            String sex = resultSet.getString("stu_sex");
            String address = resultSet.getString("stu_address");
            String phone = resultSet.getString("stu_phonenumber");
            String stuClass = resultSet.getString("stu_class");
            student = new Student(no,name,sex,stuClass,address,phone);
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return student;
    }

        public static Student getStudentById(String no){
        /*根据学生的学号查询学生的信息最后得到一个学生对象*/
            connection = getConnection();
            Student student = null;
            try{
                String  sql = "select * from stu_info where stu_no = ?";//防止sql注入
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1,no);//设置第一个问号为学号no
                resultSet = preparedStatement.executeQuery();
                /*
                 *  ResultSet executeQuery() throws SQLException;
                 * 注意返回值为一个结果集*/


                if(resultSet.next()){
                    student = getStudent(resultSet);
                }
            }catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                close(resultSet,preparedStatement,connection);
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
        }finally {
            close(resultSet,preparedStatement,connection);
        }

        if(state!=0){
            System.out.println("删除学号为 " + no + " 的学生成功");
        }else
            System.out.println("不存在学号为 " + no + " 的学生");
        return state;
    }


    public static int addStudent(Student student){
        connection = getConnection();
        int result = 0;
        try{
            String sql = "insert into stu_info(stu_name,stu_sex,stu_class,stu_address,stu_phonenumber) values (?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,student.getStuName());
            preparedStatement.setString(2, student.getStuSex());
            preparedStatement.setString(3, student.getStuClass());
            preparedStatement.setString(4,student.getStuAddress());
            preparedStatement.setString(5,student.getStuPhoneNumber());
            result = preparedStatement. executeUpdate();
            if(result != 0){
                System.out.println("学生 " + student.getStuName() + " 的数据插入数据库成功！");
            }else
                System.out.println("学生 " + student.getStuName() + " 的数据插入数据库失败！");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(resultSet,preparedStatement,connection);
        }

        return result;
    }

        public static int updateStudent(Student student){
        connection = getConnection();
            int result = 0;
            try{
            String sql = "update stu_info set stu_name = ?,stu_sex = ? ,stu_class = ? ,stu_address = ? ,stu_phonenumber = ? where stu_no = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,student.getStuName());
            preparedStatement.setString(2, student.getStuSex());
            preparedStatement.setString(3, student.getStuClass());
            preparedStatement.setString(4,student.getStuAddress());
            preparedStatement.setString(5,student.getStuPhoneNumber());
            preparedStatement.setString(6,student.getStuNo());
            result = preparedStatement. executeUpdate();
            if(result != 0){
                System.out.println("修改学生 " + student.getStuNo() +" 的数据成功！");
            }else
                System.out.println("不存在学号为 " + student.getStuNo() + " 的学生，修改数据失败！");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(resultSet,preparedStatement,connection);
        }
            return result;
    }

    public static void main(String[] args){
//        Student studentById = getStudentById("1877000152");
//        System.out.println(studentById);

/*        int i = deleteStudentById("1877000072");
        System.out.println(i);*/

//        System.out.println(addStudent("22","w2","22","22","22"));

/*        Student student = StudentDao.getStudentById("21");
        System.out.println(student);

            student.setStuName("1111");
            student.setStuAddress("1111");
            student.setStuClass("1111");
            student.setStuSex("1111");
            student.setStuPhoneNumber("1111");

        int i = updateStudent(student);
        System.out.println(i);
        System.out.println(student);*/

        Student student = new Student();
        student.setStuName("22");
        student.setStuAddress("1111");
        student.setStuClass("1111");
        student.setStuSex("1111");
        student.setStuPhoneNumber("1111");
        int i = addStudent(student);
        System.out.println(i);
    }
}

