package model.factories;

import org.json.JSONObject;

/**
 *
 *
 */
public class JSONFactory
{

    private final JSONObject json;

    public JSONFactory()
    {
        this.json = new JSONObject();
    }

    public void buildFrom(String[] args)
    {
        this.json.put("bookID",      args[0]);
        this.json.put("publishingID",args[1]);
        this.json.put("authorID",    args[2]);

        this.json.put("title",       args[3]);
        this.json.put("gender",      args[4]);

        this.json.put("finishing",   args[5]);
        this.json.put("numberPages", args[6]);
        this.json.put("releaseYear", args[7]);

        this.json.put("code",        args[8]);
        this.json.put("sellValue",   args[9]);
        this.json.put("buyValue",    args[10]);
    }

    public JSONObject getJSON()
    {
        return this.json;
    }
}