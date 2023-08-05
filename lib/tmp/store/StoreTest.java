package lib.tmp.store;

import javax.swing.JOptionPane;

/**
 *
 */
public class StoreTest
{

    public void testAddProduct()
    {
        String name  = JOptionPane.showInputDialog("Nome do produto:");
        String value = JOptionPane.showInputDialog("Valor:");
        int code     = Integer.parseInt(JOptionPane.showInputDialog("Código:"));

        Product produto = new Product(code, name, value);

        int retorno = JOptionPane.showConfirmDialog(
            null,
            "Adicionar à lista de compras?",
            "Opção",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );

        if ( retorno == JOptionPane.YES_OPTION )
        {
            int qtde = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe a quantidade do produto:"));

            RequestedItem item = new RequestedItem(produto, qtde);
            Ordered pedido   = new Ordered();
            pedido.add(item);

            JOptionPane.showMessageDialog(
                null,
                "Item adicionado: "            + pedido.getItems() +
                "\nValor Total da compra: R$ " + pedido.getTotal().toString()
            );
        }
    }
}