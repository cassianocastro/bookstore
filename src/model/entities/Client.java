package model.entities;

import model.Name;

/**
 *
 *
 */
public class Client extends Entity
{

    private final Name name;
    private final String cpf;

    public Client(int id, Name name, String cpf)
    {
        super(id);
        this.name = name;
        this.cpf  = cpf;
    }

    public Name getName()
    {
        return this.name;
    }

    public String getCPF()
    {
        return this.cpf;
    }
}