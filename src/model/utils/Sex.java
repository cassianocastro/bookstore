package model;

/**
 *
 *
 */
public enum Sex
{

    MALE("M"), FEMALE("F");

    private final String description;

    private Sex(String description)
    {
        this.description = description;
    }

    public String getDescription()
    {
        return this.description;
    }
}