package com.revature.p0.screens;

import com.revature.p0.util.ScreenRouter;

import java.io.BufferedReader;

public class LoginScreen extends Screen{

    private BufferedReader consoleReader;
    private ScreenRouter router;

    public LoginScreen(BufferedReader consoleReader, ScreenRouter router){
        super("LoginScreen", "/Login");
        this.consoleReader = consoleReader;
        this.router = router;
    }

    @Override
    public void render() {

    }
}
