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