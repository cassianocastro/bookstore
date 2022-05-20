/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.*;
import org.json.JSONObject;

/**
 *
 *
 */
public class Invoker
{

    private final Map<String, Command> commands;

    public Invoker()
    {
        this.commands = new HashMap<>();
    }

    public void put(String cmd, Command command)
    {
        this.commands.put(cmd, command);
    }

    public void invoke(String cmd, JSONObject json)
    {
        this.commands.get(cmd).execute(json);
    }

    public List getList()
    {
        ReadBookCommand c = (ReadBookCommand) this.commands.get("ReadCommand");
        c.execute(null);
        return c.getList();
    }
}