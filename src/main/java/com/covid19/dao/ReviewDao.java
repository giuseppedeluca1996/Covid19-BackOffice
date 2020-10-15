package com.covid19.dao;

import com.covid19.model.AvgRatingReviewOfUser;
import com.covid19.model.User;

import java.util.List;
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


    public abstract Double getAverageOfReviewStructure(Integer idStructure);

    public abstract Integer getNumberOfUserReviewsByEmail(String email);

    public abstract Integer getNumberOfUserReviewsByUsername(String username);

    public abstract Integer getNumberOfUserReviewsById(Integer idUser);

    public abstract Double getAverageOfReviewUserById(Integer idUser);

    public abstract Double getAverageOfReviewUserByUsername(String username);

    public abstract Double getAverageOfReviewUserByEmail(String email);

    public abstract List<AvgRatingReviewOfUser> getAverageOfReviewUserInYear(Integer id, Integer year);

}
