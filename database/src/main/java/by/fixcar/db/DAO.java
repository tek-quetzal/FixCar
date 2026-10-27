package by.fixcar.db;

import java.util.List;
import java.util.Optional;

public interface DAO <E>{
    E save(E obj);
    boolean delete(Integer id);
    boolean update(E obj);
    Optional<E> findById(Integer id);
    List<E> findAll();

}
