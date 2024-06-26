package myapp.grupocarecaspring.entities;

import java.util.List;

public class MenuFechado extends Item {
    private List<Item> itensMenu;

    public MenuFechado(int id) {
        super("Menu Fechado", 32.0, id);
        this.itensMenu = new ArrayList<>();
    }

    public void adicionarComida(Item comida) {
        if (itensMenu.size() < 1) {
            itensMenu.add(comida);
        }
    }

    public void adicionarBebida(Item bebida) {
        if (itensMenu.size() < 3) {
            itensMenu.add(bebida);
        }
    }

    public void exibirOpcoesDisponiveis() {
        System.out.println("Opções disponíveis para o Menu Fechado:");
        System.out.println("Comidas:");
        System.out.println("1 - Falafel Assado");
        System.out.println("2 - Caçarola de Legumes");
        System.out.println("Bebidas:");
        System.out.println("3 - Copo de Suco");
        System.out.println("4 - Refrigerante Orgânico");
        System.out.println("5 - Cerveja Vegana");
    }

    public List<Item> getItensMenu() {
        return itensMenu;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(super.toString()).append(", Itens do Menu: ");
        itensMenu.forEach(item -> builder.append(item.getDescricao()).append(", "));
        return builder.toString();
    }
}