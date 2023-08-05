package model.entities;

/**
 *
 */
abstract public class Entity
{

    private final int id;

    public Entity(int id)
    {
        this.id = id;
    }

    public int getID()
    {
        return this.id;
    }
}