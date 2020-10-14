package com.covid19.dao;

import com.covid19.model.Structure;
import com.covid19.model.User;

import java.util.Map;

public abstract class ReviewDao implements AbstractDao<User> {


    @Override
    public void save(User entity) {

    }

    @Override
    public void update(User newEntity, Integer id) {

    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public Map<String, Object> getAll(Integer page, Integer size) {
        return null;
    }

    @Override
    public User getById(Integer id) {
        return null;
    }


    public abstract Double getAverageOfReview(Structure structure);
}
