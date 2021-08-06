package model.dao;

import model.ConfigDataBase;
import java.sql.*;

public class ConnectionSingleton {

    private static Connection instance;
    private static ConfigDataBase configDataBase;

    private ConnectionSingleton() {}
    
    public static void setConfig(ConfigDataBase config){
        configDataBase = config;
    }

    public static Connection getInstance() throws SQLException{
        if (instance == null && configDataBase != null) {
            instance = DriverManager.getConnection(
                configDataBase.getURL(),
                configDataBase.getUser(),
                configDataBase.getPassword()
            );
        }
        return instance;
    }
}
