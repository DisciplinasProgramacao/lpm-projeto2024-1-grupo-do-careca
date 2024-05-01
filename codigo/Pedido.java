package codigo;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private List<Item> Cardapio = new ArrayList<>();
    private List<Item> itemsEscolhidos = new ArrayList<>();

    // Construtor
    public Pedido() {
        Item item = new Item("Moqueca de palmito", 32.00);
        itemsEscolhidos.add(item);
    }

    // metodo para total do pedido

}
