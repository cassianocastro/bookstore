package model.entities;

import java.io.Serializable;
import java.util.Calendar;
import model.utils.Name;

/**
 *
 */
abstract public class Person extends Entity implements Serializable
{

    private final Name name;
    private final Calendar date;
    private final Address address;

    public Person(int id, Name name, Calendar date, Address address)
    {
        super(id);
        this.name    = name;
        this.date    = date;
        this.address = address;
    }
    
    public Name getName()
    {
        return this.name;
    }

    public Calendar getDate()
    {
        return this.date;
    }

    public Address getAddress()
    {
        return this.address;
    }
}