package com.revature.p0.util;

import com.revature.p0.daos.UserDAO;
import com.revature.p0.models.Item;
import com.revature.p0.screens.*;
import com.revature.p0.services.InputValidation;
import com.revature.p0.services.UserService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AppState {

    private BufferedReader consoleReader;
    private ScreenRouter screenRouter;
    private UserService userService;
    private InputValidation validator;
    private boolean appRunning;

    public AppState(){
        System.out.println("Rolling the Dice and Initializing the App");

        this.appRunning = true;
        this.consoleReader = new BufferedReader(new InputStreamReader(System.in));


        final UserDAO userDao = new UserDAO();

        validator = new InputValidation();
        userService = new UserService(userDao);
        ArrayList<Item> ShopsItems = userDao.randomizeShop();

        screenRouter = new ScreenRouter();
        screenRouter.addScreen(new WelcomeScreen(consoleReader, screenRouter))
                .addScreen(new LoginScreen(consoleReader, screenRouter, userService, validator))
                .addScreen(new RegisterScreen(consoleReader, screenRouter,userService, validator))
                .addScreen(new ProfileScreen(consoleReader, screenRouter, userDao))
                .addScreen(new MinigameScreen(consoleReader, screenRouter, userDao))
                .addScreen(new ShopScreen(consoleReader, userDao, ShopsItems));

        System.out.println("Initialized app and prepared NPCs");

    }

    /**
     * Returns a bool that states whether or not the app is still running
     *
     * @return bool
     */
    public boolean isAppRunning(){
        return appRunning;
    }

    /**
     * Takes in a boolean to set if the app is running
     * @param appRunning A boolean stating if the app is running or not
     */
    public void setAppRunning(boolean appRunning){
        this.appRunning = appRunning;
    }

    /**
     * Returns the screen router object to be used in screen navigation
     * @return screenRouter A linkedList holding all the screens
     */
    public ScreenRouter getScreenRouter(){
        return screenRouter;
    }

}
