package com.revature.p0.util;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * A singleton class that is incharge of holding a connection to
 * the database. Prevents the creation of multiple connection and
 * will instead be injected into needed classes
 */
public class ConnectionFactory {

    private static ConnectionFactory connectionFactory;
    private Properties props = new Properties();

    static{
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    /**
     * Constructs a new ConnectionFactory using credentials specified in a .properties files
     */
    private ConnectionFactory(){
        try{
            props.load(new FileReader("src/main/resources/application.properties"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Returns an instance of the connectionFactory. If one does not exist create the singleton
     * and return the instance
     * @return connectionFactory An instance of the singleton class ConnectionFactory
     */
    public static ConnectionFactory getInstance(){
        if(connectionFactory == null){
            connectionFactory = new ConnectionFactory();
        }

        return connectionFactory;
    }

    /**
     * Logs in and returns a created connection using the specified host, user, and pass in the
     * .properties file
     * @return conn A Connection to the data base that can be used to call sql commands
     */
    public Connection getConnection(){
        Connection conn = null;

        try{
            conn = DriverManager.getConnection(
                    props.getProperty("host-url"),
                    props.getProperty("username"),
                    props.getProperty("password")
            );
        }catch (SQLException e){
            e.printStackTrace();
        }

        return conn;
    }

}
