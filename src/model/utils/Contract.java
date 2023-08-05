package model.utils;

/**
 *
 */
public class Contract
{

    private final int matricula;
    private final String depto;
    private final String cargo;

    public Contract(int matricula, String depto, String cargo)
    {   
        this.matricula = matricula;
        this.depto     = depto;
        this.cargo     = cargo;
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
}