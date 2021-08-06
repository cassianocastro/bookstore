package model;

import java.io.Serializable;

public final class ConfigDataBase implements Serializable {

    private final String host, port, dbName, dataBase, userName, password;

    public ConfigDataBase(String host, String port, String dbName,
            String dataBase, String userName, String password) {
        this.host     = host;
        this.port     = port;
        this.dbName   = dbName;
        this.dataBase = dataBase;
        this.userName = userName;
        this.password = password;
    }

    public String getHost() {
        return this.host;
    }

    public String getPort() {
        return this.port;
    }
    
    public String getDBname(){
        return this.dbName;
    }

    public String getDataBase() {
        return this.dataBase;
    }

    public String getUser() {
        return this.userName;
    }

    public String getPassword() {
        return this.password;
    }
    
    public String getURL(){
        return "jdbc:"  + this.dbName
                + "://" + this.host 
                + ":"   + this.port 
                + "/"   + this.dataBase;
    }

}
