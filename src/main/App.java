package main;

import controller.IndexController;

/**
 *
 */
public class App
{

    public void start()
    {
        java.awt.EventQueue.invokeLater(() -> new IndexController());
    }
}