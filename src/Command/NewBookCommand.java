/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Command;

import model.Book;
import model.dao.BookDAO;
import factories.BookFactory;
import org.json.JSONObject;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author cassiano
 */
public class NewBookCommand implements Command{
    
    private BookDAO bookDAO;
    
    public NewBookCommand(BookDAO bookDAO){
        this.bookDAO = bookDAO;
    }
    
    @Override
    public void execute(JSONObject json) {
        Book book = new BookFactory().buildFrom(json);
        try {

            this.bookDAO.create(book);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
}
