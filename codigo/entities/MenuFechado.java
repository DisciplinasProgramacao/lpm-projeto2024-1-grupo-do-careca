package codigo.entities;

import java.util.List;

public class MenuFechado extends Item {
   
    private String comida;
    private List<String> bebidas;

    public MenuFechado(String nome, double preco, int identificador, String comida, List<String> bebidas) {
        super(nome, 32.0, identificador);
        this.comida = comida;
        this.bebidas = bebidas;
    }

    public void adicionarComida(Item comida) {
        super.adicionarItem(comida);
    }

    public void adicionarBebida(Item bebida) {
        super.adicionarItem(bebida);
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

   
    @Override
    public String toString() {
        return super.toString() + ", Comida: " + comida + ", Bebidas: " + bebidas;
    }
}