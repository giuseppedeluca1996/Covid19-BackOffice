package com.covid19.view;

import com.covid19.controller.SignInController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;




public class SignInViewController extends Application {

    @FXML
    private TextField usernameOrEmailTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private Button signInButton;

    @FXML
    public void initialize(){
        signInButton.setDefaultButton(true);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/SignInScreen.fxml"));
        primaryStage.setTitle("CoVid19");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void signInPress(ActionEvent actionEvent) {
        signInButton.setDisable(true);
        if (SignInController.signInPress(usernameOrEmailTextField.getText(), passwordTextField.getText())){
            signInButton.getScene().getWindow().hide();
        }else {
            passwordTextField.clear();
            usernameOrEmailTextField.clear();
            signInButton.setDisable(false);
        }

    }
}
