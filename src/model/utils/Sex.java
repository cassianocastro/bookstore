package model.utils;

/**
 *
 */
public enum Sex
{

    FEMALE("F"), MALE("M");

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