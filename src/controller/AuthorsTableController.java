package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import model.dao.AuthorRepository;
import model.entities.Author;
import model.factories.ConnectionSingleton;
import view.AuthorsTableView;
import view.BookView;

/**
 *
 */
public class AuthorsTableController
{

    private final AuthorsTableView view;

    public AuthorsTableController(BookView parent)
    {
        this.view = new AuthorsTableView(this, parent);
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
}