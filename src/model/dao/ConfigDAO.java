package model.dao;

import java.io.*;
import model.DBConfig;
import org.json.JSONObject;

/**
 *
 *
 */
public class ConfigDAO
{

    private final String pathName;

    public ConfigDAO()
    {
        this.pathName = "src/lib/config.txt";
    }

    public void write(DBConfig config) throws IOException
    {
        try (ObjectOutputStream output
            = new ObjectOutputStream(new FileOutputStream(this.pathName)))
        {
            output.writeObject(config);
        }
    }

    public JSONObject read() throws IOException, ClassNotFoundException
    {
        try (ObjectInputStream input
            = new ObjectInputStream(new FileInputStream(this.pathName)))
        {
            DBConfig config = (DBConfig) input.readObject();
            JSONObject json = new JSONObject();

            json.put("host", config.getHost());
            json.put("port", config.getPort());
            json.put("driver", config.getDriver());
            json.put("database", config.getDatabase());
            json.put("user", config.getUser());
            json.put("password", config.getPassword());

            return json;
        }
    }
}