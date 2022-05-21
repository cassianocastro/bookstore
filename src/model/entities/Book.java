package model.entities;

import java.math.BigDecimal;
import model.Product;

/**
 *
 *
 */
public class Book extends Product
{

    private final PublishingCia publishing;
    private final Author author;
    private final int year;
    private final int pages;
    private final String title;
    private final String gender;
    private final String finishing;

    public Book(int id, PublishingCia publishing, Author author, int code, int year, int pages,
            String title, String gender, String finishing, BigDecimal sellValue, BigDecimal buyValue)
    {
        super(id, code, sellValue, buyValue);
        this.publishing = publishing;
        this.author     = author;
        this.year       = year;
        this.pages      = pages;
        this.title      = title;
        this.gender     = gender;
        this.finishing  = finishing;
    }

    public PublishingCia getPublishing()
    {
        return this.publishing;
    }

    public Author getAuthor()
    {
        return this.author;
    }

    public int getReleaseYear()
    {
        return this.year;
    }

    public int getNumberPages()
    {
        return this.pages;
    }

    public String getTitle()
    {
        return this.title;
    }

    public String getGender()
    {
        return this.gender;
    }

    public String getFinishing()
    {
        return this.finishing;
    }
}