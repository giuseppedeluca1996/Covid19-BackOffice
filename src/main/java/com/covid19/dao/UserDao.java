package com.covid19.dao;

import com.covid19.model.User;

import java.util.Map;


public abstract class UserDao implements AbstractDao<User> {

    @Override
    public abstract void save(User entity);

    @Override
    public abstract  void update(User newEntity, Integer id);

    public abstract  User updateByEmail(User newEntity, String email);

    public abstract  User updateByUsername(User newEntity, String username);

    @Override
    public abstract void delete(User entity);

    public abstract void deleteByEmail(String email);

    public abstract void deleteByUsername(String username);


    @Override
    public abstract Map<String,Object> getAll(Integer page, Integer size);

    public abstract Map<String,Object> getAllByText(Integer page, Integer size, String text);

    @Override
    public abstract User getById(Integer id);

    public abstract User getByUsername(String username);

    public abstract User getByEmail(String email);



}
