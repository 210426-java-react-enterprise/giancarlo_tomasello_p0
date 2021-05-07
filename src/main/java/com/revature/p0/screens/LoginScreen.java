package com.revature.p0.screens;

import com.revature.p0.daos.UserDAO;
import com.revature.p0.models.AppUser;
import com.revature.p0.util.ScreenRouter;

import java.io.BufferedReader;

public class LoginScreen extends Screen{

    private BufferedReader consoleReader;
    private ScreenRouter router;
    private UserDAO userDAO;

    public LoginScreen(BufferedReader consoleReader, ScreenRouter router, UserDAO userDAO){
        super("LoginScreen", "/Login");
        this.consoleReader = consoleReader;
        this.router = router;
        this.userDAO = userDAO;
    }

    @Override
    public void render() {

        try{
            String username;
            String password;

            System.out.println("Welcome back Adventurer! Let's find you in our records.");

            System.out.print("Can I have your username:");
            username = consoleReader.readLine();

            System.out.print("And now your password: ");
            password = consoleReader.readLine();

            if(username != null && !username.isEmpty() && password != null && !password.isEmpty()){
                AppUser authenticatedUser = userDAO.findUserByUsernameAndPassword(username, password);
                if (authenticatedUser != null) {
                    System.out.println("Login successful!");
                    router.setUser(authenticatedUser);
                    router.navigate("/Profile");
                    //Navigate into user menu, pass along authenicatedUser?
                } else {
                    System.out.println("Login failed!");
                    System.out.println("Returning to the front desk!");
                    System.out.println("\n");
                }
            }else {
                System.out.println("It looks like you didn't provide any credentials!");
                System.out.println("Returning to the front desk!");
                System.out.println("\n");
            }

        } catch (Exception e){

        }

    }
}
