package model;

/**
 * @author cassiano
 */
public enum Sex {
    MASCULINO("M"),
    FEMININO("F");
    
    private String descricao;
    
    private Sex(String descricao){
        this.descricao = descricao;
    }
    
    public String getDescricao(){
        return this.descricao;
    }
}
