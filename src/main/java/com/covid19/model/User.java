package com.covid19.model;

import lombok.Data;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
public class User {

    private Integer id;
    private String username;
    private String email;
    private String name;
    private String surname;
    private String password;
    private Date dateOfBirth;
    private Boolean preferencesView;
    private Gender gender;
    private Boolean enabled;


    public boolean isValidEmail(String email){
        String mailPattern = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(mailPattern);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches() || email.length() > 320;
    }




}
