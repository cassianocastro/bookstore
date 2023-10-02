package lib.tmp.store;

import java.math.BigDecimal;

/**
 *
 */
public class Product
{

    private final int code;
    private final String name;
    private final BigDecimal value;

    public Product(int code, String name, String value)
    {
        this.code  = code;
        this.name  = name;
        this.value = new BigDecimal(value);
    }

    public BigDecimal getValue()
    {
        return this.value;
    }

    @Override
    public String toString()
    {
        return "\nCódigo: " + this.code
            + "\nNome: "    + this.name
            + "\nPreço: "   + this.value.toString();
    }
}