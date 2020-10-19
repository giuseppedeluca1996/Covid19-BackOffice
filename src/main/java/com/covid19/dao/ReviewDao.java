package com.covid19.dao;

import com.covid19.model.AvgRatingReviewOfStructure;
import com.covid19.model.AvgRatingReviewOfUser;
import com.covid19.model.Review;
import java.util.List;
import java.util.Map;

public abstract class ReviewDao implements AbstractDao<Review> {


    @Override
    public void save(Review entity) {

    }

    @Override
    public Review update(Review newEntity, Integer id) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public Review getById(Integer id) {
        return null;
    }

    @Override
    public Map<String, Object> getAll(Integer page, Integer size) {
        return null;
    }

    public abstract Integer getNumUserReview(Integer idUser);

    public abstract Double getAvgUserReview(Integer idUser);

    public abstract List<AvgRatingReviewOfUser> getAvgUserReviewGroupByMonthInSpecificYear(Integer id, Integer year);

    public abstract List<AvgRatingReviewOfStructure> getAvgStructureReviewGroupByMonthInSpecificYear(Integer id, Integer year);

    public abstract Integer getNumStructureReview(Integer idStructure);

    public abstract Double getAvgStructureReview(Integer idStructure);

    public abstract Double getAvgStructureReview(Integer idStructure, Integer year);

    public abstract Double getAvgStructureReviewInSpecificYearAndMonth(Integer id, Integer year, Integer month);
}
