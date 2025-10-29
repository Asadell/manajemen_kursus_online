package repository;

import model.Instructor;
import exception.EntityNotFoundException;
import interfaces.Repository;

import java.util.*;

public class InstructorRepository implements Repository<Instructor> {
    private HashMap<String, Instructor> instructors;
    
    public InstructorRepository() {
        this.instructors = new HashMap<>();
    }
    
    @Override
    public void add(Instructor instructor) {
        instructors.put(instructor.getId(), instructor);
    }
    
    @Override
    public Instructor findById(String id) throws EntityNotFoundException {
        Instructor instructor = instructors.get(id);
        if (instructor == null) {
            throw new EntityNotFoundException("Instruktur dengan ID " + id + " tidak ditemukan.");
        }
        return instructor;
    }
    
    @Override
    public List<Instructor> getAll() {
        return new ArrayList<>(instructors.values());
    }
    
    @Override
    public void remove(String id) throws EntityNotFoundException {
        if (!instructors.containsKey(id)) {
            throw new EntityNotFoundException("Instruktur dengan ID " + id + " tidak ditemukan.");
        }
        instructors.remove(id);
    }
}
