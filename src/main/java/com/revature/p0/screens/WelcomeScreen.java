package com.revature.p0.screens;

import com.revature.p0.util.AppState;
import com.revature.p0.util.ScreenRouter;

import java.io.BufferedReader;
import java.nio.Buffer;

import static com.revature.p0.Driver.getApp;

public class WelcomeScreen extends Screen{

    private BufferedReader consoleReader;
    private ScreenRouter router;

    public WelcomeScreen(BufferedReader consoleReader, ScreenRouter router){
        super("WelcomeScreen", "/Welcome");
        this.consoleReader = consoleReader;
        this.router = router;
    }

    @Override
    public void render() {
        System.out.println("Welcome Adventurer to the world of Fayrune's first Tavern & Bank!");
        System.out.println("Are you .....");
        System.out.println("1) A Returning Adventurer?");
        System.out.println("2) A New Adventurer?");
        System.out.println("3) or Taking A Break From Adventuring?");

        try {
            System.out.println("->");
            String userSelection = consoleReader.readLine();

            switch (userSelection){
                case "1":
                    //go to Login
                    router.navigate("/Login");
                    break;
                case "2":
                    //go to register
                    router.navigate("/Register");
                    break;
                case "3":
                    System.out.println("See you later adventurer, and have a magical day!");
                    getApp().setAppRunning(false);
                    break;

                default:
                    System.out.println("My apologies, but I do not recognize those provided runes");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
