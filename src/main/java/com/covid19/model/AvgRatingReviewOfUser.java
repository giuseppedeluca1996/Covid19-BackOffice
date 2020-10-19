package com.covid19.model;

import lombok.Data;

@Data
public class AvgRatingReviewOfUser {

    private Integer month;

    private Integer number_of_review;

    private Double  avg_rating;
}