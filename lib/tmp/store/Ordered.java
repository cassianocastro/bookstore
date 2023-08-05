package lib.tmp.store;

import java.math.BigDecimal;
import java.util.*;

/**
 *
 */
public class Ordered
{

    private BigDecimal total;
    private final List<RequestedItem> carrinho;

    public Ordered()
    {
        this.carrinho = new ArrayList<>();
        this.total    = new BigDecimal("0");
    }

    public void add(RequestedItem item)
    {
        this.carrinho.add(item);
    }

    public BigDecimal getTotal()
    {
        for ( RequestedItem itemPedido : this.carrinho )
        {
            this.total
                = this.total
                    .add(itemPedido.getPrice().multiply(
                        new BigDecimal(itemPedido.getQuantity())));
        }
        
        return this.total;
    }

    public String getItems()
    {
        StringBuilder msg = new StringBuilder();

        for ( RequestedItem itemPedido : this.carrinho )
        {
            msg.append(itemPedido.toString());
        }
        
        return msg.toString();
    }
}