package controller;

import java.io.IOException;
import model.dao.ConfigDAO;
import model.factories.ConfigFactory;
import view.ConfigView;

/**
 *
 *
 */
public class ConfigController
{

    private final ConfigView view;

    public ConfigController()
    {
        this.view = new ConfigView(this);
        this.loadConfiguration();
    }

    public void saveConfiguration()
    {
        String[] config = this.view.getConfig();
        try
        {
            new ConfigDAO().write(new ConfigFactory().createConfig(config));
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        // labelResponse.setText("Configurações salvas.");
    }

    private void loadConfiguration()
    {
        try
        {
            new ConfigDAO().read();
        } catch (IOException | ClassNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        this.view.setConfig(new String[]{"", "", "", "", "", ""});
    }
}