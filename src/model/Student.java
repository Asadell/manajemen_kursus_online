package model;

public class Student extends Person {
    private String major;
    
    public Student(String id, String name, String email, String major) {
        super(id, name, email);
        this.major = major;
    }
    
    public String getMajor() {
        return major;
    }
    
    @Override
    public void displayInfo() {
        System.out.println("ID: " + getId());
        System.out.println("Nama: " + getName());
        System.out.println("Email: " + getEmail());
        System.out.println("Jurusan: " + major);
    }
}
