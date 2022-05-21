package model;

import java.math.BigDecimal;

/**
 *
 *
 */
abstract public class Product
{

    private int code;
    private BigDecimal buyValue;
    private BigDecimal sellValue;

    public Product(int code, BigDecimal sellValue, BigDecimal buyValue)
    {
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