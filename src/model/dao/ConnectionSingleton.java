package model.dao;

import model.ConfigDataBase;
import java.sql.*;

/**
 *
 *
 */
public class ConnectionSingleton
{

    private static Connection instance;
    private static ConfigDataBase configDataBase;

    private ConnectionSingleton() {}

    static public void setConfig(ConfigDataBase config)
    {
        configDataBase = config;
    }

    static public Connection getInstance() throws SQLException
    {
        if ( instance == null && configDataBase != null )
        {
            instance = DriverManager.getConnection(
                configDataBase.getURL(),
                configDataBase.getUser(),
                configDataBase.getPassword()
            );
        }
        return instance;
    }
}