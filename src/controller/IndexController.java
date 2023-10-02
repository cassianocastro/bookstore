package controller;

import java.sql.Connection;
import java.sql.SQLException;

import model.factories.ConnectionSingleton;
import view.IndexView;

/**
 *
 */
public class IndexController
{

    private final IndexView view;
    
    public IndexController()
    {
        this.view = new IndexView(this);
    }

    public void showBooksView()
    {
        new BooksController();
    }

    public void showPublishingsView()
    {
        new PublishingsController();
    }

    public void showAuthorsView()
    {
        new AuthorsController();
    }

    public void showConfigView()
    {
        new ConfigsController();
    }

    public void showEmployeesView()
    {
        new EmployeesController();
    }

    public void showClientsView()
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
//        }
//        catch (IOException | ClassNotFoundException | SQLException e)
//        {
//            System.out.println(e.getMessage());
//        }
//        this.view.setButtonsEnabled(false);
        System.exit(0);
    }

    public void closeConnection()
    {   
        try (Connection connection = ConnectionSingleton.getInstance())
        {
            if ( connection != null )
            {
                connection.close();
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        
        System.exit(0);
    }
}