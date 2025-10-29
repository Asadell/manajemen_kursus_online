package model;

import java.util.Date;

import interfaces.Displayable;

public class Enrollment implements Displayable {
    private Student student;
    private Course course;
    private EnrollmentStatus status;
    private Date enrollmentDate;
    
    public Enrollment(Student student, Course course) {
        this.student = student;
        this.course = course;
        this.status = EnrollmentStatus.ACTIVE;
        this.enrollmentDate = new Date();
    }
    
    public Student getStudent() {
        return student;
    }
    
    public Course getCourse() {
        return course;
    }
    
    public EnrollmentStatus getStatus() {
        return status;
    }
    
    public void setStatus(EnrollmentStatus status) {
        this.status = status;
    }
    
    public Date getEnrollmentDate() {
        return enrollmentDate;
    }
    
    @Override
    public void displayInfo() {
        System.out.println("Mahasiswa: " + student.getName());
        System.out.println("Kursus: " + course.getName());
        System.out.println("Status: " + status);
        System.out.println("Tanggal Daftar: " + enrollmentDate);
    }
}
