package model;

import interfaces.Displayable;

public abstract class Course implements Displayable {
    private String id;
    private String name;
    private double price;
    private Instructor instructor;
    
    public Course(String id, String name, double price, Instructor instructor) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.instructor = instructor;
    }
    
    public String getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public double getPrice() {
        return price;
    }
    
    public Instructor getInstructor() {
        return instructor;
    }
    
    public abstract String getCourseType();
}
