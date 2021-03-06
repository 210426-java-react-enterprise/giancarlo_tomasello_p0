package com.revature.p0.daos;


import com.revature.p0.exceptions.UserNotFoundException;
import com.revature.p0.models.AppUser;
import com.revature.p0.models.Item;
import com.revature.p0.services.UserService;
import com.revature.p0.util.ArrayList;
import com.revature.p0.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class UserDAO {

    private AppUser currentUser;

    public AppUser getCurrentUser() {
        return currentUser;
    }


    public void setCurrentUser(AppUser currentUser) {
        this.currentUser = currentUser;
    }

    public void save(Connection conn, AppUser newUser){

        try{
            //Insert into users table
            String sqlInsert = "insert into p0.users (username, password, email, first_name, last_name) " +
                    "values (?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sqlInsert, new String[]{"user_id"});
            pstmt.setString(1, newUser.getUsername());
            pstmt.setString(2, newUser.getPassword());
            pstmt.setString(3, newUser.getEmail());
            pstmt.setString(4, newUser.getFirstName());
            pstmt.setString(5, newUser.getLastName());

            int rowsInserted = pstmt.executeUpdate();

            if(rowsInserted != 0){
                ResultSet rs = pstmt.getGeneratedKeys();
                while(rs.next()){
                    newUser.setId(rs.getInt("user_id"));
                }
            }

            //create an associated account in the account table
            sqlInsert = "insert into p0.accounts (user_id, goldpieces, dragonshards) values (?,?,?)";
            pstmt = conn.prepareStatement(sqlInsert, new String[]{"account_id"});
            pstmt.setInt(1, newUser.getId());
            pstmt.setDouble(2, newUser.getGoldPieces());
            pstmt.setInt(3, newUser.getDragonShards());

            rowsInserted = pstmt.executeUpdate();

            int account_id = 0;

            if(rowsInserted != 0){
                ResultSet rs = pstmt.getGeneratedKeys();
                while(rs.next()){
                   account_id = rs.getInt("account_id");
                }
            }

            //Create a "backpack" for the account and insert an starting item
            sqlInsert = "insert into p0.backpack (account_id, item_id) values (?, 1)";
            pstmt = conn.prepareStatement(sqlInsert, new String[]{"item_id"});
            pstmt.setInt(1, account_id);
            pstmt.executeUpdate();
            pstmt.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println("Saved to database");
    }

    public AppUser findUserByUsernameAndPassword(Connection conn, String username, String password)throws UserNotFoundException{

        AppUser user = null;
        int account_id = 0;
        ArrayList<Integer> item_ids= new ArrayList<>();
        ArrayList<Item> userItems = new ArrayList<>();

        try{
            String sql = "select * from p0.users where username = ? and password = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()){
                user = new AppUser();
                user.setId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));

            }

            if(user!=null){
                sql = "select * from p0.accounts where user_id = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, user.getId());

                rs = pstmt.executeQuery();


                while (rs.next()){
                    user.setGoldPieces(rs.getDouble("goldpieces"));
                    user.setDragonShards(rs.getInt("dragonshards"));
                    account_id = rs.getInt("account_id");
                }

            } else {
                throw new UserNotFoundException("Could not find User in Database");
            }

            if(account_id > 0){
                sql = "select * from p0.backpack where account_id = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, account_id);

                rs = pstmt.executeQuery();

                while (rs.next()){
                    int item_id = rs.getInt("item_id");
                    item_ids.add(item_id);
                }
            }

            for (int i = 0; i < item_ids.size(); i++) {
                sql = "select * from p0.itemlist where item_id = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, item_ids.get(i));

                rs = pstmt.executeQuery();

                while (rs.next()){
                    Item temp = new Item();
                    temp.setId(rs.getInt("item_id"));
                    temp.setName(rs.getString("name"));
                    temp.setDescription(rs.getString("description"));
                    temp.setRarity(rs.getString("rarity"));
                    temp.setValue(rs.getDouble("value"));

                   userItems.add(temp);
                }
                
            }

            user.setBackpack(userItems);

        }catch (SQLException e){
            e.printStackTrace();
        }



        return user;
    }



    public void printValueOfAccount(AppUser user){
        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "select goldpieces, dragonshards from p0.accounts where user_id = ?";
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


    public void updateValueOfAccount(String currencyType, double newValue, int userID, int accountID){
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "update p0.accounts set goldpieces = ? where user_id = ?"; //later add account_id ??
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, currencyType);
            pstmt.setDouble(1, newValue);
            pstmt.setInt(2, userID);

            pstmt.executeUpdate();
            pstmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void addItemToDatabase(int user_id, Item itemToAdd){
        int account_id = -1;
        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

           String sql = "select * from p0.accounts where user_id = ?";
           PreparedStatement pstmt = conn.prepareStatement(sql);
           pstmt.setInt(1, user_id);

           ResultSet rs = pstmt.executeQuery();


           if(rs.next()){
               account_id = rs.getInt("account_id");
           }

           sql = "insert into p0.backpack (account_id, item_id) values (?, ?) ";
           pstmt = conn.prepareStatement(sql);
           pstmt.setInt(1, account_id);
           pstmt.setInt(2, itemToAdd.getId());

           pstmt.executeUpdate();
           pstmt.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public ArrayList<Item> randomizeShop(){
        ArrayList<Item> shopItems = new ArrayList<>();
        Random rand = new Random();

        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            for (int i = 0; i < 3; i++) {
                String sql = "select * from p0.itemlist where item_id = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                int itemNum = rand.nextInt(7) + 2; //No 0 and no duplicate of adventure's license
                pstmt.setInt(1, itemNum);

                ResultSet rs = pstmt.executeQuery();

                rs = pstmt.executeQuery();

                while (rs.next()){
                    Item temp = new Item();
                    temp.setId(rs.getInt("item_id"));
                    temp.setName(rs.getString("name"));
                    temp.setDescription(rs.getString("description"));
                    temp.setRarity(rs.getString("rarity"));
                    temp.setValue(rs.getDouble("value"));

                    shopItems.add(temp);

                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return shopItems;
    }

    public boolean isUsernameAvailable(Connection conn, String username){

        try {
            String sql = "select * from p0.users where username = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                return false;
            } else{
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean isEmailAvailable(Connection conn, String email){
        try {
            String sql = "select * from p0.users where email = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                return false;
            } else{
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
