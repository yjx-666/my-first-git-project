package org.example.maven.entity;

//entity专门放实体类
public class Student {
    private String stuNo;
    private String stuName;
    private String stuSex;
    private String stuClass;
    private String stuAddress;

    @Override
    public String toString() {
        return "Student{" +
                "stuNo='" + stuNo + '\'' +
                ", stuName='" + stuName + '\'' +
                ", stuSex='" + stuSex + '\'' +
                ", stuClass='" + stuClass + '\'' +
                ", stuAddress='" + stuAddress + '\'' +
                ", stuPhoneNumber='" + stuPhoneNumber + '\'' +
                '}';
    }

    private String stuPhoneNumber;


    public String getStuClass() {
        return stuClass;
    }

    public void setStuClass(String stuClass) {
        this.stuClass = stuClass;
    }

    public String getStuPhoneNumber() {
        return stuPhoneNumber;
    }

    public void setStuPhoneNumber(String stuPhoneNumber) {
        this.stuPhoneNumber = stuPhoneNumber;
    }

    public String getStuAddress() {
        return stuAddress;
    }

    public void setStuAddress(String stuAddress) {
        this.stuAddress = stuAddress;
    }

    public Student(String stuNo, String stuName, String stuSex, String stuClass, String stuAddress, String stuPhoneNumber) {
        this.stuNo = stuNo;
        this.stuName = stuName;
        this.stuSex = stuSex;
        this.stuClass = stuClass;
        this.stuAddress = stuAddress;
        this.stuPhoneNumber = stuPhoneNumber;
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

