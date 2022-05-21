package controller;

import model.entities.Book;
import model.dao.BookDAO;
import model.factories.BookFactory;
import org.json.JSONObject;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 *
 */
public class NewBookCommand implements Command
{

    private final BookDAO bookDAO;

    public NewBookCommand(BookDAO bookDAO)
    {
        this.bookDAO = bookDAO;
    }

    @Override
    public void execute(JSONObject json)
    {
        Book book = new BookFactory().buildFrom(json);

        try
        {
            this.bookDAO.insert(book);
        } catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
