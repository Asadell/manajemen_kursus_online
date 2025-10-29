package repository;

import exception.*;
import model.Course;
import exception.EntityNotFoundException;
import java.util.*;

public class CourseRepository implements Repository<Course> {
    private HashMap<String, Course> courses;
    
    public CourseRepository() {
        this.courses = new HashMap<>();
    }
    
    @Override
    public void add(Course course) {
        courses.put(course.getId(), course);
    }
    
    @Override
    public Course findById(String id) throws EntityNotFoundException {
        Course course = courses.get(id);
        if (course == null) {
            throw new EntityNotFoundException("Kursus dengan ID " + id + " tidak ditemukan.");
        }
        return course;
    }
    
    @Override
    public List<Course> getAll() {
        return new ArrayList<>(courses.values());
    }
    
    @Override
    public void remove(String id) throws EntityNotFoundException {
        if (!courses.containsKey(id)) {
            throw new EntityNotFoundException("Kursus dengan ID " + id + " tidak ditemukan.");
        }
        courses.remove(id);
    }
}
