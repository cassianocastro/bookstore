package controller;

import model.dao.BookDAO;
import org.json.JSONObject;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 *
 */
public class DelBookCommand implements Command
{

    private final BookDAO bookDAO;

    public DelBookCommand(BookDAO bookDAO)
    {
        this.bookDAO = bookDAO;
    }

    @Override
    public void execute(JSONObject json)
    {
        int id = json.getInt("bookID");

        try
        {
            this.bookDAO.delete(id);
        } catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}