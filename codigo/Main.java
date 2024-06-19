package codigo;

import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

import codigo.entities.Cardapio;
import codigo.entities.Cliente;
import codigo.entities.Item;
import codigo.entities.MenuFechado;
import codigo.entities.Mesa;
import codigo.entities.Pedido;
import codigo.entities.Requisicao;
import codigo.entities.Restaurante;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Cardapio cardapio = new Cardapio();
        // criacao do restaurante
        Restaurante restaurante = new Restaurante(cardapio);

        int opcao;

        do {
            System.out.println("Menu:");
            System.out.println("1. Atender Cliente"); // coleta de dados de um cliente e processo de pedir uma mesa
            System.out.println("2. Ver Fila de Espera");
            System.out.println("3. Servir Cliente");

            System.out.println("4. Encerrar Atendimento de Cliente"); // fecha a conta, mostra a conta e vaga uma mesa
            System.out.println("5. Ver Menu");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    atenderCliente(scanner, restaurante);
                    break;
                case 2:
                    verFilaDeEspera(restaurante);
                    break;
                case 3:
                    servirCliente(scanner, restaurante);
                    break;               
                case 4:
                    encerrarAtendimento(scanner, restaurante);
                    break;
                case 5:
                    verMenu(cardapio);
                    break;
                case 6:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 6);

        scanner.close();
    }

    // Permitir ver menu, selecionar produto, incluir produto, fechar conta, mostrar
    // conta

    // opcao 1
    private static void atenderCliente(Scanner scanner, Restaurante restaurante) {
        System.out.print("Nome do Cliente: ");
        String nome = scanner.nextLine();
        System.out.print("Número de Pessoas: ");
        int numeroPessoas = scanner.nextInt();
        scanner.nextLine();
        try {
            Cliente cliente = new Cliente(nome);
            Requisicao requisicaoExistente = restaurante.getFilaEspera().stream()
                    .filter(req -> req.getCliente().equals(cliente) && req.getQuantidadeDePessoas() == numeroPessoas)
                    .findFirst()
                    .orElse(null);
    
            if (requisicaoExistente != null) {
                System.out.println("Cliente " + cliente.getNome() + " já está na fila de espera.");
                return;
            }
    
            Requisicao requisicao = new Requisicao(numeroPessoas, cliente);
            restaurante.adicionarRequisicao(requisicao);
            if (requisicao.getMesa() != null) {
                System.out.println("Cliente " + cliente.getNome() + " adicionado com sucesso! Mesa: "
                        + requisicao.getMesa().getIdMesa());
            } else {
                System.out.println("Cliente " + cliente.getNome() + " adicionado à fila de espera.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    // opcao 2
    private static void verFilaDeEspera(Restaurante restaurante) {
        System.out.println("Fila de Espera:");
        Queue<Requisicao> filaEspera = restaurante.getFilaEspera();
        if (filaEspera.isEmpty()) {
            System.out.println("Nenhum cliente na fila de espera.");
        } else {
            for (Requisicao req : filaEspera) {
                System.out.println("Cliente: " + req.getCliente().getNome() + ", Número de Pessoas: "
                        + req.getQuantidadeDePessoas());
            }
        }
    }

    // opcao 3
    private static void servirCliente(Scanner scanner, Restaurante restaurante) {
        System.out.print("Escolha uma mesa para servir o cliente: ");
        int numeroMesa = scanner.nextInt();
        scanner.nextLine();

        if (!restaurante.mesaExiste(numeroMesa)) {
            System.out.println("Mesa não existe.");
            return;
        }

        if (!restaurante.verificarMesaOcupada(numeroMesa)) {
            System.out.println("A mesa escolhida não está ocupada.");
            return;
        }

        System.out.print("É menu fechado? (s/n): ");
        String resposta = scanner.nextLine();

        if (resposta.equalsIgnoreCase("s")) {
            adicionarPedidoMenuFechado(scanner, restaurante,numeroMesa);
        } else {
        criarPedido(scanner, restaurante, numeroMesa);
        }
    }

    private static void criarPedido(Scanner scanner, Restaurante restaurante, int numeroMesa) {
       
        Requisicao requisicao = restaurante.obterRequisicaoPorMesa(numeroMesa);
    
        if (requisicao == null) {
            System.out.println("Não há uma requisição associada a esta mesa.");
            return;
        }    
       
        if (!requisicao.getPedidos().isEmpty()) {
            System.out.println("Já existe um pedido associado a esta requisição.");
            return;
        }
    
        Pedido pedido = new Pedido(false);
        boolean continuarPedindo = true;
        do {
            System.out.println("Opções do Cardápio:");
            List<Item> itensCardapio = restaurante.obterItensCardapio();
            for (Item item : itensCardapio) {
                System.out.println(item.getIdentificador() + " - " + item.toString());
            }
    
            System.out.println("Digite o código do item desejado (0 para encerrar):");
            int codigoItem = scanner.nextInt();
            scanner.nextLine();
    
            if (codigoItem == 0) {
                continuarPedindo = false;
            } else {
                Item item = restaurante.obterItemCardapio(codigoItem);
                if (item != null) {
                    pedido.pedirItem(item);
                    System.out.println("Item adicionado ao pedido: " + item.getNome());
                } else {
                    System.out.println("Item não encontrado.");
                }
            }
        } while (continuarPedindo);
    
        requisicao.adicionarPedido(pedido);
        System.out.println("Pedido realizado com sucesso.");
    }
    
    // duvidas aqui, eu faço todas essas validações aqui mesmo?
    private static void encerrarAtendimento(Scanner scanner, Restaurante restaurante) {
        System.out.println(restaurante.getMesasOcupadas());
       
        System.out.print("Informe o Id da mesa para encerrar: ");
        int idMesa = scanner.nextInt(); 
        scanner.nextLine();

        if (!restaurante.verificarMesaOcupada(idMesa)) {
            System.out.println("A mesa escolhida não está ocupada ou não existe.");
            return;
        }

        Requisicao finalizada = restaurante.encerrarAtendimento(idMesa);
        if (finalizada != null) {
            finalizada.encerrarRequisicao();
            System.out.println("Atendimento finalizado: " + finalizada.relatorioAtendimento());
        } else {
            System.out.println("Erro ao encerrar atendimento.");
        }
   
    }

    // opcao 5
    private static void verMenu(Cardapio cardapio) {
        List<Item> itensCardapio = cardapio.getItems();
        for (Item item : itensCardapio) {
            System.out.println(item.getIdentificador() + ". " + item.getNome() + " - R$ " + item.getPreco());
        }
    }

    private static void adicionarPedidoMenuFechado(Scanner scanner, Restaurante restaurante, int numeroMesa) {
        
        Requisicao requisicao = restaurante.obterRequisicaoPorMesa(numeroMesa);

        if (requisicao == null) {
            System.out.println("Não há uma requisição associada a esta mesa.");
            return;
        }


        int numeroDePessoas = requisicao.getQuantidadeDePessoas();
        for (int i = 0; i < numeroDePessoas; i++) {
            System.out.println("Escolha a comida para o cliente " + (i + 1) + ": ");
            System.out.println("1. Falafel Assado");
            System.out.println("2. Caçarol de legumes");
            int opcaoComida = scanner.nextInt();
            scanner.nextLine();
            String comida = (opcaoComida == 1) ? "Falafel Assado" : "Caçarol de legumes";      

        System.out.println("Escolha duas bebidas para o cliente " + (i + 1) + " (separadas por vírgula): ");
        System.out.println("1. Copo de suco");
        System.out.println("2. Refrigerante orgânico");
        System.out.println("3. Cerveja vegana");
        String[] opcaoBebidas = scanner.nextLine().split(",");
        List<String> bebidas = Arrays.asList(
                (opcaoBebidas[0].trim().equals("1") ? "Copo de suco" : (opcaoBebidas[0].trim().equals("2") ? "Refrigerante orgânico" : "Cerveja vegana")),
                (opcaoBebidas[1].trim().equals("1") ? "Copo de suco" : (opcaoBebidas[1].trim().equals("2") ? "Refrigerante orgânico" : "Cerveja vegana"))
        );

        MenuFechado menuFechado = new MenuFechado("Menu Fechado", 32.0 , 99, comida, bebidas);
        restaurante.adicionarPedidoMenuFechado(numeroMesa, menuFechado);
        System.out.println("Pedido de menu fechado adicionado com sucesso.");
    }
  

}
}