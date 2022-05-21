package model;

/**
 *
 *
 */
public class Name
{

    private final String first;
    private final String last;

    public Name(String first, String last)
    {
        this.first = first;
        this.last  = last;
    }

    public String getFirst()
    {
        return this.first;
    }

    public String getLast()
    {
        return this.last;
    }

    @Override
    public String toString()
    {
        return this.first + " " + this.last;
    }
}