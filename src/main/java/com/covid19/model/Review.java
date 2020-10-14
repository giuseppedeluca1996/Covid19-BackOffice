package com.covid19.model;

import lombok.Data;

import java.util.Date;

@Data
public class Review {
    private Short rating;
    private String description;
    private Date date;
    private Short qualityPrice;
    private Short cleaning;
    private Short service;

}
