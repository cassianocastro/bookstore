/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 *
 */
public class Author
{

    private final int id;
    private final Name name;

    public Author(int id, Name name)
    {
        this.id   = id;
        this.name = name;
    }

    public int getID()
    {
        return this.id;
    }

    public Name getName()
    {
        return this.name;
    }
}