package org.example.maven.entity;

//entity专门放实体类
public class Student {
    private String stuNo;
    private   String stuName;

    @Override
    public String toString() {
        return "Student{" +
                "stuNo='" + stuNo + '\'' +
                ", stuName='" + stuName + '\'' +
                ", stuSex='" + stuSex + '\'' +
                '}';
    }

    public String getStuSex() {
        return stuSex;
    }

    public void setStuSex(String stuSex) {
        this.stuSex = stuSex;
    }

    public Student(String stuNo, String stuName, String stuSex) {
        this.stuNo = stuNo;
        this.stuName = stuName;
        this.stuSex = stuSex;
    }

    private   String stuSex;

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuNo() {
        return stuNo;
    }

    public String getStuName() {
        return stuName;
    }

    public Student() {}

    public Student(String stuNo, String stuName) {
        this.stuNo = stuNo;
        this.stuName = stuName;
    }
}

