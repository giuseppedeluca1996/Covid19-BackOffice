package com.covid19.view;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.*;

public class StatisticsViewController extends Application {

    @FXML
    private PieChart numberOfReviewPieChart;

    @FXML
    private BarChart<String,Double> topThreeOfYearBarChart;

    @FXML
    private  LineChart<String, Double> avgLineChart;

    Map<String, Integer> numberOfReviewStructure;
    Map<String, Double> topThreeOfYear;
    Map<String,Map<Integer,Double>>  avgReviewForStructureInTheYear;

    public void initData() {

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        Set<String> key=numberOfReviewStructure.keySet();
        for(String s : key){
            pieChartData.add( new PieChart.Data(s, numberOfReviewStructure.get(s)));
        }
        numberOfReviewPieChart.setData(pieChartData);


        Iterator<Map.Entry<String,Double>> i=topThreeOfYear.entrySet().iterator();
        int j=0;


        ObservableList<String> structureNames= FXCollections.observableArrayList();
        XYChart.Series<String,Double> series = new XYChart.Series<>();
        while (i.hasNext() && j<3){
            Map.Entry<String ,Double > e=i.next();
            String s=e.getKey();
            Double d=e.getValue();
            structureNames.add(s);
            series.getData().add(new XYChart.Data<>(s,d));
            j++;
        }
        topThreeOfYearBarChart.getData().add(series);


        Set<String> s=avgReviewForStructureInTheYear.keySet();
        List<XYChart.Series<String,Double>> list=new LinkedList<>();
        for(String string: s){
            XYChart.Series<String,Double> series1 = new XYChart.Series<>();
            series1.setName(string);
            for(int h=1; h<13; h++){
                Map<Integer,Double> r=((Map<Integer,Double>)avgReviewForStructureInTheYear.get(string));
                Double risss= r.get(h);
                series1.getData().add(new XYChart.Data<String,Double>(String.valueOf(h),risss));

            }
            list.add(series1);
        }

        for(XYChart.Series<String,Double> x :  list ){
            avgLineChart.getData().add(x);
        }

    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/StatisticsScreen.fxml"));
        loader.setController(this);
        Parent statisticViewParent  = loader.load();
        Scene statisticViewScene = new Scene(statisticViewParent);
        stage.setScene(statisticViewScene);
        stage.getIcons().add(new Image("/download.jpg"));
        stage.show();
        initData();
    }

    public void setNumberOfReviewStructure(Map<String, Integer> numberOfReviewStructure) {
        this.numberOfReviewStructure = numberOfReviewStructure;
    }

    public void setTopThreeOfYear(Map<String, Double> topThreeOfYear) {
        this.topThreeOfYear = topThreeOfYear;
    }

    public void setAvgReviewForStructureInTheYear(Map<String, Map<Integer, Double>> avgReviewForStructureInTheYear) {
        this.avgReviewForStructureInTheYear = avgReviewForStructureInTheYear;
    }
}

