package com.revature.p0.screens;

import com.revature.p0.daos.UserDAO;
import com.revature.p0.util.ScreenRouter;

import java.io.BufferedReader;

public class ShopScreen extends Screen{

    private BufferedReader consoleReader;
    private ScreenRouter router;
    private UserDAO userDAO;

    public ShopScreen (BufferedReader consoleReader, ScreenRouter router, UserDAO userDAO){
        super("ShopScreen", "/Shop");
        this.consoleReader = consoleReader;
        this.router = router;
        this.userDAO = userDAO;
    }

    @Override
    public void render() {
        System.out.println("Welcome to Wally's Wares!");
    }
}
