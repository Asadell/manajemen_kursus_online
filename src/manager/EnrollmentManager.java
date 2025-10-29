package manager;

import java.util.List;
import model.*;
import exception.DuplicateEnrollmentException;
import java.util.*;

public class EnrollmentManager {
    private List<Enrollment> enrollments;
    
    public EnrollmentManager() {
        this.enrollments = new ArrayList<>();
    }
    
    public void addEnrollment(Enrollment enrollment) throws DuplicateEnrollmentException {
        if (isAlreadyEnrolled(enrollment.getStudent(), enrollment.getCourse())) {
            throw new DuplicateEnrollmentException("Mahasiswa sudah terdaftar di kursus ini.");
        }
        enrollments.add(enrollment);
    }
    
    public List<Enrollment> getAllEnrollments() {
        return new ArrayList<>(enrollments);
    }
    
    public List<Enrollment> getEnrollmentsByStudent(Student student) {
        List<Enrollment> result = new ArrayList<>();
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStudent().getId().equals(student.getId())) {
                result.add(enrollment);
            }
        }
        return result;
    }
    
    public boolean isAlreadyEnrolled(Student student, Course course) {
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStudent().getId().equals(student.getId()) &&
                enrollment.getCourse().getId().equals(course.getId()) &&
                enrollment.getStatus() == EnrollmentStatus.ACTIVE) {
                return true;
            }
        }
        return false;
    }
    
    public double calculateTotalRevenue() {
        double total = 0;
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStatus() == EnrollmentStatus.ACTIVE) {
                total += enrollment.getCourse().getPrice();
            }
        }
        return total;
    }
}
