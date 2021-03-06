package com.revature.p0.screens;

import com.revature.p0.daos.UserDAO;
import com.revature.p0.models.AppUser;
import com.revature.p0.models.Item;
import com.revature.p0.util.ArrayList;
import com.revature.p0.util.ConnectionFactory;
import com.revature.p0.util.ScreenRouter;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class ProfileScreen extends Screen{

    private BufferedReader consoleReader;
    private ScreenRouter router;
    private UserDAO userDAO;
    private AppUser user;
    boolean exitScreen;

    public ProfileScreen(BufferedReader consoleReader, ScreenRouter router, UserDAO userDAO) {
        super("ProfileScreen", "/Profile");
        this.consoleReader = consoleReader;
        this.router = router;
        this.userDAO = userDAO;
    }

    public void setUser (AppUser user){
        this.user = user;
    }

    @Override
    public void render() {
        exitScreen = false;
        setUser(userDAO.getCurrentUser());
        System.out.println(user.getBackpack().size());

        System.out.println("Welcome back " + user.getFirstName() + " " + user.getLastName() + "!");
        System.out.println("How can we Help you today?");
        System.out.println("1) I want to see how much treasure I have");
        System.out.println("2) I want to see all my items");
        System.out.println("3) I want to hear about by future adventures");
        System.out.println("4) I wanna go find more gold");
        System.out.println("5) I wanna go to the shop");
        System.out.println("6) I'm done for the day");
        while(!exitScreen) {
            try {

                System.out.print("->");
                String userSelection = consoleReader.readLine();

                switch (userSelection) {
                    case "1":
                        //Print out the value of the user's account
                        userDAO.printValueOfAccount(user);
                        break;
                    case "2":
                        //Display the items that belong to the user's account
                        printBackpackArray();
                        break;
                    case "3":
                        //Display previous transactions
                        System.out.println("You will will travel to many new lands meeting new friends along the way");
                        break;
                    case "4":
                        //Navigate to minigame screen
                        router.navigate("/Minigame");
                        break;
                    case "5":
                        //Navigate to the shop screen
                        router.navigate("/Shop");
                        System.out.println("New total: " + user.getGoldPieces());
                        break;
                    case "6":
                        exitScreen = true;
                        break;
                    default:
                        System.out.println("My apologies, but I don't recognize that runic symbol");
                        exitScreen = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println("Update the functions");
            }
        }
    }

    public void printBackpackArray(){
        ArrayList<Item> backpack = user.getBackpack();
        System.out.println("Let's see what you have.");
        for (int i = 0; i < backpack.size(); i++) {
            System.out.printf("%d] %s | %s | %s | %s Gp \n",
                    i + 1, backpack.get(i).getName(), backpack.get(i).getDescription(),
                    backpack.get(i).getRarity(), backpack.get(i).getValue());
        }
    }


}
