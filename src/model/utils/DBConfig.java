package model.utils;

import java.io.Serializable;

/**
 *
 */
final public class DBConfig implements Serializable
{

    private final int port;
    private final String host;
    private final String driver;
    private final String dbname;
    private final String user;
    private final String pass;

    public DBConfig(String host, int port, String driver, String dbname, String user, String pass)
    {
        this.host   = host;
        this.port   = port;
        this.driver = driver;
        this.dbname = dbname;
        this.user   = user;
        this.pass   = pass;
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
        return this.dbname;
    }

    public String getUser()
    {
        return this.user;
    }

    public String getPassword()
    {
        return this.pass;
    }

    public final String getDSN()
    {
        return "jdbc:"  + this.driver
                + "://" + this.host
                + ":"   + this.port
                + "/"   + this.dbname;
    }
}