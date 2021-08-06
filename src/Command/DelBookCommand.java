/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Command;

import model.dao.BookDAO;
import org.json.JSONObject;
import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author cassiano
 */
public class DelBookCommand implements Command{
    
    private BookDAO bookDAO;

    public DelBookCommand(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }
    
    
    @Override
    public void execute(JSONObject json) {
        int id = json.getInt("bookID");
        try {
            
            this.bookDAO.deleteBy( id );
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
}
