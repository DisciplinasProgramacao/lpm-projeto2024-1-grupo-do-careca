package codigo;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private final double TAXA = 0.1;

    private List<Item> Cardapio = new ArrayList<>();

    private List<Item> itemsEscolhidos = new ArrayList<>();

    // Construtor
    public Pedido() {

    }

    public void pedirItem(Item item) {
        itemsEscolhidos.add(item);
    }

    public void valorAPagar() {
        double valorTotal = 0.0;

        for (Item item : itemsEscolhidos) {
            valorTotal += item.getPreco();
        }
        valorTotal += valorTotal * TAXA;
    }

    public void fecharPedido() {
        // percorrer o itemsEscolhidos, somar e dividir pela qtd de pessoas

    }

        Item item = new Item("Moqueca de palmito", 32.00);
        itemsEscolhidos.add(item);
    }

    // metodo para total do pedido


}
