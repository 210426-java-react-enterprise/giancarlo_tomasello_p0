package com.revature.p0.util;

import com.revature.p0.daos.UserDAO;
import com.revature.p0.screens.WelcomeScreen;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AppState {

    private BufferedReader consoleReader;
    private ScreenRouter screenRouter;
    private boolean appRunning;

    public AppState(){
        System.out.println("Rolling the Dice and Initializing the App");

        this.appRunning = true;
        this.consoleReader = new BufferedReader(new InputStreamReader(System.in));

        final UserDAO userDao = new UserDAO();

        screenRouter = new ScreenRouter();
        screenRouter.addScreen(new WelcomeScreen(consoleReader, screenRouter));

        System.out.println("Initialized app and prepared NPCs");

    }

    public boolean isAppRunning(){
        return appRunning;
    }

    public void setAppRunning(boolean appRunning){
        this.appRunning = appRunning;
    }

    public ScreenRouter getScreenRouter(){
        return screenRouter;
    }
}
