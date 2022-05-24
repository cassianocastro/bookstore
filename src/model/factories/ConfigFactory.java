package model.factories;

import model.DBConfig;

/**
 *
 *
 */
public class ConfigFactory
{

    public DBConfig createConfig(String[] args)
    {
        int port        = Integer.parseInt(args[0]);
        String host     = args[1];
        String driver   = args[2];
        String database = args[3];
        String user     = args[4];
        String password = args[5];

        return new DBConfig(host, port, driver, database, user, password);
    }
}