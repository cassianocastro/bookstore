package model;

/**
 *
 *
 */
public class Address
{

    private final String uf;
    private final String city;
    private final String bairro;
    private final String street;
    private final String compl;
    private final String cep;
    private final int number;

    public Address(String uf, String city, String bairro, String street, int number, String compl, String cep)
    {
        this.uf     = uf;
        this.city   = city;
        this.bairro = bairro;
        this.street = street;
        this.number = number;
        this.compl  = compl;
        this.cep    = cep;
    }

    public String getUf()
    {
        return uf;
    }

    public String getCity()
    {
        return city;
    }

    public String getBairro()
    {
        return bairro;
    }

    public String getStreet()
    {
        return street;
    }

    public int getNumber()
    {
        return number;
    }

    public String getCompl()
    {
        return compl;
    }

    public String getCep()
    {
        return cep;
    }
}