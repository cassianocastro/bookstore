package controller;

import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import model.entities.Book;
import model.dao.BookDAO;
import org.json.JSONObject;

/**
 *
 *
 */
public class ReadBookCommand implements Command
{

    private final BookDAO bookDAO;
    private List<Book> list;

    public ReadBookCommand(BookDAO bookDAO)
    {
        this.bookDAO = bookDAO;
    }

    public List<Book> getList()
    {
        return this.list;
    }

    @Override
    public void execute(JSONObject json)
    {
        try
        {
            this.list = this.bookDAO.getAll();
        } catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}