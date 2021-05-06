package com.revature.p0.screens;

import com.revature.p0.util.ScreenRouter;

import java.io.BufferedReader;
import java.nio.Buffer;

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
        System.out.println("Welcome Adventure to the world of Fairune's first Tavern & Bank!");
        System.out.println("Are you .....");
        System.out.println("1) A Returning Adventurer?");
        System.out.println("2) A New Adventurer?");
        System.out.println("3) or Taking A Break From Adventuring?");

        try {
            System.out.println("->");
            String userSelection = consoleReader.readLine();

            switch (userSelection){
                case "1":
                    System.out.println("Welcome back Adventurer! Let's find you in our records.");
                    //go to Login
                    break;
                case "2":
                    System.out.println("Grertings new Adventurer. Lets get you set up in our records");
                    //go to register
                    break;
                case "3":
                    System.out.println("See you later adventure, and have a magical day!");
                    //Tell app to stop
                    break;

                default:
                    System.out.println("My apologies, but I do not recognize those provided runes");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}