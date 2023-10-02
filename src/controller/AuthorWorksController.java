package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import model.dao.AuthorWorksRepository;
import model.factories.ConnectionSingleton;
import view.AuthorWorksView;

/**
 *
 */
public class AuthorWorksController
{

    private final AuthorWorksView view;
    private String id;

    public AuthorWorksController()
    {
        this.view = new AuthorWorksView(this);
    }
    
    public String getID()
    {
        return this.id;
    }

    public void setID(String id)
    {
        this.id = id;
    }
    
    public List showBooksFromAuthor()
    {   
        try (Connection connection = ConnectionSingleton.getInstance())
        {
            return new AuthorWorksRepository(connection).findBooksByAuthor(0);
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

        return Collections.emptyList();
    }
}