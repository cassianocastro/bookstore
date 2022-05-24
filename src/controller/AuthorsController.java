package controller;

import view.AuthorView;

/**
 *
 *
 */
public class AuthorsController
{

    private final AuthorView view;

    public AuthorsController()
    {
        this.view = new AuthorView(this);
    }
}