package com.revature.p0.screens;

import com.revature.p0.daos.UserDAO;
import com.revature.p0.models.AppUser;
import com.revature.p0.models.Item;
import com.revature.p0.util.ArrayList;
import com.revature.p0.util.ScreenRouter;

import java.io.BufferedReader;
import java.io.IOException;

public class ShopScreen extends Screen{

    private BufferedReader consoleReader;
    private UserDAO userDAO;
    private boolean leaveStore;
    ArrayList<Item> shopItems = new ArrayList<>();

    public ShopScreen (BufferedReader consoleReader, UserDAO userDAO, ArrayList<Item> shopItems){
        super("ShopScreen", "/Shop");
        this.consoleReader = consoleReader;
        this.shopItems = shopItems;
        this.leaveStore = false;
        this.userDAO = userDAO;
    }

    @Override
    public void render() {
        this.leaveStore = false;
        System.out.println("Welcome to Wally's Wares! Today we have: ");

        printStoreItems();

        while (leaveStore == false) {
            System.out.println("Would you like to:");
            System.out.println("1) Purchase an Item");
            System.out.println("2) See the items again");
            System.out.println("3) Leave the store");

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

        System.out.println("Leaving the store now!");

    }

    public void printStoreItems(){
        System.out.println("+---------------+");
        for (int i = 0; i < shopItems.size(); i++) {
            Item item = shopItems.get(i);
            System.out.printf("%s | %s | Rarity: %s | Cost: %s\n", item.getName(), item.getDescription(), item.getRarity(), item.getValue());
        }
        System.out.println("+---------------+");
    }

    public void buyAnItem(){
        AppUser currentUser = userDAO.getCurrentUser();
        Item boughtItem = new Item();

        System.out.println("Please input 1, 2, or 3 to buy the corresponding item");
        try {
            System.out.print("->");
            String userSelection = consoleReader.readLine();
            boughtItem = shopItems.get(Integer.parseInt(userSelection)-1);

            System.out.println(boughtItem.toString());

        } catch (IOException e){
            e.printStackTrace();
        }

        //CheckOverage() in userService
        double newValue = currentUser.getGoldPieces()-boughtItem.getValue();
        if(newValue >= 0){
            currentUser.setGoldPieces(newValue);
            userDAO.updateValueOfAccount("goldpieces", newValue, currentUser.getId(), -1);
            System.out.printf("You bought an item for %.2f and now you have %.2f gold pieces.\n", boughtItem.getValue(), newValue);

            currentUser.getBackpack().add(boughtItem);
            userDAO.addItemToDatabase(currentUser.getId(), boughtItem);
        } else {
            System.out.println("I'm sorry but you don't have enough to buy that item");
        }



    }
}
