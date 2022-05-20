/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.command;

import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import model.Book;
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
            this.list = this.bookDAO.read();
        } catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}