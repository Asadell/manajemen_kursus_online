package model;

public class Instructor extends Person {
    private String specialization;
    
    public Instructor(String id, String name, String email, String specialization) {
        super(id, name, email);
        this.specialization = specialization;
    }
    
    public String getSpecialization() {
        return specialization;
    }
    
    @Override
    public void displayInfo() {
        System.out.println("ID: " + getId());
        System.out.println("Nama: " + getName());
        System.out.println("Email: " + getEmail());
        System.out.println("Spesialisasi: " + specialization);
    }
}
