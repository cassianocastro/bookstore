package model;

import java.util.Date;

/**
 * @author cassiano
 */
public class EmployeePerson extends Person{
    
    private int id;
    private int matricula;
    private String cpf;
    private String depto;
    private String cargo;
    private Date date;
    private Sex sex;
    private Address address;

    public EmployeePerson(int id, String firstName, String lastName, Address address,
            String cpf, int matricula, String depto, String cargo, Date date, Sex sex) {
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
    
    public int getId() {
        return id;
    }

    public int getMatricula() {
        return matricula;
    }

    public String getDepto() {
        return depto;
    }

    public String getCargo() {
        return cargo;
    }

    public Date getDate() {
        return date;
    }

    public Sex getSex() {
        return sex;
    }

    public Address getAddress() {
        return this.address;
    }

    public String getCPF() {
        return this.cpf;
    }
}
