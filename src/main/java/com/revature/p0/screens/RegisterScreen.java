package com.revature.p0.screens;

import com.revature.p0.daos.UserDAO;
import com.revature.p0.models.AppUser;
import com.revature.p0.util.ScreenRouter;

import java.io.BufferedReader;

public class RegisterScreen extends Screen{

    private UserDAO userDAO = new UserDAO();
    private BufferedReader consoleReader;
    private ScreenRouter router;

    public RegisterScreen(BufferedReader consoleReader, ScreenRouter router){
        super("RegisterScreen", "/Register");
        this.consoleReader = consoleReader;
        this.router = router;
    }

    @Override
    public void render() {

        String username;
        String password;
        String firstName;
        String lastName;
        String email;
        double goldPieces;
        int dragonShards;

        try{
            System.out.println("Salutations new adventurer! Let me jot down your information so I can keep a record" +
                    " of your accolades.");
            System.out.println("*~~~~~~~~~~~~~~~~*");

            System.out.print("Username: ");
            username = consoleReader.readLine();

            System.out.print("Password: ");
            password = consoleReader.readLine();

            System.out.print("First name: ");
            firstName = consoleReader.readLine();

            System.out.print("Last name: ");
            lastName = consoleReader.readLine();

            System.out.print("Email: ");
            email = consoleReader.readLine();

            System.out.print("Starting gold: ");
            goldPieces = Double.parseDouble(consoleReader.readLine());

            System.out.print("Starting DragonShards: ");
            dragonShards = Integer.parseInt(consoleReader.readLine());

            AppUser newUser = new AppUser(username, password, email, firstName, lastName, goldPieces, dragonShards);
            userDAO.save(newUser);

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
