package codigo;

import java.util.List;
import java.util.Scanner;

public class Garcom {
    private Restaurante restaurante;
    private Cardapio cardapio;

    public Garcom(Restaurante restaurante, Cardapio cardapio) {
        this.restaurante = restaurante;
        this.cardapio = cardapio;
    }
    
    public boolean verificarMesaExistente(int numeroMesa) {
        return restaurante.mesaExiste(numeroMesa);
    }

    public boolean verificarMesaOcupada(int numeroMesa) {
        Mesa mesa = restaurante.encontrarMesaPorNumero(numeroMesa);
        return mesa != null && mesa.isMesaOcupada();
    }

    public List<Item> obterItensCardapio() {
        return cardapio.getItem();
    }

    public Item obterItemCardapio(int codigoItem) {
        return cardapio.getItem(codigoItem);
    }

    public void servirCliente(int numeroMesa, Pedido pedido) {
        restaurante.adicionarPedido(numeroMesa, pedido);
    }        

    public void fazerPedido(int numeroMesa, Pedido pedido) {
        restaurante.adicionarPedido(numeroMesa, pedido);
    }    
}


