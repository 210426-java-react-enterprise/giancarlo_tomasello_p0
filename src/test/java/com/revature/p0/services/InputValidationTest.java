package com.revature.p0.services;

import com.revature.p0.UserInputException;
import com.revature.p0.util.Regex;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.regex.Pattern;

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
        } catch (IOException | UserInputException e) {
            e.printStackTrace();
        }

        Assert.assertSame(input, recieved);
    }

    @Test(expected = IOException.class)
    public void test_UsernameValidationFail(){
        String input = "1111";

        String recieved = null;
        try {
            recieved = sut.testUserInput(input, Regex.USERNAME_PATTERN, "Error");
        } catch (IOException | UserInputException e) {
            e.printStackTrace();
        }

    }

}
