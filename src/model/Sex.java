package model;

/**
 *
 *
 */
public enum Sex
{

    MASCULINO("M"), FEMININO("F");

    private final String descricao;

    private Sex(String descricao)
    {
        this.descricao = descricao;
    }

    public String getDescricao()
    {
        return this.descricao;
    }
}