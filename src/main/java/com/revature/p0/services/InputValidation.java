package com.revature.p0.services;

import com.revature.p0.exceptions.UserInputException;
import com.revature.p0.util.Regex;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidation {

    public String testUserInput(String toBeChecked, Regex pattern, String ErrorMessage) throws IOException, UserInputException {

        Pattern p = Pattern.compile(pattern.getPattern());
        Matcher m = p.matcher(toBeChecked);

        if(!m.find()){
            System.out.println("Inncorrect input, throw error");
            throw new UserInputException(ErrorMessage);

        }

        System.out.println("Pattern is succesful.");
        return toBeChecked;
    }
}
