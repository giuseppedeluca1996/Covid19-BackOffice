package com.covid19.dao.impl;

import com.covid19.dao.ReviewDao;
import com.covid19.model.AvgRatingReviewOfStructure;
import com.covid19.model.AvgRatingReviewOfUser;
import com.covid19.model.HttpRequest;
import com.covid19.model.Review;
import com.covid19.security.AuthManagerFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

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
    public void save(Review entity) {

    }

    @Override
    public Review update(Review newEntity, Integer id) {
        return null;
    }

    @Override
    public void delete(Integer id) { }

    @Override
    public Review getById(Integer id) {
        return null;
    }

    @Override
    public Map<String, Object> getAll(Integer page, Integer size) {
        return null;
    }

    @Override
    public Integer getNumUserReview (Integer idUser) {

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
    public  Double getAvgUserReview (Integer idUser){

        try {
            HttpResponse<String> response= httpRequest.requestGet( "/review/getAverageReviewOfUser?idUser="+ idUser ,true, Objects.requireNonNull(AuthManagerFactory.getAuthManagerFactory()).getAuthManager().getAuthenticationString());
            if(response.statusCode()==200){
                return Double.valueOf(response.body());
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return 0D;

    }

    @Override
    public List<AvgRatingReviewOfUser> getAvgUserReviewGroupByMonthInSpecificYear (Integer id, Integer year){
        try {
            HttpResponse<String> response= httpRequest.requestGet( "/review/averageReviewOfUser?idUser="+ id + "&year=" + year ,true, Objects.requireNonNull(AuthManagerFactory.getAuthManagerFactory()).getAuthManager().getAuthenticationString());

            if(response.statusCode()==200){
                return gson.fromJson(response.body(), new TypeToken<List<AvgRatingReviewOfUser>>() {}.getType());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<AvgRatingReviewOfStructure> getAvgStructureReviewGroupByMonthInSpecificYear (Integer id, Integer year){
        try {
            HttpResponse<String> response= httpRequest.requestGet( "/review/averageReviewOStructure?idStructure="+ id + "&year=" + year ,true, Objects.requireNonNull(AuthManagerFactory.getAuthManagerFactory()).getAuthManager().getAuthenticationString());

            if(response.statusCode()==200){
                return gson.fromJson(response.body(), new TypeToken<List<AvgRatingReviewOfStructure>>() {}.getType());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public  Integer getNumStructureReview (Integer idStructure){
        try {
            HttpResponse<String> response= httpRequest.requestGet( "/review/getTotalNumberOfReviewOfStructure?idStructure="+ idStructure,true, Objects.requireNonNull(AuthManagerFactory.getAuthManagerFactory()).getAuthManager().getAuthenticationString());
            if(response.statusCode()==200){
                return Integer.valueOf(response.body());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Double getAvgStructureReview (Integer idStructure) {
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
    public Double getAvgStructureReview (Integer idStructure, Integer year) {
        try {
            HttpResponse<String> response= httpRequest.requestGet( "/review/public/getAverageRatingOfStructure?idStructure="+ idStructure + "&year=" + year ,false, null);
            if(response.statusCode()==200){
                return Double.valueOf(response.body());
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return 0D;
    }

    @Override
    public Double getAvgStructureReviewInSpecificYearAndMonth (Integer id, Integer year, Integer month){
        try {
            HttpResponse<String> response= httpRequest.requestGet( "/review/public/averageReviewOfStructureInYearAndMonth?id="+ id + "&year=" + year + "&month=" + month,false, null);
            if(response.statusCode()==200){
                if(response.body().isEmpty())
                    return 0D;
                return Double.valueOf(response.body());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return 0D;
    }


}
