package com.revature.p0.screens;

import com.revature.p0.daos.UserDAO;
import com.revature.p0.models.Item;
import com.revature.p0.util.ArrayList;
import com.revature.p0.util.ScreenRouter;

import java.io.BufferedReader;
import java.io.IOException;

public class ShopScreen extends Screen{

    private BufferedReader consoleReader;
    private ScreenRouter router;
    private UserDAO userDAO;
    private boolean leaveStore;
    ArrayList<Item> shopItems = new ArrayList<>();

    public ShopScreen (BufferedReader consoleReader, ScreenRouter router, ArrayList<Item> shopItems){
        super("ShopScreen", "/Shop");
        this.consoleReader = consoleReader;
        this.router = router;
        this.shopItems = shopItems;
        this.leaveStore = false;
    }

    @Override
    public void render() {
        this.leaveStore = false;
        System.out.println("Welcome to Wally's Wares! Today we have: ");

        printStoreItems();

        System.out.println("Would you like to:");
        System.out.println("1) Purchase an Item");
        System.out.println("2) See the items again");
        System.out.println("3) Leave the store");

        while (leaveStore = false) {
            try {
                System.out.print("->");
                String userSelection = consoleReader.readLine();
                switch (userSelection) {
                    case "1":
                        buyAnItem();
                        break;
                    case "2":
                        //Reprint items
                        System.out.println("Here is what is for sale:");
                        printStoreItems();
                        break;
                    case "3":
                        leaveStore = true;
                        System.out.println("Thanks for stopping by. Please come again in the future.");
                        break;
                    default:
                        System.out.println("I don't recognize that request. Is it some wizard slang?");

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    public void printStoreItems(){
        for (int i = 0; i < shopItems.size(); i++) {
            Item item = shopItems.get(i);
            System.out.printf("%s | %s | Rarity: %s | Cost: %s\n", item.getName(), item.getDescription(), item.getRarity(), item.getValue());
        }

    }

    public void buyAnItem(){
        System.out.println("Please input 1, 2, or 3 to buy the corresponding item");
        //Check funds
            //If it won't create an overcharge subtract
            //Else return
        //Add Item to backpack and database;
    }
}
