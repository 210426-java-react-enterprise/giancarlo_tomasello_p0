package com.revature.p0.daos;

import com.revature.p0.models.AppUser;
import com.revature.p0.models.Item;
import com.revature.p0.util.ArrayList;
import com.revature.p0.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public UserDAO(){

    }

    public void save(AppUser newUser){

        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
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

    public AppUser findUserByUsernameAndPassword(String username, String password){

        AppUser user = null;
        int account_id = 0;
        ArrayList<Integer> item_ids= new ArrayList<>();
        ArrayList<Item> userItems = new ArrayList<>();

        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
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
}
