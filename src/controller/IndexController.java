package controller;

import java.sql.SQLException;
import model.factories.ConnectionSingleton;
import view.IndexView;

/**
 *
 */
public class IndexController
{

    public void createIndexView()
    {
        new IndexView(this);
    }

    public void createBooksView()
    {
        new BooksController();
    }

    public void createPublishingsView()
    {
        new PublishingsController();
    }

    public void createAuthorsView()
    {
        new AuthorsController();
    }

    public void createConfigView()
    {
        new ConfigsController();
    }

    public void createEmployeesView()
    {
        new EmployeesController();
    }

    public void createClientsView()
    {
        new ClientsController();
    }

    public void exit()
    {
//        try
//        {
//            JSONObject config = new ConfigDAO().read();
//            DBConfig dbc = new ConfigFactory().createConfig(new String[]{});
//
//            ConnectionSingleton.setConfig(dbc);
//            ConnectionSingleton.getInstance();
//
//            this.view.setButtonsEnabled(true);
//            // this.view.labelResponse.setText("Conexão estabelecida.");
//        } catch (IOException | ClassNotFoundException | SQLException e)
//        {
//            System.out.println(e.getMessage());
//        }
//        this.view.setButtonsEnabled(false);
        System.exit(0);
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