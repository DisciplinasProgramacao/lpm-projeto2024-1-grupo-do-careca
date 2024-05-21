package codigo;

import java.util.ArrayList;
import java.util.List;


public class Pedido {

    private final double TAXA = 0.1;

    private Cardapio cardapio;

    private List<Item> itemsEscolhidos = new ArrayList<>();

    // Construtor
    public Pedido() {

    }

    public void pedirItem(Item pedidos) {
        itemsEscolhidos.add(pedidos);
    }

    public void valorAPagar() {
        double valorTotal = 0.0;

        for (Item item : itemsEscolhidos) {
            valorTotal += item.getPreco();
        }
        valorTotal += valorTotal * TAXA;
    }

        
}





