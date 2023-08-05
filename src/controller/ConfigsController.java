package controller;

import java.io.IOException;

import model.dao.ConfigRepository;
import model.utils.DBConfig;
import view.ConfigView;

/**
 *
 */
public class ConfigsController
{

    private final ConfigView view;

    public ConfigsController()
    {
        this.view = new ConfigView(this);
        
        this.loadConfiguration();
    }

    public void saveConfiguration()
    {
        DBConfig config = this.view.getConfig();
        
        try
        {
            new ConfigRepository().write(config);
            
            // labelResponse.setText("Configurações salvas.");
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    private void loadConfiguration()
    {
        try
        {
            DBConfig config = new ConfigRepository().read();
            
            this.view.setConfig(config);
        }
        catch (IOException | ClassNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
    }
}