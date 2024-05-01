package codigo;

import java.util.ArrayList;
import java.util.List;

public class Cardapio {

    private List<Item> bebidas = new ArrayList<>();
    private List<Item> comidas = new ArrayList<>();

    public Cardapio() {
        this.bebidas.add(new Item("Agua ", 3.00, 1));
        this.bebidas.add(new Item("Copo de Suco ", 7.00, 2));
        this.bebidas.add(new Item("Refrigerante Organico ", 7.00, 3));
        this.bebidas.add(new Item("Cerveja Vegana ", 9.00, 4));
        this.bebidas.add(new Item("Taça de vinho vegano ", 18.00, 5));

        this.comidas.add(new Item("Moqueca de palmito", 32.00, 6));
        this.comidas.add(new Item("Falafel Assado", 20.00, 7));
        this.comidas.add(new Item("Salada primavera com macarrão Konjac", 25.00, 8));
        this.comidas.add(new Item("Escondidinho de Inhame", 18.00, 9));
        this.comidas.add(new Item("Strogonoff de cogumelos", 35.00, 10));
        this.comidas.add(new Item("Caçarola de legumes", 22.00, 11));
    }

    public void exibirCardapio() {
        System.out.println("---------------");
        System.out.println(" ### Bebidas ###");
        System.out.println("---------------");
        for (Item items : bebidas) {
            System.out.println(items.getIdentificador() + " - " + items.getNome() + " - "
                    + String.format("%.2f", items.getPreco()));
        }
        System.out.println();

        System.out.println("---------------");
        System.out.println(" ### Comidas ###");
        System.out.println("---------------");
        for (Item items : comidas) {
            System.out.println(items.getIdentificador() + " - " + items.getNome() + " - "
                    + String.format("%.2f", items.getPreco()));
        }
        System.out.println();
    }

}