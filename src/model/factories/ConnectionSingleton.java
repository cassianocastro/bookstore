package model.factories;

import model.utils.DBConfig;
import java.sql.*;

/**
 *
 */
public class ConnectionSingleton
{

    private static Connection connection;
    private static DBConfig config;

    private ConnectionSingleton() {}

    static public void setConfig(DBConfig dbc)
    {
        config = dbc;
    }

    static public Connection getInstance() throws SQLException
    {
        if ( connection == null && config != null )
        {
            connection = DriverManager.getConnection(
                config.getDSN(),
                config.getUser(),
                config.getPassword()
            );
        }
        
        return connection;
    }
}