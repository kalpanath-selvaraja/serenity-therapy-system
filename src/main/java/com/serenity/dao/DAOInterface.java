package com.serenity.dao;

import java.io.Serializable;
import java.util.List;

public interface DAOInterface<T> {
    void save (T entity);
    void update(T entity);
    void delete (T entity);
    T getById(Class<T> tClass , Serializable id);
    List<T> getAll (Class<T> tClass);


}
