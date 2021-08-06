/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controll;

import Command.*;
import java.sql.SQLException;
import java.util.List;
import model.Book;
import model.dao.*;
import org.json.JSONObject;
/**
 *
 * @author cassiano
 */
public class BookController {

    private Invoker invoker;
    
    public BookController() {
        invoker = new Invoker();
        foo();
    }
    
    public void foo(){
        try {
            BookDAO bookDAO = new BookDAO();
            invoker.put("NewCommand",  new NewBookCommand (bookDAO));
            invoker.put("EditCommand", new EditBookCommand(bookDAO));
            invoker.put("DelCommand",  new DelBookCommand (bookDAO));
            invoker.put("ReadCommand", new ReadBookCommand(bookDAO));
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void invoke(String cmd, JSONObject json){
        invoker.invoke(cmd, json);
    }
    
    public List<Book> getList(){
        return invoker.getList();
    }
}
