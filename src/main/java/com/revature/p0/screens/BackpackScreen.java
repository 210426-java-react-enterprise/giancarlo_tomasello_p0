package com.revature.p0.screens;

import com.revature.p0.daos.UserDAO;
import com.revature.p0.util.ScreenRouter;

import java.io.BufferedReader;

public class BackpackScreen {

    private BufferedReader consoleReader;
    private ScreenRouter router;
    private UserDAO userDAO;

    public BackpackScreen (BufferedReader consoleReader, ScreenRouter router, UserDAO userDAO){
        this.consoleReader = consoleReader;
        this.router = router;
        this.userDAO = userDAO;
    }



}
