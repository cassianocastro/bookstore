package model.dao;

import model.DBConfig;
import java.sql.*;

/**
 *
 *
 */
public class ConnectionSingleton
{

    private static Connection instance;
    private static DBConfig configDataBase;

    private ConnectionSingleton() {}

    static public void setConfig(DBConfig config)
    {
        configDataBase = config;
    }

    static public Connection getInstance() throws SQLException
    {
        if ( instance == null && configDataBase != null )
        {
            instance = DriverManager.getConnection(
                configDataBase.getDSN(),
                configDataBase.getUser(),
                configDataBase.getPassword()
            );
        }
        return instance;
    }
}