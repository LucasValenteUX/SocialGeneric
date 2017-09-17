package com.br.lp3.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 1147106
 */
public class SingletonConnection {
    
    private static SingletonConnection instance;
    private Connection connection;
    public static final String DATABASE = "bancolp2";
    public static final String USERNAME = "ricardo";
    public static final String PWD = "123";
    public static final String HOST = "localhost:1527";
    public static final String PROTOCOL = "jdbc:derby:";
    public static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    
    
    private SingletonConnection(){
        try {
            Class.forName(DRIVER).newInstance();
            connection = DriverManager.getConnection(PROTOCOL+"//"+HOST+"/"+DATABASE, USERNAME, PWD);  
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(SingletonConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static SingletonConnection getInstance(){
        if(instance==null) instance = new SingletonConnection();
        return instance;
    }
    
    public Connection getConnection(){
        return connection;
    }
    
    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(SingletonConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
