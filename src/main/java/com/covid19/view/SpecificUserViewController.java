package com.covid19.view;
import com.covid19.controller.HomepageController;
import com.covid19.model.Gender;
import com.covid19.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

import java.io.IOException;
import java.text.DateFormatSymbols;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
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
    private LineChart<String, Integer> GraphicsLineChart;

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
    private Stage homepageStage;
    private ObservableList<String> monthNames = FXCollections.observableArrayList();
    String[] months = DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths();



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
        if(selectedUser.getGender()==Gender.FEMALE){
            genderComboBox.setValue(Gender.FEMALE.toString());
        }else{
            genderComboBox.setValue(Gender.MALE.toString());

        }
        stateComboBox.setValue(selectedUser.getEnabled().toString());
        ObservableList<String> listState = FXCollections.observableArrayList();
        listState.addAll("TRUE","FALSE");
        stateComboBox.setItems(listState);
        if(selectedUser.getEnabled() ){
            stateComboBox.setValue("TRUE");
        }else{
            stateComboBox.setValue("FALSE");
        }


        //INIZIALIZZAZIONE DATI DIAGRAMMA
        monthNames.addAll(Arrays.asList(months));
        xAxis.setCategories(monthNames);
        starUserRating.setRating(3.5);



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
