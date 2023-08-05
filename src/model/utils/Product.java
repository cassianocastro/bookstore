package model.utils;

import model.entities.Entity;
import java.math.BigDecimal;

/**
 *
 */
abstract public class Product extends Entity
{

    private int code;
    private BigDecimal buyValue;
    private BigDecimal sellValue;

    public Product(int id, int code, BigDecimal sellValue, BigDecimal buyValue)
    {
        super(id);
        this.code      = code;
        this.buyValue  = buyValue;
        this.sellValue = sellValue;
    }

    public int getCode()
    {
        return this.code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public BigDecimal getBuyValue()
    {
        return this.buyValue;
    }

    public void setBuyValue(BigDecimal buyValue)
    {
        this.buyValue = buyValue;
    }

    public BigDecimal getSellValue()
    {
        return this.sellValue;
    }

    public void setSellValue(BigDecimal sellValue)
    {
        this.sellValue = sellValue;
    }
}