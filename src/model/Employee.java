package model;

import java.util.Date;

/**
 *
 *
 */
public class Employee extends Person
{

    private final int id;
    private final int matricula;
    private final String cpf;
    private final String depto;
    private final String cargo;
    private final Date date;
    private final Sex sex;
    private final Address address;

    public Employee(int id, String firstName, String lastName, Address address,
            String cpf, int matricula, String depto, String cargo, Date date, Sex sex)
    {
        super(firstName, lastName);
        this.id        = id;
        this.matricula = matricula;
        this.depto     = depto;
        this.cargo     = cargo;
        this.date      = date;
        this.sex       = sex;
        this.cpf       = cpf;
        this.address   = address;
    }

    public int getId()
    {
        return id;
    }

    public int getMatricula()
    {
        return matricula;
    }

    public String getDepto()
    {
        return depto;
    }

    public String getCargo()
    {
        return cargo;
    }

    public Date getDate()
    {
        return date;
    }

    public Sex getSex()
    {
        return sex;
    }

    public Address getAddress()
    {
        return this.address;
    }

    public String getCPF()
    {
        return this.cpf;
    }
}