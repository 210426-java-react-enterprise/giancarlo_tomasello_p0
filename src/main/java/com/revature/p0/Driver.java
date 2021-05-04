package com.revature.p0;

import com.revature.p0.screens.RegisterScreen;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Driver {
    public static void main(String[] args) {
        try(BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))){
            RegisterScreen registerScreen = new RegisterScreen(consoleReader);
            registerScreen.render();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
