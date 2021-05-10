package com.revature.p0.screens;

import com.revature.p0.daos.UserDAO;
import com.revature.p0.models.AppUser;
import com.revature.p0.util.ScreenRouter;

import java.io.BufferedReader;
import java.io.IOException;

public class MinigameScreen extends Screen{

    private BufferedReader consoleReader;
    private ScreenRouter router;
    private UserDAO userDAO;
    AppUser user;
    double prizeMoney;

    public MinigameScreen(BufferedReader consoleReader, ScreenRouter router, UserDAO userDAO){
        super("MinigameScreen", "/Minigame");
        this.consoleReader = consoleReader;
        this.router = router;
        this.userDAO = userDAO;
    }

    @Override
    public void render() {
       prizeMoney = minigame_PickAChest();
       //TODO: Prompt users (who have multiple accounts) and ask what account to place money into
        user = router.getUser();
        user.setGoldPieces(user.getGoldPieces()+prizeMoney);
        System.out.println("New gold total is: " + user.getGoldPieces());

        double updateAmount = user.getGoldPieces()+prizeMoney;

        userDAO.updateValueOfAccount("goldpieces",updateAmount, user.getId(), 1 );
    }

    public double minigame_PickAChest(){
        double chest1 = Math.random() * 100;
        double chest2 = Math.random() * 100;
        double chest3 = Math.random() * 100;
        int playerChoice = 0;

        System.out.println("If it's money you seek why not try your hand at the magical chest gamble?");
        System.out.println("All you need to do is pick from chest 1,2,or 3 and you will win some gold. Give it a try");
        System.out.print(">");
        try {
            playerChoice = Integer.parseInt(consoleReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        switch (playerChoice){
            case 1:
                System.out.println("You opened chest 1 and got " +  chest1 + " gold pieces");
                return chest1;


            case 2:
                System.out.println("You opened chest 2 and got " +  chest2 + " gold pieces");
                return chest2;

            case 3:
                System.out.println("You opened chest 3 and got " +  chest3 + " gold pieces");
                return chest3;

            default:
                return 0;
        }
    }

}
