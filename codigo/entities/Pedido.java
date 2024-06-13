package codigo.entities;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private final double TAXA = 0.1;
    // BOMBA nao pode ser booleano
    private boolean menuFechado;

    private List<Item> itemsEscolhidos;

    public Pedido(boolean menuFechado) {
        this.itemsEscolhidos = new ArrayList<>();
        this.menuFechado = menuFechado;
    }

    public List<Item> getItemsEscolhidos() {
        return itemsEscolhidos;
    }

    public void pedirItem(Item pedido) {
        itemsEscolhidos.add(pedido);
    }

    public boolean isMenuFechado() {
        return menuFechado;
    }

    public double valorAPagar() {
        double valorTotal = 0.0;

        for (Item item : itemsEscolhidos) {
            valorTotal += item.getPreco();
        }

        return valorTotal * (1 + TAXA);
    }

    public double calcularValorPorPessoa(int numeroDePessoas) {
        return valorAPagar() / numeroDePessoas;
    }

    public String itemFormatado(Item item) {
        return item.toString() + "\n";
    }

    public List<String> relatorioItens() {
        List<String> relatorio = new ArrayList<>();
        for (Item item : itemsEscolhidos) {
            relatorio.add(itemFormatado(item));
        }
        return relatorio;

    }
}
