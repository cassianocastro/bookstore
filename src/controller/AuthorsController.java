package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import model.dao.AuthorRepository;
import model.entities.Author;
import model.factories.ConnectionSingleton;
import view.AuthorView;

/**
 *
 */
public class AuthorsController
{

    private final AuthorView view;

    public AuthorsController()
    {
        this.view = new AuthorView(this);
    }

    public void addAuthor()
    {
        try (Connection connection = ConnectionSingleton.getInstance())
        {
            // new AuthorRepository(connection).insert(author);
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    public List<Author> showAuthors()
    {   
        try (Connection connection = ConnectionSingleton.getInstance())
        {
            return new AuthorRepository(connection).getAll();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

        return Collections.emptyList();
    }

    public void updateAuthor()
    {
        try (Connection connection = ConnectionSingleton.getInstance())
        {
            // new AuthorRepository(connection).update(author);
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void deleteAuthor()
    {
        try (Connection connection = ConnectionSingleton.getInstance())
        {
            // new AuthorRepository(connection).delete(author);
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    public void showAuthorsWorks()
    {
        String id = this.view.getIDField().getText();

        new AuthorWorksController().setID(id);
    }
}