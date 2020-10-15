package com.covid19.view;
import com.covid19.controller.HomepageController;
import com.covid19.model.AvgRatingReviewOfUser;
import com.covid19.model.Gender;
import com.covid19.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.Rating;


import java.io.IOException;
import java.text.DateFormatSymbols;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class SpecificUserViewController {

    @FXML
    private TextField surnameTextFiled;

    @FXML
    private ComboBox<String> genderComboBox;

    @FXML
    private TextField nameTextField;

    @FXML
    private DatePicker dateOfBirthPicker;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField usernameTextField;

    @FXML
    private Button applyChangesButton;

    @FXML
    private Button deleteButton;

    @FXML
    private BarChart<String,Double> reviewForMontChart;

    @FXML
    private Label starLabel;

    @FXML
    private Label numberReviewLabel;

    @FXML
    private Button backButton;

    @FXML
    private ComboBox<String> stateComboBox;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private Rating starUserRating;

    @FXML
    private ImageView backImage;


    private User selectedUser;

    private ObservableList<String> monthNames = FXCollections.observableArrayList();
    private String[] months = DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths();



    public void initData(User user){

        selectedUser = user;

        usernameTextField.setText(selectedUser.getUsername());
        nameTextField.setText(selectedUser.getName());
        surnameTextFiled.setText(selectedUser.getSurname());
        emailTextField.setText(selectedUser.getEmail());
        dateOfBirthPicker.setValue(selectedUser.getDateOfBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

        ObservableList<String> listGender = FXCollections.observableArrayList();
        listGender.addAll(Gender.FEMALE.toString(),Gender.MALE.toString());
        genderComboBox.setItems(listGender);
        if ((selectedUser.getGender() == Gender.FEMALE)) {
            genderComboBox.setValue(Gender.FEMALE.toString());
        } else {
            genderComboBox.setValue(Gender.MALE.toString());
        }

        ObservableList<String> listState = FXCollections.observableArrayList();
        listState.addAll("TRUE","FALSE");
        stateComboBox.setItems(listState);

        stateComboBox.setValue(selectedUser.getEnabled().toString());


        List<AvgRatingReviewOfUser> averageOfReviewUserInYear=HomepageController.getAverageOfReviewsUserInYear(user.getId(), LocalDate.now().getYear() );
        monthNames.addAll(Arrays.asList(months));
        xAxis.setCategories(monthNames);
        XYChart.Series<String,Double> series = new XYChart.Series<>();
        for (AvgRatingReviewOfUser a : averageOfReviewUserInYear){
            series.getData().add(new XYChart.Data<String,Double>(monthNames.get(a.getMonth()),a.getAvgRating()));
        }
        reviewForMontChart.getData().add(series);

        starUserRating.setRating(HomepageController.getAverageOfReviewsUserByEmail(user.getEmail()));
        numberReviewLabel.setText(HomepageController.getNumberOfUserReviewsByEmail(user.getEmail()).toString());

    }

    public void applyChangeUser(ActionEvent event) {
        if(PopUpDialog.showPopUpWarning("Attention", "Are you sure to apply this change?")){
            selectedUser.setEmail(emailTextField.getText());
            selectedUser.setName(nameTextField.getText());
            selectedUser.setGender(Gender.valueOf(genderComboBox.getValue()));
            selectedUser.setEnabled(Boolean.valueOf(stateComboBox.getValue()));
            selectedUser.setUsername(usernameTextField.getText());
            selectedUser.setSurname(surnameTextFiled.getText());
            LocalDate localDate = dateOfBirthPicker.getValue();
            Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            Date date = Date.from(instant);
            System.out.println(localDate + "\n" + instant + "\n" + date);
            selectedUser.setDateOfBirth(date);
            HomepageController.updateUserByUsername(selectedUser,selectedUser.getUsername());
        }
    }

    public void deleteUser(ActionEvent event) {
        if(PopUpDialog.showPopUpWarning("Delete user!", "Are you sure to delete this user?")){
            HomepageController.deleteUserByEmail(selectedUser);
        }
    }

    public void backClicked(MouseEvent event) throws IOException {

        emailTextField.getScene().getWindow().hide();

    }
}
