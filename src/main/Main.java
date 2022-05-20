package main;

import java.sql.Connection;
import java.sql.SQLException;
import model.dao.ConnectionSingleton;
import view.HomeView;

/**
 *
 *
 */
public class Main
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        java.awt.EventQueue.invokeLater(() ->
        {
            try
            {
                new HomeView();
                Connection connection = ConnectionSingleton.getInstance();
                connection.close();
            } catch (SQLException e)
            {
                System.out.println(e.getMessage());
            }
        });
    }
}