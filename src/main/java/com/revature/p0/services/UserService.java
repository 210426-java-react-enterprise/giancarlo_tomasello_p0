package com.revature.p0.services;

import com.revature.p0.daos.UserDAO;
import com.revature.p0.exceptions.UserNotFoundException;
import com.revature.p0.models.AppUser;
import com.revature.p0.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class UserService {

    private UserDAO userDAO;

    public UserService(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    public void register(AppUser newUser){

    }

    public void findUserInDatabase(String username, String password){
        try (Connection conn = ConnectionFactory.getInstance().getConnection()){
            userDAO.setCurrentUser(userDAO.findUserByUsernameAndPassword(conn, username, password));
        } catch (SQLException e){
            e.printStackTrace();
        } catch (UserNotFoundException e){
            System.out.println("It would appear you are not in our records. Either you are a new adventure or you gave" +
                    " an incorrect username");
        }
    }

    //Set up connections? for userDao
    //Register and Login


    //boolean validateUsername (username, password)


    //validateRegister (AppUser)
}
