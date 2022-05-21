package model;

/**
 *
 *
 */
public class PublishingCia
{

    private final int id;
    private final String name;
    private final Address address;

    public PublishingCia(int id, String name, Address address)
    {
        this.id      = id;
        this.name    = name;
        this.address = address;
    }

    public int getID()
    {
        return this.id;
    }

    public String getName()
    {
        return this.name;
    }

    public Address getAddress()
    {
        return this.address;
    }
}