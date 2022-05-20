/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.command.EditBookCommand;
import controller.command.NewBookCommand;
import controller.command.Invoker;
import controller.command.ReadBookCommand;
import controller.command.DelBookCommand;
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
        invoker = new Invoker();
        this.foo();
    }

    private void foo()
    {
        try
        {
            BookDAO bookDAO = new BookDAO();
            invoker.put("NewCommand", new NewBookCommand(bookDAO));
            invoker.put("EditCommand", new EditBookCommand(bookDAO));
            invoker.put("DelCommand", new DelBookCommand(bookDAO));
            invoker.put("ReadCommand", new ReadBookCommand(bookDAO));
        } catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    public void invoke(String cmd, JSONObject json)
    {
        invoker.invoke(cmd, json);
    }

    public List<Book> getList()
    {
        return invoker.getList();
    }
}