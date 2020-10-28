package com.covid19.controller;

import com.covid19.model.UnauthorizedException;
import com.covid19.security.AuthManager;
import com.covid19.security.AuthManagerFactory;
import com.covid19.view.HomepageViewController;
import com.covid19.view.PopUpDialog;
import javafx.stage.Stage;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public  class SignInController {

    public static boolean signInPress(String usernameOrEmail , String password ) throws UnauthorizedException{
        if(usernameOrEmail==null || password==null){
            return false;
         }

         if ((usernameOrEmail.isBlank() ||  password.isBlank()) ) {
             PopUpDialog.showPopUpError("Some fields are empty!", "Enter the credentials again");
             return false;
         }

         String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
         Pattern pattern = Pattern.compile(regex);
         Matcher matcher = pattern.matcher(usernameOrEmail);

         AuthManager authManager = Objects.requireNonNull(AuthManagerFactory.getAuthManagerFactory()).getAuthManager();

         if (matcher.matches()) {
             if (!authManager.loginWithEmail(usernameOrEmail, password)) {
                 PopUpDialog.showPopUpError("Invalid credentials!", "Email o password are incorrect");
                 return false;
             }
         } else {
             if (!authManager.loginWithUsername(usernameOrEmail, password)) {
                 PopUpDialog.showPopUpError("Invalid credentials!", "Username o password are incorrect");
                 return false;
             }
         }
        showHomepage();
        return true;
    }

    private static void showHomepage(){
         try {
             Stage stage = new Stage();
             new HomepageViewController().start(stage);
         } catch (Exception e) {
             e.printStackTrace();
         }
     }

}
