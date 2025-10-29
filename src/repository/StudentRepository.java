package repository;

import model.Student;
import exception.EntityNotFoundException;
import interfaces.Repository;

import java.util.*;

public class StudentRepository implements Repository<Student> {
    private HashMap<String, Student> students;
    
    public StudentRepository() {
        this.students = new HashMap<>();
    }
    
    @Override
    public void add(Student student) {
        students.put(student.getId(), student);
    }
    
    @Override
    public Student findById(String id) throws EntityNotFoundException {
        Student student = students.get(id);
        if (student == null) {
            throw new EntityNotFoundException("Mahasiswa dengan ID " + id + " tidak ditemukan.");
        }
        return student;
    }
    
    @Override
    public List<Student> getAll() {
        return new ArrayList<>(students.values());
    }
    
    @Override
    public void remove(String id) throws EntityNotFoundException {
        if (!students.containsKey(id)) {
            throw new EntityNotFoundException("Mahasiswa dengan ID " + id + " tidak ditemukan.");
        }
        students.remove(id);
    }
}
