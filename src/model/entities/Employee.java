package model.entities;

import java.util.Calendar;
import model.utils.Contract;
import model.utils.Name;
import model.utils.Sex;

/**
 *
 */
public class Employee extends PF
{

    private final Contract contract;

    public Employee(int id, Name name, Address address, String cpf, Calendar date, Sex sex, Contract contract)
    {
        super(id, name, date, address, cpf, sex);
        
        this.contract = contract;
    }

    public Contract getContract()
    {
        return this.contract;
    }
}