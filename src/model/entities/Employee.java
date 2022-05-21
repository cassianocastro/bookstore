package model.entities;

import java.util.Calendar;
import model.Name;
import model.Sex;

/**
 *
 *
 */
public class Employee extends Entity
{

    private final int matricula;
    private final Name name;
    private final String cpf;
    private final String depto;
    private final String cargo;
    private final Calendar date;
    private final Sex sex;
    private final Address address;

    public Employee(int id, Name name, Address address, String cpf,
                    int matricula, String depto, String cargo, Calendar date, Sex sex)
    {
        super(id);
        this.name      = name;
        this.matricula = matricula;
        this.depto     = depto;
        this.cargo     = cargo;
        this.date      = date;
        this.sex       = sex;
        this.cpf       = cpf;
        this.address   = address;
    }

    public Name getName()
    {
        return this.name;
    }

    public int getMatricula()
    {
        return this.matricula;
    }

    public String getDepto()
    {
        return this.depto;
    }

    public String getCargo()
    {
        return this.cargo;
    }

    public Calendar getDate()
    {
        return this.date;
    }

    public Sex getSex()
    {
        return this.sex;
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