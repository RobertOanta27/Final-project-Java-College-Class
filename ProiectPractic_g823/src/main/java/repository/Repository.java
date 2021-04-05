package repository;

import java.util.Collection;

public interface Repository <ID, T>{
    T add(T elem);
    T findByID(ID id);
    Iterable <T> findAll();
    Collection<T> getAll();
}