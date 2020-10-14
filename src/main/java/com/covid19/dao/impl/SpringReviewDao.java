package com.covid19.dao.impl;

import com.covid19.dao.ReviewDao;
import com.covid19.model.HttpRequest;
import com.covid19.model.Structure;
import com.covid19.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Map;

public class SpringReviewDao extends ReviewDao {
    private final HttpRequest httpRequest= new HttpRequest();
    private static SpringReviewDao instance;
    private Gson gson;

    private SpringReviewDao(){
        gson = new GsonBuilder().setDateFormat("HH:mm:ss").create();
    }

    public static SpringReviewDao getInstance(){
        if(instance == null) {
            synchronized (SpringUserDao.class) {
                instance = new SpringReviewDao();
            }
        }
        return instance;
    }

    @Override
    public Double getAverageOfReview(Structure structure) {

        String jsonStructure=gson.toJson(structure);
        try {
            HttpResponse<String> response= httpRequest.requestPost(  jsonStructure, "/review/public/getAverageRating" ,false, null);
            if(response.statusCode()==200){
                return Double.valueOf(response.body());
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return 0D;
    }

    @Override
    public void save(User entity) {
        super.save(entity);
    }

    @Override
    public void update(User newEntity, Integer id) {
        super.update(newEntity, id);
    }

    @Override
    public void delete(User entity) {
        super.delete(entity);
    }

    @Override
    public Map<String, Object> getAll(Integer page, Integer size) {
        return super.getAll(page, size);
    }

    @Override
    public User getById(Integer id) {
        return super.getById(id);
    }
}
