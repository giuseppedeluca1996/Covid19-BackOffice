package com.covid19.dao.impl;

import com.covid19.dao.ReviewDao;
import com.covid19.model.AvgRatingReviewOfUser;
import com.covid19.model.HttpRequest;
import com.covid19.model.Structure;
import com.covid19.model.User;
import com.covid19.security.AuthManagerFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
    public Integer getNumberOfUserReviewsByEmail(String email) {


        try {
            HttpResponse<String> response= httpRequest.requestGet( "/review/numberOfUserReviews?email="+ email ,true, Objects.requireNonNull(AuthManagerFactory.getAuthManagerFactory()).getAuthManager().getAuthenticationString());
            if(response.statusCode()==200){
                return Integer.valueOf(response.body());
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Integer getNumberOfUserReviewsByUsername(String username) {


        try {
            HttpResponse<String> response= httpRequest.requestGet( "/review/numberOfUserReviews?username="+ username ,true, Objects.requireNonNull(AuthManagerFactory.getAuthManagerFactory()).getAuthManager().getAuthenticationString());
            if(response.statusCode()==200){
                return Integer.valueOf(response.body());
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Integer getNumberOfUserReviewsById(Integer idUser) {

        try {
            HttpResponse<String> response= httpRequest.requestGet( "/review/numberOfUserReviews?idUser="+ idUser ,true, Objects.requireNonNull(AuthManagerFactory.getAuthManagerFactory()).getAuthManager().getAuthenticationString());
            if(response.statusCode()==200){
                return Integer.valueOf(response.body());
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Double getAverageOfReviewStructure(Integer idStructure) {
        try {
            HttpResponse<String> response= httpRequest.requestGet( "/review/public/getAverageRatingOfStructure?idStructure="+ idStructure ,false, null);
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

    @Override
    public  Double getAverageOfReviewUserById(Integer idUser){

        try {
            HttpResponse<String> response= httpRequest.requestGet( "/review/getAverageRatingOfUser?idUser="+ idUser ,true, Objects.requireNonNull(AuthManagerFactory.getAuthManagerFactory()).getAuthManager().getAuthenticationString());
            if(response.statusCode()==200){
                return Double.valueOf(response.body());
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return 0D;

    }

    @Override
    public  Double getAverageOfReviewUserByUsername(String username){

        try {
            HttpResponse<String> response= httpRequest.requestGet( "/review/getAverageRatingOfUser?username="+ username ,true, Objects.requireNonNull(AuthManagerFactory.getAuthManagerFactory()).getAuthManager().getAuthenticationString());
            if(response.statusCode()==200){
                return Double.valueOf(response.body());
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return 0D;

    }


    @Override
    public  Double getAverageOfReviewUserByEmail(String email){

        try {
            HttpResponse<String> response= httpRequest.requestGet( "/review/getAverageRatingOfUser?email="+ email ,true, Objects.requireNonNull(AuthManagerFactory.getAuthManagerFactory()).getAuthManager().getAuthenticationString());
            if(response.statusCode()==200){
                return Double.valueOf(response.body());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return 0D;
    }

    @Override
    public List<AvgRatingReviewOfUser> getAverageOfReviewUserInYear(Integer id, Integer year){
        try {
            HttpResponse<String> response= httpRequest.requestGet( "/review/averageReviewOfUser?id="+ id + "&year=" + year ,true, Objects.requireNonNull(AuthManagerFactory.getAuthManagerFactory()).getAuthManager().getAuthenticationString());

            if(response.statusCode()==200){
                return gson.fromJson(response.body(), new TypeToken<List<AvgRatingReviewOfUser>>() {}.getType());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }


}
