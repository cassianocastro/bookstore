package controller;

import java.io.IOException;
import java.sql.SQLException;
import model.DBConfig;
import model.dao.ConfigDAO;
import model.factories.ConnectionSingleton;
import model.factories.ConfigFactory;
import org.json.JSONObject;
import view.IndexView;

/**
 *
 *
 */
public class IndexController
{

    private final IndexView view;

    public IndexController()
    {
        this.view = new IndexView(this);
    }

    public void createBooksView()
    {
        new BookController();
    }

    public void createPublishingsView()
    {
        new PublishingController();
    }

    public void createAuthorsView()
    {
        new AuthorsController();
    }

    public void createConfigView()
    {
        new ConfigController();
    }

    public void createEmployeesView()
    {

    }

    public void createClientsView()
    {

    }

    public void connect()
    {
        try
        {
            JSONObject config = new ConfigDAO().read();
            DBConfig dbc = new ConfigFactory().createConfig(new String[]{});

            ConnectionSingleton.setConfig(dbc);
            ConnectionSingleton.getInstance();

            this.view.setButtonsEnabled(true);
            // this.view.labelResponse.setText("Conexão estabelecida.");
        } catch (IOException | ClassNotFoundException | SQLException e)
        {
            System.out.println(e.getMessage());
        }
        this.view.setButtonsEnabled(false);
    }

    public void closeConnection()
    {
        try
        {
            if ( ConnectionSingleton.getInstance() != null )
            {
                ConnectionSingleton.getInstance().close();
            }
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        System.exit(0);
    }
}