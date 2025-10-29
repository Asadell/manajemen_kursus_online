package model;

public class OnlineCourse extends Course {
    private String meetingLink;
    
    public OnlineCourse(String id, String name, double price, Instructor instructor, String meetingLink) {
        super(id, name, price, instructor);
        this.meetingLink = meetingLink;
    }
    
    public String getMeetingLink() {
        return meetingLink;
    }
    
    @Override
    public String getCourseType() {
        return "Online";
    }
    
    @Override
    public void displayInfo() {
        System.out.println("ID: " + getId());
        System.out.println("Nama: " + getName());
        System.out.println("Tipe: " + getCourseType());
        System.out.println("Harga: Rp " + getPrice());
        System.out.println("Instruktur: " + getInstructor().getName());
        System.out.println("Link Meeting: " + meetingLink);
    }
}
