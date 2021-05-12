package com.revature.p0.services;

import com.revature.p0.UserInputException;
import com.revature.p0.util.Regex;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidation {

    private BufferedReader consoleReader;

    public InputValidation(){

    }

    public String testUserInput(String toBeChecked, Regex Pattern, String ErrorMessage) throws IOException, UserInputException {

        Pattern p = java.util.regex.Pattern.compile(Pattern.getPattern());
        Matcher m = p.matcher(toBeChecked);

        if(!m.find()){
            System.out.println("Inncorrect input, throw error");
            throw new UserInputException(ErrorMessage);

        }

        System.out.println("Pattern is succesful.");
        return toBeChecked;
    }
}
