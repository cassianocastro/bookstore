package view.listeners;

import controller.ConfigsController;
import java.awt.event.ActionEvent;
import model.factories.ConfigFactory;
import model.utils.DBConfig;
import view.ConfigView;

/**
 *
 */
public class ConfigListener
{

    private final ConfigsController controller;
    private final ConfigView ui;
    
    public ConfigListener(ConfigView ui, ConfigsController controller)
    {
        this.controller = controller;
        this.ui = ui;

        this.init();
    }

    private void init()
    {
        {
            var button = this.ui.getSubmitButton();

            button.addActionListener((ActionEvent e) -> this.controller.saveConfiguration());
        }

        {
            var button = this.ui.getCancelButton();

            button.addActionListener((ActionEvent e) -> this.ui.dispose());
        }
    }

    public DBConfig getConfig()
    {
        String port   = this.ui.getPortField().getText();
        String host   = this.ui.getHostField().getText();
        String driver = this.ui.getDriverField().getText();
        String dbname = this.ui.getDatabaseField().getText();
        String user   = this.ui.getUserField().getText();
        String pass   = String.valueOf(this.ui.getPasswordField().getPassword());
        
        return new ConfigFactory().create(host, port, driver, dbname, user, pass);
    }

    public void setConfig(final DBConfig config)
    {
        this.ui.getHostField().setText(config.getHost());
        this.ui.getPortField().setText(String.valueOf(config.getPort()));
        this.ui.getDatabaseField().setText(config.getDatabase());
        this.ui.getUserField().setText(config.getUser());
        this.ui.getPasswordField().setText(config.getPassword());
        this.ui.getDriverField().setText(config.getDriver());
    }
}