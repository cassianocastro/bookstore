package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import model.dao.BookRepository;
import model.entities.Book;
import model.factories.ConnectionSingleton;
import view.BookView;

/**
 *
 */
public class BooksController
{

    private final BookView view;

    public BooksController()
    {
        this.view = new BookView(this);
    }

    public void addBook()
    {
        try (Connection connection = ConnectionSingleton.getInstance())
        {
            // new BookRepository(connection).insert(book);
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    public List<Book> showBooks()
    {
        try (Connection connection = ConnectionSingleton.getInstance())
        {
            return new BookRepository(connection).getAll();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        
        return Collections.emptyList();
    }

    public void updateBook()
    {
        try (Connection connection = ConnectionSingleton.getInstance())
        {
            // new BookRepository(connection).update(book);
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void deleteBook()
    {
        try (Connection connection = ConnectionSingleton.getInstance())
        {
            // new BookRepository(connection).delete(book);
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
}