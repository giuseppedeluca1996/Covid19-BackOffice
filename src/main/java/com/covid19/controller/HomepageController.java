package com.covid19.controller;
import com.covid19.dao.DaoFactory;
import com.covid19.dao.ReviewDao;
import com.covid19.dao.StructureDao;
import com.covid19.dao.UserDao;
import com.covid19.model.AvgRatingReviewOfUser;
import com.covid19.model.Structure;
import com.covid19.model.User;
import com.covid19.view.HomepageViewController;

import java.util.*;

public class HomepageController {

    private static HomepageViewController homepageViewController;

    private static final UserDao userDao= Objects.requireNonNull(DaoFactory.getDaoFactory()).getUserDao();
    private static final StructureDao structureDao= Objects.requireNonNull(DaoFactory.getDaoFactory()).getStructureDao();
    private static final ReviewDao reviewDao= Objects.requireNonNull(DaoFactory.getDaoFactory()).getReviewDao();

    public static Map<String,Object> getAllUser(Integer page, Integer size){
        return userDao.getAll(page, size);
    }

    public static Map<String,Object> getAllUserByText(Integer page, Integer size,String text){
        return userDao.getAllByText(page, size, text);
    }

    public static Map<String,Object> getAllStructure(Integer page, Integer size) {
        return structureDao.getAll(page,size);
    }

    public static  Map<String,Object> getAllHotel(Integer page, Integer size) {
        return structureDao.getAllHotel(page,size);
    }

    public static Map<String,Object> getAllRestaurant(Integer page, Integer size) {
        return structureDao.getAllRestaurant(page,size);
    }

    public static Map<String,Object> getAllAttraction(Integer page, Integer size) {
        return structureDao.getAllAttraction(page,size);
    }


    public static Map<String, Object> getAllHotelByText(Integer page, Integer size, String text) {
        return  structureDao.getAllHotelByText(page,size,text);
    }

    public static Map<String, Object> getAllRestaurantByText(Integer page, Integer size, String text) {
        return  structureDao.getAllRestaurantByText(page,size,text);
    }

    public static Map<String, Object> getAllAttractionByText(Integer page, Integer size, String text) {
        return  structureDao.getAllAttractionByText(page,size,text);
    }

    public static void deleteUserByUsername(User user){
       userDao.deleteByUsername(user.getUsername());
    }

    public static void deleteUserByEmail(User user){
        userDao.deleteByEmail(user.getEmail());
        homepageViewController.getUserObservableList().remove(user);
    }

    public static void updateUserByUsername(User newUser, String username){
        User updatedUser = userDao.updateByUsername(newUser, username);
        homepageViewController.getUserObservableList().remove(updatedUser);
        homepageViewController.getUserObservableList().add(updatedUser);

    }
    public static void updateUserByEmail(User newUser, String email){
        User updatedUser = userDao.updateByUsername(newUser, email);
        homepageViewController.getUserObservableList().remove(updatedUser);
        homepageViewController.getUserObservableList().add(updatedUser);
    }


    public static Double getAverageOfReviewsStructure(Integer idStructure){
        return reviewDao.getAverageOfReviewStructure(idStructure);
    }

    public static Integer getNumberOfUserReviewsById(Integer idUser){
        return reviewDao.getNumberOfUserReviewsById(idUser);
    }

    public static Integer getNumberOfUserReviewsByEmail(String email){
        return reviewDao.getNumberOfUserReviewsByEmail(email);
    }

    public static Integer getNumberOfUserReviewsByUsername(String username){
        return reviewDao.getNumberOfUserReviewsByUsername(username);
    }

    public static Double getAverageOfReviewsUserById(Integer idUser){
        return reviewDao.getAverageOfReviewUserById(idUser);
    }

    public static Double getAverageOfReviewsUserByEmail(String email){
        return reviewDao.getAverageOfReviewUserByEmail(email);
    }

    public static Double getAverageOfReviewsUserByUsername(String usernme){
        return reviewDao.getAverageOfReviewUserByUsername(usernme);
    }

    public static List<AvgRatingReviewOfUser> getAverageOfReviewsUserInYear(Integer id, Integer year){
        return reviewDao.getAverageOfReviewUserInYear(id,year);
    }



    public static HomepageViewController getHomepageViewController() {
        return homepageViewController;
    }

    public static void setHomepageViewController(HomepageViewController homepageViewController) {
        HomepageController.homepageViewController = homepageViewController;
    }



}
