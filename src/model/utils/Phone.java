package model.utils;

/**
 *
 */
public class Phone
{

    private final int ddd;
    private final int number;
    
    public Phone(int ddd, int number)
    {
        this.ddd    = ddd;
        this.number = number;
    }
    
    public int getDDD()
    {
        return this.ddd;
    }
    
    public int getNumber()
    {
        return this.number;
    }
    
    @Override
    public String toString()
    {
        return "(" + this.ddd + ") " + this.number;
    }
}