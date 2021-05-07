package com.revature.p0.screens;

import com.revature.p0.models.AppUser;
import com.revature.p0.util.ConnectionFactory;
import com.revature.p0.util.ScreenRouter;

import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileScreen extends Screen{

    private BufferedReader consoleReader;
    private ScreenRouter router;
    private AppUser user;

    public ProfileScreen(BufferedReader consoleReader, ScreenRouter router) {
        super("ProfileScreen", "/Profile");
        this.consoleReader = consoleReader;
        this.router = router;
    }

    public void setUser (AppUser user){
        this.user = user;
    }

    @Override
    public void render() {

        setUser(router.getUser());

        System.out.println("Welcome back " + user.getFirstName() + " " + user.getLastName() + "!");
        System.out.println("How can we Help you today?");
        System.out.println("1) I want to see how much treasure I have");
        System.out.println("2) I want to see all my items");
        System.out.println("3) I want to hear about by past adventures.");
        System.out.println("4) I wanna go find more gold");

        try {
            System.out.print("->");
            String userSelection = consoleReader.readLine();

            switch (userSelection){
                case "1":
                    printValueOfAccount();
                    //print out gold totals
                    break;
                case "2":
                    //Display corresponding items from item table
                    break;
                case "3":
                    //Display previous transactions
                    break;
                case "4":
                    //Navigate to minigame screen
                    break;
                default:
                    System.out.println("My apologies, but I don't recognize that runic symbol");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void printValueOfAccount(){
        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "select goldpieces, dragonshards from p0.users where user_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, user.getId());

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                System.out.println("You still have " + rs.getDouble("goldpieces") + " gold pieces and " +
                        rs.getInt("dragonshards") + " Dragon Shards in your account.");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

    }
}
