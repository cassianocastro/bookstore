package model.factories;

import java.math.BigDecimal;
import model.entities.Book;
import org.json.JSONObject;

/**
 *
 *
 */
public class BookFactory
{

    public Book buildFrom(JSONObject json)
    {
        String id         = json.getString("ID");
        String publishing = json.getString("publishing");
        String author     = json.getString("author");

        String title      = json.getString("title");
        String gender     = json.getString("gender");
        String year       = json.getString("year");
        String finishing  = json.getString("finishing");
        String pages      = json.getString("pages");

        String code       = json.getString("code");
        String sell       = json.getString("sellValue");
        String buy        = json.getString("buyValue");

        return new Book(
            Integer.parseInt(id),
            null,
            null,
            Integer.parseInt(code),
            Integer.parseInt(year),
            Integer.parseInt(pages),
            title,
            gender,
            finishing,
            new BigDecimal(sell),
            new BigDecimal(buy)
        );
    }
}