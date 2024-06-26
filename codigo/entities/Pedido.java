package codigo.entities;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private final double TAXA = 0.1;
    private final List<Item> itens;

    public Pedido() {
        this.itens = new ArrayList<>();
    }

    public void adicionarItem(Item item) {
        itens.add(item);
    }

    public double calcularValorTotal() {
        double total = itens.stream().mapToDouble(Item::getPreco).sum();
        boolean temMenuFechado = itens.stream().anyMatch(item -> item instanceof MenuFechado);
        if (temMenuFechado) {
            total = itens.stream()
                .filter(item -> item instanceof MenuFechado)
                .mapToDouble(Item::getPreco)
                .sum();
        }
        return total; 
    }


    public double calcularValorTotalComTaxa() {
        double valorTotal = calcularValorTotal();
        return valorTotal + (valorTotal * TAXA);
    }

    public double calcularValorPorPessoa(int numeroDePessoas) {
        return calcularValorTotalComTaxa() / numeroDePessoas;
    }

    public String listarItens() {
        StringBuilder builder = new StringBuilder();
        itens.forEach(item -> {
            builder.append(item.getDescricao()).append(" - R$ ").append(String.format("%.2f", item.getPreco()))
                    .append("\n");
        });
        return builder.toString();
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "itens=" + itens +
                '}';
    }
}
