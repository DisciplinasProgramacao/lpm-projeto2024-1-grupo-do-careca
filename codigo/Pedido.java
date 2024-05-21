package codigo;

import java.util.ArrayList;
import java.util.List;


public class Pedido {

    private final double TAXA = 0.1;
    private boolean menuFechado;   

    private List<Item> itemsEscolhidos;

    // Construtor
    public Pedido(boolean menuFechado) {
        this.itemsEscolhidos = new ArrayList<>();
        this.menuFechado = menuFechado;
    }

    public void pedirItem(Item pedido) {
        itemsEscolhidos.add(pedido);
    }

    public double valorAPagar() {
        double valorTotal = 0.0;

        for (Item item : itemsEscolhidos) {
            valorTotal += item.getPreco();
        }

        if(!menuFechado){
            valorTotal += valorTotal * TAXA;
        }
        return valorTotal;
    }

    public double calcularValorPorPessoa(int numeroDePessoas) {
        return valorAPagar() / numeroDePessoas;
    }

        
}





