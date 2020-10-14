package com.covid19.dao.impl;

import com.covid19.dao.UserDao;
import com.covid19.model.HttpRequest;
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
import java.util.*;


public class SpringUserDao extends UserDao {

    private final HttpRequest httpRequest= new HttpRequest();
    private static SpringUserDao instance;
    private Gson gson;

    private SpringUserDao(){
       gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
    }

    public static SpringUserDao getInstance(){
        if(instance == null) {
            synchronized (SpringUserDao.class) {
                instance = new SpringUserDao();
            }
        }
        return instance;
    }


    @Override
    public void save(User entity) {

    }

    @Override
    public void update(User newEntity, Integer id) {

    }

    public User updateByEmail(User newEntity, String email){

        User updateUser=null;

        String userJson= gson.toJson(newEntity);
        try {
            HttpResponse<String> response= httpRequest.requestPut("/user/updateUser?email=" + email,userJson, true, Objects.requireNonNull(AuthManagerFactory.getAuthManagerFactory()).getAuthManager().getAuthenticationString());
            if(response.statusCode()==200){
                updateUser=gson.fromJson(response.body(), new TypeToken<User>() {}.getType());
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return updateUser;

    }

    public User updateByUsername(User newEntity, String username){

        User updateUser=null;

        String userJson= gson.toJson(newEntity);
        try {
            HttpResponse<String> response= httpRequest.requestPut("/user/updateUser?username=" + username,userJson, true, Objects.requireNonNull(AuthManagerFactory.getAuthManagerFactory()).getAuthManager().getAuthenticationString());
            if(response.statusCode()==200){
                updateUser=gson.fromJson(response.body(), new TypeToken<User>() {}.getType());
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return updateUser;
    }


    @Override
    public void delete(User entity) {

    }


    @Override
    public void deleteByEmail(String email) {
        try {
            HttpResponse<String> response= httpRequest.requestDelete("/user/deleteUser?email=" + email, true, Objects.requireNonNull(AuthManagerFactory.getAuthManagerFactory()).getAuthManager().getAuthenticationString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteByUsername(String username) {
        try {
            HttpResponse<String> response= httpRequest.requestDelete("/user/deleteUser?username=" + username, true, Objects.requireNonNull(AuthManagerFactory.getAuthManagerFactory()).getAuthManager().getAuthenticationString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Map<String,Object> getAll(Integer page, Integer size) {
        Map<String, Object> map =new HashMap<>();
        map.put("numberOfTotalItems",0);
        map.put("collectionItems", null);
        map.put("sizeCollectionItems", 0);
        try {
           HttpResponse<String> response= httpRequest.requestGet("/user/getAllUser?page=" + page + "&size=" +size, true, Objects.requireNonNull(AuthManagerFactory.getAuthManagerFactory()).getAuthManager().getAuthenticationString());
           if (response.statusCode()==200 ) {
               try {
                   JSONObject jsonObject = (JSONObject) new JSONParser().parse(response.body());
                   if(!(Boolean )jsonObject.get("empty")){
                       JSONArray userContent = (JSONArray) jsonObject.get("content");
                       map.replace("collectionItems", gson.fromJson(userContent.toJSONString(), new TypeToken<List<User>>() {}.getType()));
                       map.replace("numberOfTotalItems", Integer.valueOf(jsonObject.get("totalElements").toString()));
                       map.replace("sizeCollectionItems", Integer.valueOf(jsonObject.get("numberOfElements").toString()));
                   }
               } catch (ParseException e) {
                   e.printStackTrace();
               }
           }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public Map<String,Object> getAllByText(Integer page, Integer size,String text) {
        Map<String, Object> map =new HashMap<>();
        map.put("numberOfTotalItems",0);
        map.put("collectionItems", null);
        map.put("sizeCollectionItems", 0);
        try {
            HttpResponse<String> response= httpRequest.requestGet("/user/getAllUser/"+ text +"?page=" + page + "&size=" +size, true, Objects.requireNonNull(AuthManagerFactory.getAuthManagerFactory()).getAuthManager().getAuthenticationString());
            if (response.statusCode()==200 ) {
                try {
                    JSONObject jsonObject = (JSONObject) new JSONParser().parse(response.body());
                    if(!(Boolean )jsonObject.get("empty")){
                        JSONArray userContent = (JSONArray) jsonObject.get("content");
                        map.replace("collectionItems", gson.fromJson(userContent.toJSONString(), new TypeToken<List<User>>() {}.getType()));
                        map.replace("numberOfTotalItems", Integer.valueOf(jsonObject.get("totalElements").toString()));
                        map.replace("sizeCollectionItems", Integer.valueOf(jsonObject.get("numberOfElements").toString()));
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public User getById(Integer id) {
        return null;
    }

    @Override
    public User getByUsername(String username) {
        return null;
    }

    @Override
    public User getByEmail(String email) {
        return null;
    }


}
