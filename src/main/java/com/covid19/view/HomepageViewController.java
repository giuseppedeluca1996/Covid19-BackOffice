package com.covid19.view;

import com.covid19.controller.HomepageController;
import com.covid19.model.Structure;
import com.covid19.model.User;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class HomepageViewController extends Application {

    @FXML
    private Button userButton;

    @FXML
    private Button structureButton;

    @FXML
    private TextField searchTextField;

    @FXML
    private TabPane structureTabPane;

    @FXML
    private ListView<Structure> hotelList;

    @FXML
    private Button generateStatisticsHotelButton;

    @FXML
    private ListView<Structure> restaurantList;

    @FXML
    private Button generateStatisticsRestaurantButton;

    @FXML
    private ListView<Structure> attractionList;

    @FXML
    private Button generateStatisticAttractionButton;

    @FXML
    private ListView<User> userList;

    @FXML
    private Tab attractionTab;

    @FXML
    private Tab restaurantTab;

    @FXML
    private Tab hotelTab;

    @FXML
    private Label searchIsEmptyLabel;

    ObservableList<User> userObservableList = FXCollections.observableArrayList();
    ObservableList<Structure> structureObservableList = FXCollections.observableArrayList();
    private final static Collection<User> users=new ArrayList<>();
    private final static Collection<Structure> structures= new ArrayList<>();


    private int indexUserPage = 0;
    private int userPageSize = 5;
    private Integer numberOfTotalUser;
    private Integer numberOfViewUser = 0;
    private  Boolean nextQueryUserIsValid = false;

    private int indexStructurePage = 0;
    private int structurePageSize = 5;
    private Integer numberOfTotalStructure;
    private Integer numberOfViewStructure = 0;
    private  Boolean nextQueryStructureIsValid = false;


    private short firstesecution=0;

    @FXML
    public void initialize(){

        structureTabPane.setVisible(false);
        HomepageController.setHomepageViewController(this);
        searchIsEmptyLabel.setVisible(false);

        Map<String, Object> map = HomepageController.getAllUser(indexUserPage,userPageSize);
        users.addAll((Collection<User>)(map.get("collectionItems")));
        numberOfTotalUser=(Integer)map.get("numberOfTotalItems");
        numberOfViewUser=(Integer)map.get("sizeCollectionItems");
        nextQueryUserIsValid=true;

        userObservableList.addAll(users);
        userList.setItems(userObservableList);

        hotelList.setItems(structureObservableList);
        restaurantList.setItems(structureObservableList);
        attractionList.setItems(structureObservableList);

        userList.setCellFactory(UserCellList -> new UserCellViewController());
        hotelList.setCellFactory(structureCellList -> new StructureCellViewController());
        restaurantList.setCellFactory(structureCellList -> new StructureCellViewController());
        attractionList.setCellFactory(structureCellList -> new StructureCellViewController());

    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/UserHomepage.fxml"));
        stage.setHeight(750);
        stage.setWidth(1300);
        stage.setTitle("User Homepage");
        stage.setScene(new Scene(root, 1280,720));
        stage.setResizable(false);
        stage.getIcons().add(new Image("/download.jpg"));
        stage.show();
    }

    public void userPress(ActionEvent actionEvent) {

        if(!userList.isVisible()){
            structureTabPane.setVisible(false);
            userList.setVisible(true);
            searchTextField.clear();

            indexUserPage=0;
            numberOfViewUser=0;
            userObservableList.clear();

            Map<String, Object> map = HomepageController.getAllUser(indexUserPage, userPageSize);
            if(((Collection<User>) (map.get("collectionItems")) != null)) {
                users.addAll((Collection<User>) (map.get("collectionItems")));
            }
            numberOfTotalUser = (Integer) map.get("numberOfTotalItems");
            numberOfViewUser = (Integer) map.get("sizeCollectionItems");
            nextQueryUserIsValid=false;

            if(!users.isEmpty()) {
                userObservableList.addAll(users);
                searchIsEmptyLabel.setVisible(false);
            } else{
                searchIsEmptyLabel.setVisible(true);
            }
        }
    }

    public void structurePress(ActionEvent actionEvent) {

        if(!structureTabPane.isVisible()) {

            structureTabPane.setVisible(true);
            userList.setVisible(false);
            searchTextField.clear();

            indexStructurePage = 0;
            numberOfViewStructure = 0;
            structureObservableList.clear();
            nextQueryStructureIsValid=false;

            if(hotelTab.isSelected()) {
                hotelTabPressed(null);
            } else if (restaurantTab.isSelected()) {
                restaurantTabPressed(null);
            } else {
                attractionTabPressed(null);
            }
        }
    }

    public void openSpecificUser(MouseEvent mouseEvent) {

        if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
            if(mouseEvent.getClickCount() == 2) {
                Stage stage = new Stage();
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(searchTextField.getScene().getWindow());
                HomepageController.showSpecificUserView(stage, userList.getSelectionModel().getSelectedItem());
            }
        }
    }

    public void hotelTabPressed(Event event) {
        if(firstesecution==0){
            firstesecution++;
        } else{
            if(hotelTab.isSelected()){

                indexStructurePage = 0;
                numberOfViewStructure = 0;
                structureObservableList.clear();
                structures.clear();
                Map<String, Object> map;
                if(searchTextField.getText().isEmpty()){
                    map = HomepageController.getAllHotel(indexStructurePage, structurePageSize);
                    nextQueryStructureIsValid=false;
                }else  {
                    map = HomepageController.getAllHotelByText(indexStructurePage, structurePageSize, searchTextField.getText());
                    nextQueryStructureIsValid=true;
                }
                if(((Collection<Structure>) (map.get("collectionItems")) != null)) {
                    structures.addAll((Collection<Structure>) (map.get("collectionItems")));
                }
                numberOfTotalStructure=(Integer)map.get("numberOfTotalItems");
                numberOfViewStructure=(Integer)map.get("sizeCollectionItems");
                if(!structures.isEmpty()) {
                    for(Structure s : structures){
                        s.setAverageRating(HomepageController.getAvgStructureReview(s.getId()));
                    }
                    structureObservableList.addAll(structures);
                    searchIsEmptyLabel.setVisible(false);
                    generateStatisticsHotelButton.setDisable(false);
                }  else{
                    searchIsEmptyLabel.setVisible(true);
                    generateStatisticsHotelButton.setDisable(true);
                }
            }
        }
    }

    public void restaurantTabPressed(Event event) {

        if(restaurantTab.isSelected()){

            indexStructurePage = 0;
            numberOfViewStructure = 0;
            structureObservableList.clear();
            structures.clear();
            Map<String, Object> map;
            if(searchTextField.getText().isEmpty()){
                map = HomepageController.getAllRestaurant(indexStructurePage, structurePageSize);
                nextQueryStructureIsValid=false;
            }else  {
                map = HomepageController.getAllRestaurantByText(indexStructurePage, structurePageSize, searchTextField.getText());
                nextQueryStructureIsValid=true;
            }
            if(((Collection<Structure>) (map.get("collectionItems")) != null)) {
                structures.addAll((Collection<Structure>) (map.get("collectionItems")));
            }
            numberOfTotalStructure=(Integer)map.get("numberOfTotalItems");
            numberOfViewStructure=(Integer)map.get("sizeCollectionItems");
            if(!structures.isEmpty()) {
                for(Structure s : structures){
                    s.setAverageRating(HomepageController.getAvgStructureReview(s.getId()));
                }
                structureObservableList.addAll(structures);
                searchIsEmptyLabel.setVisible(false);
                generateStatisticsRestaurantButton.setDisable(false);
            } else{
                searchIsEmptyLabel.setVisible(true);
                generateStatisticsRestaurantButton.setDisable(true);

            }
        }
    }

    public void attractionTabPressed(Event event) {
        if(attractionTab.isSelected()){

            indexStructurePage = 0;
            numberOfViewStructure = 0;
            structureObservableList.clear();
            structures.clear();
            Map<String, Object> map;
            if(searchTextField.getText().isEmpty()){
                map = HomepageController.getAllAttraction(indexStructurePage, structurePageSize);
                nextQueryStructureIsValid=false;
            }else  {
                map = HomepageController.getAllAttractionByText(indexStructurePage, structurePageSize, searchTextField.getText());
                nextQueryStructureIsValid=true;
            }
            if(((Collection<Structure>) (map.get("collectionItems")) != null)) {
                structures.addAll((Collection<Structure>) (map.get("collectionItems")));
            }
            numberOfTotalStructure=(Integer)map.get("numberOfTotalItems");
            numberOfViewStructure=(Integer)map.get("sizeCollectionItems");
            if(!structures.isEmpty()){
                for(Structure s : structures){
                    s.setAverageRating(HomepageController.getAvgStructureReview(s.getId()));
                }
                structureObservableList.addAll(structures);
                searchIsEmptyLabel.setVisible(false);
                generateStatisticAttractionButton.setDisable(false);
            } else{
                searchIsEmptyLabel.setVisible(true);
                generateStatisticAttractionButton.setDisable(false);

            }
        }
    }



    public void search(KeyEvent keyEvent) {

        if(keyEvent.getCode() == KeyCode.ENTER){
            if(userList.isVisible()){

                indexUserPage=0;
                numberOfViewUser=0;
                userObservableList.clear();
                users.clear();
                Map<String, Object> map;
                if(searchTextField.getText().isEmpty()){
                    map = HomepageController.getAllUser(indexUserPage, userPageSize);
                    nextQueryUserIsValid=false;
                }else{
                    map = HomepageController.getAllUserByText(indexUserPage, userPageSize,searchTextField.getText());
                    nextQueryUserIsValid=true;
                }
                if(((Collection<User>) (map.get("collectionItems")) != null)) {
                    users.addAll((Collection<User>) (map.get("collectionItems")));
                }
                numberOfTotalUser = (Integer) map.get("numberOfTotalItems");
                numberOfViewUser = (Integer) map.get("sizeCollectionItems");

                if(users != null) {
                    userObservableList.addAll(users);
                    searchIsEmptyLabel.setVisible(false);
                }else
                    searchIsEmptyLabel.setVisible(true);
            }else{
                if (hotelTab.isSelected()) {
                    hotelTabPressed(null);
                } else if (restaurantTab.isSelected()) {
                    restaurantTabPressed(null);
                } else {
                    attractionTabPressed(null);
                }
            }
        }
    }

    public void onScrollListUserView(ScrollEvent scrollEvent) {

        int focusedIndex=userList.getFocusModel().getFocusedIndex();
        if( numberOfViewUser < numberOfTotalUser && focusedIndex >= userObservableList.size()-3){
            Map<String, Object> map;
            if(!nextQueryUserIsValid){
                map=HomepageController.getAllUser(++indexUserPage,userPageSize);
            }else {
                map=HomepageController.getAllUserByText(++indexUserPage,userPageSize, searchTextField.getText());
            }
            users.addAll((Collection<User>)(map.get("collectionItems")));
            numberOfViewUser=numberOfViewUser+(Integer) map.get("sizeCollectionItems");
            if(!users.isEmpty())
                userObservableList.addAll(users);
        }
    }

    public void onScrollListHotelView(ScrollEvent scrollEvent) {

        int focusedIndex=hotelList.getFocusModel().getFocusedIndex();
        if( numberOfViewStructure < numberOfTotalStructure && focusedIndex >= structureObservableList.size()-3){
            Map<String, Object> map;
            if(!nextQueryStructureIsValid){
               map = HomepageController.getAllHotel(++indexStructurePage, structurePageSize);
            }else {
                map = HomepageController.getAllHotelByText(++indexStructurePage, structurePageSize,searchTextField.getText());
            }
            structures.addAll((Collection<Structure>) (map.get("collectionItems")));
            for(Structure s : structures){
                s.setAverageRating(HomepageController.getAvgStructureReview(s.getId()));
            }
            numberOfViewStructure=numberOfViewStructure+(Integer) map.get("sizeCollectionItems");
            if(!structures.isEmpty())
                structureObservableList.addAll(structures);
        }
    }

    public void onScrollListRestaurantView(ScrollEvent scrollEvent) {

        int focusedIndex=hotelList.getFocusModel().getFocusedIndex();
        if( numberOfViewStructure < numberOfTotalStructure && focusedIndex >= structureObservableList.size()-3){
            Map<String, Object> map;
            if(!nextQueryStructureIsValid){
                map = HomepageController.getAllRestaurant(++indexStructurePage, structurePageSize);
            }else {
                map = HomepageController.getAllRestaurantByText(++indexStructurePage, structurePageSize,searchTextField.getText());
            }
            structures.addAll((Collection<Structure>) (map.get("collectionItems")));
            for(Structure s : structures){
                s.setAverageRating(HomepageController.getAvgStructureReview(s.getId()));
            }
            numberOfViewStructure=numberOfViewStructure+(Integer) map.get("sizeCollectionItems");
            if(!structures.isEmpty())
                structureObservableList.addAll(structures);
        }
    }

    public void onScrollListAttractionView(ScrollEvent scrollEvent) {

        int focusedIndex=hotelList.getFocusModel().getFocusedIndex();
        if( numberOfViewStructure < numberOfTotalStructure && focusedIndex >= structureObservableList.size()-3){
            Map<String, Object> map;
            if(!nextQueryStructureIsValid){
                map = HomepageController.getAllAttraction(++indexStructurePage, structurePageSize);
            }else {
                map = HomepageController.getAllAttractionByText(++indexStructurePage, structurePageSize,searchTextField.getText());
            }
            structures.addAll( (Collection<Structure>) (map.get("collectionItems")));
            for(Structure s : structures){
                s.setAverageRating(HomepageController.getAvgStructureReview(s.getId()));
            }
            numberOfViewStructure=numberOfViewStructure+(Integer) map.get("sizeCollectionItems");
            if(!structures.isEmpty())
                structureObservableList.addAll(structures);
        }

    }

    public ObservableList<User> getUserObservableList() {
        return userObservableList;
    }

    public void setUserObservableList(ObservableList<User> userObservableList) {
        this.userObservableList = userObservableList;
    }

    public ObservableList<Structure> getStructureObservableList() {
        return structureObservableList;
    }

    public void setStructureObservableList(ObservableList<Structure> structureObservableList) {
        this.structureObservableList = structureObservableList;
    }

    public int getUserPageSize() {
        return userPageSize;
    }

    public void setUserPageSize(int userPageSize) {
        this.userPageSize = userPageSize;
    }

    public int getStructurePageSize() {
        return structurePageSize;
    }

    public void setStructurePageSize(int structurePageSize) {
        this.structurePageSize = structurePageSize;
    }

    public void openStatisticsHotel(ActionEvent event) {

        if( numberOfViewStructure < numberOfTotalStructure){
            Map<String, Object> map;
            if(!nextQueryStructureIsValid){
                map = HomepageController.getAllHotel(++indexStructurePage, structurePageSize);
            }else {
                map = HomepageController.getAllHotelByText(++indexStructurePage, structurePageSize,searchTextField.getText());
            }
            structures.addAll((Collection<Structure>) (map.get("collectionItems")));
            for(Structure s : structures){
                s.setAverageRating(HomepageController.getAvgStructureReview(s.getId()));
            }
            numberOfViewStructure=numberOfViewStructure+(Integer) map.get("sizeCollectionItems");
            if(!structures.isEmpty())
                structureObservableList.addAll(structures);
        }
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(searchTextField.getScene().getWindow());
        HomepageController.showStatisticView(stage, structures);
    }

    public void openStatisticsAttraction(ActionEvent event) {
        if( numberOfViewStructure < numberOfTotalStructure){
            Map<String, Object> map;
            if(!nextQueryStructureIsValid){
                map = HomepageController.getAllAttraction(++indexStructurePage, structurePageSize);
            }else {
                map = HomepageController.getAllAttractionByText(++indexStructurePage, structurePageSize,searchTextField.getText());
            }
            structures.addAll((Collection<Structure>) (map.get("collectionItems")));
            for(Structure s : structures){
                s.setAverageRating(HomepageController.getAvgStructureReview(s.getId()));
            }
            numberOfViewStructure=numberOfViewStructure+(Integer) map.get("sizeCollectionItems");
            if(!structures.isEmpty())
                structureObservableList.addAll(structures);
        }
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(searchTextField.getScene().getWindow());
        HomepageController.showStatisticView(stage,structures);
    }

    public void openStatisticsRestaurant(ActionEvent event) {
        if( numberOfViewStructure < numberOfTotalStructure){
            Map<String, Object> map;
            if(!nextQueryStructureIsValid){
                map = HomepageController.getAllRestaurant(++indexStructurePage, structurePageSize);
            }else {
                map = HomepageController.getAllRestaurantByText(++indexStructurePage, structurePageSize,searchTextField.getText());
            }
            structures.addAll((Collection<Structure>) (map.get("collectionItems")));
            for(Structure s : structures){
                s.setAverageRating(HomepageController.getAvgStructureReview(s.getId()));
            }
            numberOfViewStructure=numberOfViewStructure+(Integer) map.get("sizeCollectionItems");
            if(!structures.isEmpty())
                structureObservableList.addAll(structures);
        }
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(searchTextField.getScene().getWindow());
        HomepageController.showStatisticView(stage,structures );
    }

    public void exitPressed(ActionEvent actionEvent) {
        searchTextField.getScene().getWindow().hide();
    }

    public void signoutPressed(ActionEvent actionEvent) {
        HomepageController.signOut();
        searchTextField.getScene().getWindow().hide();

    }

    public void aboutPressed(ActionEvent actionEvent) {
        Dialog<String> dialog = new Dialog<String>();
        dialog.setTitle("Dialog");
        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        dialog.setContentText("Pannello Back Office Applicazione CoVid19\nCreato da Oreste Apa, Giuseppe De Luca, Mariamichela Graziano\n\nProgetto di Ingegneria del software 2019/2020");
        dialog.getDialogPane().getButtonTypes().add(type);
        dialog.showAndWait();
    }
}
