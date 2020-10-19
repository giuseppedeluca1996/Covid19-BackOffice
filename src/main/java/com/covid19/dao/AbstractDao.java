package com.covid19.dao;


import java.util.Map;

public interface AbstractDao<T> {


    void save( T entity);
    T update(T newEntity, Integer id);
    void delete(Integer id);
    T getById(Integer id);
    Map<String,Object> getAll(Integer page , Integer size);


}
