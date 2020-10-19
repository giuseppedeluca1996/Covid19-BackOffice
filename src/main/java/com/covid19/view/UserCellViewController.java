package com.covid19.view;

import com.covid19.controller.HomepageController;
import com.covid19.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class UserCellViewController extends ListCell<User>  {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label usernameLabelValue;

    @FXML
    private Label emailLabelValue;

    @FXML
    private Label nameLabelValue;

    @FXML
    private Label dateOfBirthLabelValue;

    @FXML
    private Label genderLabelValue;

    @FXML
    private Label surnameLabelValue;

    @FXML
    private Label isEnabledLabelValue;


    private FXMLLoader mLLoader;

    private User user;

    public UserCellViewController() {

        if (mLLoader == null) {
            mLLoader = new FXMLLoader(getClass().getResource("/UserCell.fxml"));
            mLLoader.setController(this);
            try {
                mLLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    protected void updateItem(User user, boolean empty) {
        super.updateItem(user, empty);

        if(empty || user == null) {

            setText(null);
            setGraphic(null);

        } else {
            this.user=user;
            usernameLabelValue.setText(user.getUsername());
            emailLabelValue.setText(user.getEmail());
            nameLabelValue.setText(user.getName());
            surnameLabelValue.setText(user.getSurname());
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            dateOfBirthLabelValue.setText(formatter.format(user.getDateOfBirth()));
            genderLabelValue.setText(user.getGender().toString());
            isEnabledLabelValue.setText(user.getEnabled().toString());

            setText(null);
            setGraphic(anchorPane);
        }

    }

    @FXML
    void deleteUser(MouseEvent event) {
        if(PopUpDialog.showPopUpWarning("Delete user!", "You are sure to delete this user?")){
            HomepageController.deleteUser(user);
        }


    }


}
