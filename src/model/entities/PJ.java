package model.entities;

import java.util.Calendar;

import model.utils.Name;

/**
 * 
 */
public class PJ extends Person
{
    
    private final String cnpj;

    public PJ(int id, Name name, Calendar date, Address address, String cnpj)
    {
        super(id, name, date, address);
        
        this.cnpj = cnpj;
    }

    public String getCNPJ()
    {
        return this.cnpj;
    }
}