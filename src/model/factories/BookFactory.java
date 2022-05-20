package model.factories;

import java.math.BigDecimal;
import model.Book;
import org.json.JSONObject;

/**
 *
 *
 */
public class BookFactory
{

    public Book buildFrom(JSONObject json)
    {
        String bookID       = json.getString("bookID");
        String publishingID = json.getString("publishingID");
        String authorID     = json.getString("authorID");

        String title        = json.getString("title");
        String gender       = json.getString("gender");
        String releaseYear  = json.getString("releaseYear");
        String finishing    = json.getString("finishing");
        String pages        = json.getString("numberPages");

        String code         = json.getString("code");
        String sell         = json.getString("sellValue");
        String buy          = json.getString("buyValue");

        return new Book(
            Integer.parseInt(bookID),
            Integer.parseInt(publishingID),
            Integer.parseInt(authorID),
            Integer.parseInt(code),
            Integer.parseInt(releaseYear),
            Integer.parseInt(pages),
            title,
            gender,
            finishing,
            new BigDecimal(sell),
            new BigDecimal(buy)
        );
    }
}