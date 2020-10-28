package com.covid19.controller;


import com.covid19.model.UnauthorizedException;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SignInControllerTest {

    @Test
    void signInPressUsernameOrEmailOrPasswordNull() {
        assertFalse( SignInController.signInPress("giuseppedeluca15296",null));
        assertFalse( SignInController.signInPress(null,"Napoli1926"));

    }
    @Test
    void signInPressUsernameOrEmailOrPasswordEmpty() {
        assertFalse(SignInController.signInPress("giuseppedeluca15296",""));
        assertFalse( SignInController.signInPress("","Napoli1926"));
    }


    @Test
    void signInPressUsernameOrEmailExitsAndPasswordNotValid() {
        assertFalse( SignInController.signInPress("oreste96@hotmail.it","ewkflewr"));
        assertFalse( SignInController.signInPress("oreste96","ewkflewr"));
    }
    @Test
    void signInPressUsernameOrEmailExitsAndPasswordValid() {
        assertTrue( SignInController.signInPress("oreste96@hotmail.it","Napoli1926"));
        assertTrue( SignInController.signInPress("oreste96","Napoli1926"));
    }

    @Test
    void signInPressUsernameOrEmailNotExits() {
        assertFalse( SignInController.signInPress("oreste964","Napoli1926"));
        assertFalse( SignInController.signInPress("oresete96@hotmail.com","Napoli1926"));
    }


    @Test
    void signInPressUsernameOrEmailNotAdmin(){
        assertThrows(UnauthorizedException.class, () -> SignInController.signInPress("cinziape","Napoli1926" ));
        assertThrows(UnauthorizedException.class, () -> SignInController.signInPress("cinziape30@gmail.com","Napoli1926" ));
    }

}