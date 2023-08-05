package model.dao;

import java.io.*;
import model.utils.DBConfig;

/**
 *
 */
public class ConfigRepository
{

    private final String path;

    public ConfigRepository()
    {
        this.path = "lib/config.txt";
    }

    public void write(DBConfig config) throws IOException
    {
        try (ObjectOutputStream output
            = new ObjectOutputStream(
                new FileOutputStream(this.path)))
        {
            output.writeObject(config);
        }
    }

    public DBConfig read() throws IOException, ClassNotFoundException
    {
        try (ObjectInputStream input
            = new ObjectInputStream(
                new FileInputStream(this.path)))
        {
            return (DBConfig) input.readObject();
        }
    }
}