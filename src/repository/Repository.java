package repository;

import java.util.List;
import exception.EntityNotFoundException;

public interface Repository<T> {
    void add(T entity);
    T findById(String id) throws EntityNotFoundException;
    List<T> getAll();
    void remove(String id) throws EntityNotFoundException;
}
