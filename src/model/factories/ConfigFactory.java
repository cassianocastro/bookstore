package model.factories;

import model.DBConfig;
import org.json.JSONObject;

/**
 *
 *
 */
public class ConfigFactory
{

    public DBConfig buildFrom(JSONObject json)
    {
        int port        = json.getInt("port");
        String host     = json.getString("host");
        String dbName   = json.getString("dbName");
        String dataBase = json.getString("database");
        String user     = json.getString("user");
        String password = json.getString("pass");

        return new DBConfig(host, port, dbName, dataBase, user, password);
    }
}