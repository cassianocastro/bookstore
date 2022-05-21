package model.entities;

/**
 *
 *
 */
public class PublishingCia extends Entity
{

    private final String name;
    private final Address address;

    public PublishingCia(int id, String name, Address address)
    {
        super(id);
        this.name    = name;
        this.address = address;
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