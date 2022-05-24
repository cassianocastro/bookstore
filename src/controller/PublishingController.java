package controller;

import view.PublishingView;

/**
 *
 *
 */
public class PublishingController
{

    private final PublishingView view;

    public PublishingController()
    {
        this.view = new PublishingView(this);
    }

}