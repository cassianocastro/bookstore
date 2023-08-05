package model.entities;

import java.util.Calendar;
import model.utils.Name;
import model.utils.Sex;

/**
 *
 */
public class PF extends Person
{
    
    private final String cpf;
    private final Sex sex;
    
    public PF(int id, Name name, Calendar date, Address address, String cpf, Sex sex)
    {
        super(id, name, date, address);
        
        this.cpf = cpf;
        this.sex = sex;
    }

    public String getCPF()
    {
        return this.cpf;
    }
    
    public Sex getSex()
    {
        return this.sex;
    }
}