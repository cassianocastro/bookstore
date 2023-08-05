package model;

import java.io.Serializable;

/**
 *
 *
 */
final public class DBConfig implements Serializable
{

    private final int port;
    private final String host;
    private final String driver;
    private final String database;
    private final String user;
    private final String password;

    public DBConfig(String host, int port, String driver,
            String database, String user, String password)
    {
        this.host     = host;
        this.port     = port;
        this.driver   = driver;
        this.database = database;
        this.user     = user;
        this.password = password;
    }

    public String getHost()
    {
        return this.host;
    }

    public int getPort()
    {
        return this.port;
    }

    public String getDriver()
    {
        return this.driver;
    }

    public String getDatabase()
    {
        return this.database;
    }

    public String getUser()
    {
        return this.user;
    }

    public String getPassword()
    {
        return this.password;
    }

    public String getDSN()
    {
        return "jdbc:"  + this.driver
                + "://" + this.host
                + ":"   + this.port
                + "/"   + this.database;
    }
}