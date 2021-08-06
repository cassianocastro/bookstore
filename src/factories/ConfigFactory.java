package factories;

import model.ConfigDataBase;
import org.json.JSONObject;

/**
 * @author cassiano
 */
public class ConfigFactory {
    public ConfigDataBase buildFrom(JSONObject json){
        String host     = json.getString("host");
        String port     = json.getString("port");
        String dbName   = json.getString("dbName");
        String dataBase = json.getString("database");
        String user     = json.getString("user");
        String password = json.getString("pass");

        return new ConfigDataBase(host, port, dbName, dataBase, user, password);
    }
}
