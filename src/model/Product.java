package model;

import java.math.BigDecimal;

/**
 *
 *
 */
abstract public class Product
{

    private BigDecimal sellValue, buyValue;

    public Product(BigDecimal sellValue, BigDecimal buyValue)
    {
        this.buyValue  = buyValue;
        this.sellValue = sellValue;
    }

    public BigDecimal getSellValue()
    {
        return sellValue;
    }

    public void setSellValue(BigDecimal sellValue)
    {
        this.sellValue = sellValue;
    }

    public BigDecimal getBuyValue()
    {
        return buyValue;
    }

    public void setBuyValue(BigDecimal buyValue)
    {
        this.buyValue = buyValue;
    }
}