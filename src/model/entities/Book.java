package model.entities;

import java.math.BigDecimal;

import model.utils.Gender;
import model.utils.Product;

/**
 *
 */
public class Book extends Product
{

    private final PublishingCia publishing;
    private final Author author;
    private final int release;
    private final int pages;
    private final String title;
    private final Gender gender;
    private final String finishing;

    public Book(int id, PublishingCia publishing, Author author, int code, int release, int pages,
            String title, Gender gender, String finishing, BigDecimal sellValue, BigDecimal buyValue)
    {
        super(id, code, sellValue, buyValue);
        this.publishing = publishing;
        this.author     = author;
        this.release    = release;
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
        return this.release;
    }

    public int getNumberPages()
    {
        return this.pages;
    }

    public String getTitle()
    {
        return this.title;
    }

    public Gender getGender()
    {
        return this.gender;
    }

    public String getFinishing()
    {
        return this.finishing;
    }
}