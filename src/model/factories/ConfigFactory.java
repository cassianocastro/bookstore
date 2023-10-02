package model.factories;

import model.utils.DBConfig;

/**
 *
 */
public class ConfigFactory
{

    public DBConfig create(String host, String port, String driver, String dbname, String user, String pass)
    {
        int nport = parsePort(port);
        
        return new DBConfig(host, nport, driver, dbname, user, pass);
    }
    
    private int parsePort(String port) throws NumberFormatException
    {
        try
        {
            return Integer.parseInt(port);
        }
        catch (NumberFormatException e)
        {
            throw e;
        }
    }
}