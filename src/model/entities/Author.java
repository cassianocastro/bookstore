package model.entities;

import model.utils.Name;

/**
 *
 */
public class Author extends Entity
{

    private final Name name;

    public Author(int id, Name name)
    {
        super(id);

        this.name = name;
    }

    public Name getName()
    {
        return this.name;
    }
}