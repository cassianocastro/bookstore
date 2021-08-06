package model.dao;

import java.io.*;
import model.ConfigDataBase;
import org.json.*;

/**
 * @author cassiano
 */
public class ConfigDAO {
    
    private final String pathName;
    
    public ConfigDAO(){
        this.pathName = "src/resources/config.txt";
    }

    public void write(ConfigDataBase config) throws IOException{
        try(ObjectOutputStream output = 
                new ObjectOutputStream(
                        new FileOutputStream(this.pathName))) {
            
            output.writeObject(config);
        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    public JSONObject read() throws IOException{
        try(ObjectInputStream input = 
                new ObjectInputStream(
                        new FileInputStream(this.pathName))) {
            
            ConfigDataBase config = (ConfigDataBase) input.readObject();
            JSONObject json = new JSONObject();
            json.put("host",    config.getHost());
            json.put("port",    config.getPort());
            json.put("dbName",  config.getDBname());
            json.put("database",config.getDataBase());
            json.put("user",    config.getUser());
            json.put("pass",    config.getPassword());
            return json;
        } catch (IOException | ClassNotFoundException e) {
            throw new IOException(e);
        }
    }

}