/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import model.Book;
import model.dao.*;
import org.json.JSONObject;

/**
 *
 *
 */
public class BookController
{

    private final Invoker invoker;

    public BookController()
    {
        this.invoker = new Invoker();
        this.foo();
    }

    private void foo()
    {
        try
        {
            Connection connection = ConnectionSingleton.getInstance();
            BookDAO bookDAO = new BookDAO(connection);

            invoker.put("NewCommand", new NewBookCommand(bookDAO));
            invoker.put("EditCommand", new EditBookCommand(bookDAO));
            invoker.put("DelCommand", new DelBookCommand(bookDAO));
            invoker.put("ReadCommand", new ReadBookCommand(bookDAO));
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void invoke(String cmd, JSONObject json)
    {
        this.invoker.invoke(cmd, json);
    }

    public List<Book> getList()
    {
        return this.invoker.getList();
    }
}