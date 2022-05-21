package controller;

import org.json.JSONObject;

/**
 *
 *
 */
public interface Command
{

    public void execute(JSONObject json);
}