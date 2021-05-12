package com.revature.p0.services;

import com.revature.p0.exceptions.UserInputException;
import com.revature.p0.util.Regex;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class InputValidationTest {
    private InputValidation sut;

    @Before
    public void SetUp(){
        sut = new InputValidation();
    }

    @After
    public void TearDown(){
        sut = null;
    }

    @Test
    public void test_UsernameValidationSuccess(){
        String input = "Username";


        String recieved = null;
        try {
            recieved = sut.testUserInput(input, Regex.USERNAME_PATTERN, "Error");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Assert.assertSame(input, recieved);
    }

    @Test(expected = UserInputException.class)
    public void test_UsernameValidationFail(){
        String input = "1111";

        try {
            sut.testUserInput(input, Regex.USERNAME_PATTERN, "Error");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test_PasswordValidationSuccess(){
        String input = "A1ys";


        String recieved = null;
        try {
            recieved = sut.testUserInput(input, Regex.PASSWORD_PATTERN, "Error");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Assert.assertSame(input, recieved);
    }

    @Test(expected = UserInputException.class)
    public void test_PasswordValidationFail(){
        String input = "A1ys ";

        try {
            sut.testUserInput(input, Regex.PASSWORD_PATTERN, "Error");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test_FirstNameValidationSuccess(){
        String input = "First Name";


        String recieved = null;
        try {
            recieved = sut.testUserInput(input, Regex.FIRSTNAME_PATTERN, "Error");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Assert.assertSame(input, recieved);
    }

    @Test(expected = UserInputException.class)
    public void test_FirstnameValidationFail(){
        String input = "1111";

        try {
            sut.testUserInput(input, Regex.FIRSTNAME_PATTERN, "Error");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test_EmailValidationSuccess(){
        String input = "hello@mail.com";


        String recieved = null;
        try {
            recieved = sut.testUserInput(input, Regex.EMAIL_PATTERN, "Error");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Assert.assertSame(input, recieved);
    }

    @Test(expected = UserInputException.class)
    public void test_EmailValidationFail(){
        String input = "hello@mail;drop";

        try {
            sut.testUserInput(input, Regex.EMAIL_PATTERN, "Error");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
