package model.entities;

/**
 *
 *
 */
public class Address
{

    private final String uf;
    private final String city;
    private final String district;
    private final String street;
    private final String complement;
    private final String cep;
    private final int number;

    public Address(String uf, String city, String district, String street, int number, String compl, String cep)
    {
        this.uf         = uf;
        this.city       = city;
        this.district   = district;
        this.street     = street;
        this.number     = number;
        this.complement = compl;
        this.cep        = cep;
    }

    public String getUF()
    {
        return this.uf;
    }

    public String getCity()
    {
        return this.city;
    }

    public String getDistrict()
    {
        return this.district;
    }

    public String getStreet()
    {
        return this.street;
    }

    public int getNumber()
    {
        return this.number;
    }

    public String getComplement()
    {
        return this.complement;
    }

    public String getCEP()
    {
        return this.cep;
    }
}