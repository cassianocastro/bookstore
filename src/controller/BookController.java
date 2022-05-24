package controller;

import view.BookView;

/**
 *
 *
 */
public class BookController
{

    private final BookView view;

    public BookController()
    {
        this.view = new BookView(this);
    }

}