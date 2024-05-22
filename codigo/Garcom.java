package codigo;

import java.util.Scanner;

public class Garcom {
    private Restaurante restaurante;
    private Cardapio cardapio;

    public Garcom(Restaurante restaurante, Cardapio cardapio) {
        this.restaurante = restaurante;
        this.cardapio = cardapio;
    }

    public void servirCliente() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Escolha uma mesa para servir o cliente:");
        int numeroMesa = scanner.nextInt();
        scanner.nextLine();     

        if (!restaurante.mesaExiste(numeroMesa)) {
            System.out.println("Mesa não existe.");
            return;
        }
      
        Mesa mesa = restaurante.encontrarMesaPorNumero(numeroMesa);
    if (!mesa.isMesaOcupada()) {
        System.out.println("A mesa escolhida não está ocupada.");
        return;
    }

        System.out.println("Opções do Cardápio:");
        for (Item item : cardapio.getItem()) {
            System.out.println(item.getIdentificador() + ". " + item.getNome() + " - R$ " + item.getPreco());
        }
       
        Pedido pedido = new Pedido(false); 

        boolean continuarPedindo = true;
        do {
            System.out.println("Digite o código do item desejado (0 para encerrar):");
            int codigoItem = scanner.nextInt();
            scanner.nextLine();  

            if (codigoItem == 0) {
                continuarPedindo = false;
            } else {
                Item item = cardapio.getItem(codigoItem);
                if (item != null) {
                    pedido.pedirItem(item);
                } else {
                    System.out.println("Item não encontrado.");
                }
            }
        } while (continuarPedindo);

        fazerPedido(numeroMesa, pedido);
        
        restaurante.adicionarPedido(numeroMesa, pedido);

        System.out.println("Pedido realizado com sucesso.");

    }

    public void fazerPedido(int numeroMesa, Pedido pedido) {
        restaurante.adicionarPedido(numeroMesa, pedido);
    }

    
}


