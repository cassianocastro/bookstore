/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 *
 */
public class Client
{

    private final int id;
    private final Name name;
    private final String cpf;

    public Client(int id, Name name, String cpf)
    {
        this.id   = id;
        this.name = name;
        this.cpf  = cpf;
    }

    public int getID()
    {
        return this.id;
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