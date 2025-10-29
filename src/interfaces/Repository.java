package interfaces;

import exception.EntityNotFoundException;
import java.util.List;

public interface Repository<T> {
    void add(T entity);
    T findById(String id) throws EntityNotFoundException;
    List<T> getAll();
    void remove(String id) throws EntityNotFoundException;
}