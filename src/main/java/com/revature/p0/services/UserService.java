package com.revature.p0.services;

import com.revature.p0.daos.UserDAO;
import com.revature.p0.exceptions.EmailUnavailableException;
import com.revature.p0.exceptions.ResourcePersistenceException;
import com.revature.p0.exceptions.UserNotFoundException;
import com.revature.p0.exceptions.UsernameUnavailableException;
import com.revature.p0.models.AppUser;
import com.revature.p0.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class UserService {

    private UserDAO userDAO;

    public UserService(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    /**
     * Take a newUser to check if it can be added to the database using helper methods. If true it calls
     * the userDao to add the newUser to teh database.
     * @param newUser The new user that wants to be added to the database
     * @throws ResourcePersistenceException Thrown when the newUser would create a duplciate username of email
     */
    public void register(AppUser newUser) throws ResourcePersistenceException {
        System.out.println("register the new user");

        try (Connection conn = ConnectionFactory.getInstance().getConnection()){
            if(!userDAO.isUsernameAvailable(conn, newUser.getUsername())){
                System.out.println("Username is already taken!!!");
                throw new UsernameUnavailableException("Username is already taken");
            }

            if (!userDAO.isEmailAvailable(conn, newUser.getEmail())){
                System.out.println("Email is already taken!!!");
                throw new EmailUnavailableException("Email already exitst in the database");
            }

            userDAO.save(conn, newUser);

        } catch (SQLException e){
            e.printStackTrace();
        }
        //Check if username is avaliable
        //check if email is avaliable
        //save
    }

    /**
     * Uses the userDao to check if the user exists in the database. If it does exist
     * set the current user for the program
     * @param username The username of the user you are looking for
     * @param password The password for the user you are looking for
     */
    public void findUserInDatabase(String username, String password){
        try (Connection conn = ConnectionFactory.getInstance().getConnection()){
            AppUser foundUser = userDAO.findUserByUsernameAndPassword(conn, username, password);
            userDAO.setCurrentUser(foundUser);
        } catch (SQLException e){
            e.printStackTrace();
        } catch (UserNotFoundException e){
            System.out.println("It would appear you are not in our records. Either you are a new adventure or you gave" +
                    " an incorrect username");
        }
    }

}
