package com.revature.p0.screens;

import com.revature.p0.daos.UserDAO;

import java.io.BufferedReader;

public class RegisterScreen extends Screen{

    private UserDAO userDAO = new UserDAO(); //OK for now, but is grows. Find a way to make on a higher scope
    private BufferedReader consoleReader;

    public RegisterScreen(BufferedReader consoleReader){
        this.consoleReader = consoleReader;
    }

    @Override
    public void render() {
        String username;
        String password;
        String email;
        String firstName;
        String lastName;
        double goldPieces;
        int dragonShards;

        try{
            System.out.println("Register for a new account!");
            System.out.println("+-------------------------+");

            System.out.println("Enter your desired Username: ");
            username = consoleReader.readLine();

            System.out.println("Enter a secure password: ");
            password = consoleReader.readLine();

            System.out.println("Enter your email: ");
            email = consoleReader.readLine();

            System.out.println("Enter your first name: ");
            firstName = consoleReader.readLine();

            System.out.println("Enter your last name: ");
            lastName = consoleReader.readLine();

            System.out.println("Enter your starting ammount of gold: ");
            goldPieces = Double.parseDouble(consoleReader.readLine());

            System.out.println("Enter your startning number of dragon shards: ");
            dragonShards = Integer.parseInt(consoleReader.readLine());


        } catch (NumberFormatException nfe){
            System.err.println("You need to provide a correct value for age! Please try again!");
            this.render();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
