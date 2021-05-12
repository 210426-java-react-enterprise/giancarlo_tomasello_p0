package com.revature.p0.screens;

import com.revature.p0.daos.UserDAO;
import com.revature.p0.exceptions.UserInputException;
import com.revature.p0.models.AppUser;
import com.revature.p0.services.InputValidation;
import com.revature.p0.services.UserService;
import com.revature.p0.util.Regex;
import com.revature.p0.util.ScreenRouter;

import java.io.BufferedReader;

public class LoginScreen extends Screen{

    private BufferedReader consoleReader;
    private ScreenRouter router;
   // private UserDAO userDAO;
    private InputValidation validator;
    private UserService userService;
    private final String usernameError = "Your username was invalid. Remember it must start with a letter, be at least" +
            " 2 characters long, and contain no special characters aside from _";
    private final String passwordError = "Your password was invalid. Remember it must have a capital letter, a number " +
            "be at least 4 characters long but no longer than 50 characters, and no whiteSpaces, and limited special " +
            "characters";

    public LoginScreen(BufferedReader consoleReader, ScreenRouter router, UserService userService, InputValidation validator){
        super("LoginScreen", "/Login");
        this.consoleReader = consoleReader;
        this.router = router;
       // this.userDAO = userDAO;
        this.userService = userService;
        this.validator = validator;
    }

    @Override
    public void render() {

        try{
            String username;
            String password;

            System.out.println("Welcome back Adventurer! Let's find you in our records.");

            System.out.print("Can I have your username:");
//            username = consoleReader.readLine();
//            validator.testUserInput(username, Regex.USERNAME_PATTERN, usernameError);

            username = validator.testUserInput(consoleReader.readLine(), Regex.USERNAME_PATTERN, usernameError);


            System.out.print("And now your password: ");
            //password = consoleReader.readLine();
            password = validator.testUserInput(consoleReader.readLine(), Regex.PASSWORD_PATTERN, passwordError);

            //validate username and password

            //Using the UserService establish a connection and find the user in db
            userService.findUserInDatabase(username, password);


//            if(username != null && !username.isEmpty() && password != null && !password.isEmpty()){
//                AppUser authenticatedUser = userDAO.findUserByUsernameAndPassword(username, password);
//                if (authenticatedUser != null) {
//                    System.out.println("Login successful!");
//                    router.setUser(authenticatedUser);
//                    router.navigate("/Profile");
//                    //Navigate into user menu, pass along authenicatedUser?
//                } else {
//                    System.out.println("Login failed!");
//                    System.out.println("Returning to the front desk!");
//                    System.out.println("\n");
//                }
//            }else {
//                System.out.println("It looks like you didn't provide any credentials!");
//                System.out.println("Returning to the front desk!");
//                System.out.println("\n");
//            }

        } catch (UserInputException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
