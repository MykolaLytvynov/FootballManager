package ua.mykola.footballmanager.bl.service;


public interface CrudOperations<T, ID> {

    void save(T entity);

    T findById(ID id);

    void update(T entity);

    void deleteById(ID id);
}
