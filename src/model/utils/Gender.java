package model.utils;

/**
 *
 */
public enum Gender
{

    ADVENTURE(0), HORROR(1);
    
    private int description;
    
    private Gender(int description)
    {
        this.description = description;
    }
    
    @Override
    public String toString()
    {
        return this == ADVENTURE ? "Adventure" : "Horror";
    }
}