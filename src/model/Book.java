package model;

import java.math.BigDecimal;

/**
 *
 *
 */
public class Book extends Product
{

    private int bookID;
    private int publishingID;
    private int authorID;
    private int codeBar;
    private int releaseYear;
    private int numberPages;
    private String title;
    private String gender;
    private String finishing;

    public Book(int bookID, int publishingID, int authorID, int codeBar, int releaseYear, int numberPages,
            String title, String gender, String finishing, BigDecimal sellValue, BigDecimal buyValue)
    {
        super(sellValue, buyValue);
        this.bookID       = bookID;
        this.publishingID = publishingID;
        this.authorID     = authorID;
        this.codeBar      = codeBar;
        this.releaseYear  = releaseYear;
        this.numberPages  = numberPages;
        this.title        = title;
        this.gender       = gender;
        this.finishing    = finishing;
    }

    public int getBookID()
    {
        return bookID;
    }

    public void setBookID(int bookID)
    {
        this.bookID = bookID;
    }

    public int getPublishingID()
    {
        return publishingID;
    }

    public void setPublishingID(int publishingID)
    {
        this.publishingID = publishingID;
    }

    public int getAuthorID()
    {
        return authorID;
    }

    public void setAuthorID(int authorID)
    {
        this.authorID = authorID;
    }

    public int getCodeBar()
    {
        return codeBar;
    }

    public void setCodeBar(int codeBar)
    {
        this.codeBar = codeBar;
    }

    public int getReleaseYear()
    {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear)
    {
        this.releaseYear = releaseYear;
    }

    public int getNumberPages()
    {
        return numberPages;
    }

    public void setNumberPages(int numberPages)
    {
        this.numberPages = numberPages;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getGender()
    {
        return gender;
    }

    public void setGender(String gender)
    {
        this.gender = gender;
    }

    public String getFinishing()
    {
        return finishing;
    }

    public void setFinishing(String finishing)
    {
        this.finishing = finishing;
    }
}