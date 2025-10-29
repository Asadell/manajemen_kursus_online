package model;

public class OfflineCourse extends Course {
    private String location;
    
    public OfflineCourse(String id, String name, double price, Instructor instructor, String location) {
        super(id, name, price, instructor);
        this.location = location;
    }
    
    public String getLocation() {
        return location;
    }
    
    @Override
    public String getCourseType() {
        return "Offline";
    }
    
    @Override
    public void displayInfo() {
        System.out.println("ID: " + getId());
        System.out.println("Nama: " + getName());
        System.out.println("Tipe: " + getCourseType());
        System.out.println("Harga: Rp " + getPrice());
        System.out.println("Instruktur: " + getInstructor().getName());
        System.out.println("Lokasi: " + location);
    }
}
