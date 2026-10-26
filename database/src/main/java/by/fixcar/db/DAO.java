package by.fixcar.db;

import java.util.List;

public interface DAO <E>{
    E save(E obj);
    boolean delete(Integer id);
    boolean update(E obj);
    E findById(Integer id);
    List<E> findAll();

}
