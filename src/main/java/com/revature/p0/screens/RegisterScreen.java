package com.revature.p0.screens;

import com.revature.p0.daos.UserDAO;
import com.revature.p0.exceptions.UserInputException;
import com.revature.p0.models.AppUser;
import com.revature.p0.models.Item;
import com.revature.p0.services.InputValidation;
import com.revature.p0.services.UserService;
import com.revature.p0.util.ArrayList;
import com.revature.p0.util.LinkedList;
import com.revature.p0.util.Regex;
import com.revature.p0.util.ScreenRouter;

import java.io.BufferedReader;

public class RegisterScreen extends Screen{

    private BufferedReader consoleReader;
    private ScreenRouter router;
    private InputValidation validator;
    private UserService userService;
    private final String usernameError = "Your username was invalid. Make sure it starts with a letter, is at least" +
            " 2 characters long, and contain no special characters aside from _";
    private final String passwordError = "Your password was invalid. Make sut it has a capital letter, a number " +
            "is at least 4 characters long but no longer than 50 characters, has no whiteSpaces, and only uses " +
            "certain special characters characters. (No ;, =, ], etc.)";
    private final String nameError = "There were problems with this name. Make sure you only use Letters, Underscores," +
            " or dashes.";
    private final String emailError = "There was a problem with your email. Make sure you are only using letters, " +
            "numbers, underscores and dashes. And avoid using underscores in the domain name.";

    public RegisterScreen(BufferedReader consoleReader, ScreenRouter router, UserService userService, InputValidation validator){
        super("RegisterScreen", "/Register");
        this.consoleReader = consoleReader;
        this.router = router;
        this.validator = validator;
        this.userService = userService;
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
        ArrayList<Item> backpack = new ArrayList<>();

        try{
            System.out.println("Salutations new adventurer! Let me jot down your information so I can keep a record" +
                    " of your accolades.");
            System.out.println("*~~~~~~~~~~~~~~~~*");

            System.out.print("Username: ");
            username = validator.testUserInput(consoleReader.readLine(), Regex.USERNAME_PATTERN, usernameError);

            System.out.print("Password: ");
            password = validator.testUserInput(consoleReader.readLine(), Regex.PASSWORD_PATTERN, passwordError);

            System.out.print("First name: ");
            firstName = validator.testUserInput(consoleReader.readLine(), Regex.FIRSTNAME_PATTERN, nameError);

            System.out.print("Last name: ");
            lastName = validator.testUserInput(consoleReader.readLine(), Regex.FIRSTNAME_PATTERN, nameError);

            System.out.print("Email: ");
            email = validator.testUserInput(consoleReader.readLine(), Regex.EMAIL_PATTERN, emailError);

            System.out.print("Starting gold: ");
            goldPieces = Double.parseDouble(consoleReader.readLine());
            validator.testMoneyInput(goldPieces);

            System.out.print("Starting DragonShards: ");
            dragonShards = Integer.parseInt(consoleReader.readLine());
            validator.testMoneyInput(dragonShards);

            AppUser newUser = new AppUser(username, password, email, firstName, lastName, goldPieces, dragonShards,
                    backpack);

            userService.register(newUser);

        } catch (UserInputException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
