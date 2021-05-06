package com.revature.p0.daos;

import com.revature.p0.models.AppUser;
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
            String sqlInsert = "insert into p0.users (username, password, email, first_name, " +
                    "last_name, goldpieces, dragonshards) values (?,?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sqlInsert);
            pstmt.setString(1, newUser.getUsername());
            pstmt.setString(2, newUser.getPassword());
            pstmt.setString(3, newUser.getEmail());
            pstmt.setString(4, newUser.getFirstName());
            pstmt.setString(5, newUser.getLastName());
            pstmt.setDouble(6, newUser.getGoldPieces());
            pstmt.setInt(7, newUser.getDragonShards());


        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public AppUser findUserByUsernameAndPassword(String username, String password){

        AppUser user = null;

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
                user.setGoldPieces(rs.getDouble("goldpieces"));
                user.setDragonShards(rs.getInt("dragonshards"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return user;
    }
}
