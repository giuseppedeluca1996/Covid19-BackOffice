package com.covid19.dao;

import java.util.Collection;
import java.util.Map;

public interface AbstractDao<T> {


    void save( T entity);
    void update(T newEntity, Integer id);
    void delete(T entity);
    Map<String,Object> getAll(Integer page , Integer size);
    T getById(Integer id);

}
