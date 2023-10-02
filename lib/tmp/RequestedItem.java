package lib.tmp.store;

import java.math.BigDecimal;

/**
 *
 */
public class RequestedItem
{

    private final Product product;
    private final int quantity;

    public RequestedItem(Product product, int quantity)
    {
        this.product  = product;
        this.quantity = quantity;
    }

    public int getQuantity()
    {
        return this.quantity;
    }

    public BigDecimal getPrice()
    {
        return this.product.getValue();
    }

    @Override
    public String toString()
    {
        return this.product.toString();
    }
}