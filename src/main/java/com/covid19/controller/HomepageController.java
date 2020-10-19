package com.covid19.controller;
import com.covid19.dao.DaoFactory;
import com.covid19.dao.ReviewDao;
import com.covid19.dao.StructureDao;
import com.covid19.dao.UserDao;
import com.covid19.model.AvgRatingReviewOfStructure;
import com.covid19.model.AvgRatingReviewOfUser;
import com.covid19.model.Structure;
import com.covid19.model.User;
import com.covid19.view.HomepageViewController;
import com.covid19.view.SpecificUserViewController;
import com.covid19.view.StatisticsViewController;
import com.sun.javafx.image.IntPixelGetter;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ThreadPoolExecutor;

public class HomepageController {

    private static HomepageViewController homepageViewController;

    private static final UserDao userDao= Objects.requireNonNull(DaoFactory.getDaoFactory()).getUserDao();

    private static final StructureDao structureDao= Objects.requireNonNull(DaoFactory.getDaoFactory()).getStructureDao();

    private static final ReviewDao reviewDao= Objects.requireNonNull(DaoFactory.getDaoFactory()).getReviewDao();

    public static void deleteUser(User user){
        userDao.delete(user.getId());
        homepageViewController.getUserObservableList().remove(user);
    }

    public static void updateUser(User newUser, Integer id){
        User updatedUser = userDao.update(newUser, id);
        homepageViewController.getUserObservableList().remove(updatedUser);
        homepageViewController.getUserObservableList().add(updatedUser);
    }

    public static Map<String,Object> getAllUser(Integer page, Integer size){
        return userDao.getAll(page, size);
    }

    public static Map<String,Object> getAllUserByText(Integer page, Integer size,String text){
        return userDao.getAllByText(page, size, text);
    }

    public static Integer getNumUserReview(Integer idUser){
        return reviewDao.getNumUserReview(idUser);
    }

    public static Double getAvgUserReview(Integer idUser){
        return reviewDao.getAvgUserReview(idUser);
    }

    public static List<AvgRatingReviewOfUser> getAvgUserReviewGroupByMonthInSpecificYear(Integer id, Integer year){
        return reviewDao.getAvgUserReviewGroupByMonthInSpecificYear(id,year);
    }

    public static List<AvgRatingReviewOfStructure> getAvgStructureReviewGroupByMonthInSpecificYear(Integer id, Integer year){
        return reviewDao.getAvgStructureReviewGroupByMonthInSpecificYear(id,year);
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

    public static Double getAvgStructureReview(Integer idStructure){
        return reviewDao.getAvgStructureReview(idStructure);
    }

    public static Double getAvgStructureReview(Integer idStructure,Integer year){
        return reviewDao.getAvgStructureReview(idStructure, year);
    }

    public static Double getAvgStructureReviewInSpecificYearAndMonth(Integer id, Integer year, Integer month){
        Double ris= reviewDao.getAvgStructureReviewInSpecificYearAndMonth(id,year,month);
        return Objects.requireNonNullElse(ris, 0D);
    }

    public static Integer getNumStructureReview(Integer idStructure){
        return reviewDao.getNumStructureReview(idStructure);
    }

    public static void showSpecificUserView(Stage stage, User user){
        try {
            SpecificUserViewController specificUserViewController = new SpecificUserViewController();
            specificUserViewController.setSelectedUser(user);
            specificUserViewController.setAverageOfReviewUserInYear(getAvgUserReviewGroupByMonthInSpecificYear(user.getId(), LocalDate.now().getYear()));
            specificUserViewController.start(stage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showStatisticView(Stage stage, Collection<Structure> structures){


        Map<String,Integer> numberOfReviewStructure=new HashMap<>();
        Map<String,Double> topThreeOfYearNd = new HashMap<>();
        Map<String,Map<Integer,Double>>  avgReviewForStructureInTheYear = new HashMap<>();


        Map<Integer,Double>m;
        for(Structure s :structures){
            numberOfReviewStructure.put(s.getName(),getNumStructureReview(s.getId()));
            topThreeOfYearNd.put(s.getName(),getAvgStructureReview(s.getId(),LocalDate.now().getYear()));
            List<AvgRatingReviewOfStructure> avg=HomepageController.getAvgStructureReviewGroupByMonthInSpecificYear(s.getId(),LocalDate.now().getYear());
            m=new HashMap<>();
            for(int i=1; i<13;i++){
                AvgRatingReviewOfStructure a=new AvgRatingReviewOfStructure();
                a.setId(i);
                int index;
                if((index=avg.indexOf(a))>=0){
                    m.put(i,avg.get(index).getAvg_rating());
                }else {
                    m.put(i,0D);
                }
            }
            avgReviewForStructureInTheYear.put(s.getName(),m);
        }

        TreeMap<String, Double> sorted_map = new TreeMap<>( new Comparator<String>(){
            @Override
            public int compare(String o1, String o2) {
                if(topThreeOfYearNd.get(o1).equals(topThreeOfYearNd.get(o2)))
                    return 0;
                if(topThreeOfYearNd.get(o1) > topThreeOfYearNd.get(o2)){
                    return -1;
                }else {
                    return 1;
                }
            }
        });
        sorted_map.putAll(topThreeOfYearNd);

        try {
            StatisticsViewController statisticsViewController = new StatisticsViewController();
            statisticsViewController.setNumberOfReviewStructure(numberOfReviewStructure);
            statisticsViewController.setTopThreeOfYear(sorted_map);
            statisticsViewController.setAvgReviewForStructureInTheYear(avgReviewForStructureInTheYear);
            statisticsViewController.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static HomepageViewController getHomepageViewController() {
        return homepageViewController;
    }

    public static void setHomepageViewController(HomepageViewController homepageViewController) {
        HomepageController.homepageViewController = homepageViewController;
    }


}
