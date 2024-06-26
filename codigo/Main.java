package codigo;

import java.util.Optional;
import java.util.Scanner;

import codigo.entities.Cardapio;
import codigo.entities.Cliente;
import codigo.entities.Item;
import codigo.entities.MenuFechado;
import codigo.entities.Mesa;
import codigo.entities.Requisicao;
import codigo.entities.Restaurante;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Cardapio cardapio = new Cardapio();
        Restaurante restaurante = new Restaurante(cardapio);

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Atender Cliente");
            System.out.println("2. Ver Fila de Espera");
            System.out.println("3. Servir Cliente");
            System.out.println("4. Encerrar Atendimento de Cliente");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1:
                    atenderCliente(scanner, restaurante);
                    break;

                case 2:
                    System.out.println("Fila de Espera:");
                    for (Requisicao req : restaurante.getRequisicoesEmMesas()) {
                        System.out.println(req);
                    }
                    break;

                case 3:

                    System.out.println("Cardápio:");

                    System.out.print("Digite o ID do cliente a ser servido: ");
                    int clienteIdParaServir = scanner.nextInt();
                    scanner.nextLine();

                    Optional<Requisicao> requisicaoOpt = restaurante.getRequisicoesEmMesas().stream()
                            .filter(req -> req.getCliente().getId() == clienteIdParaServir)
                            .findFirst();

                    if (requisicaoOpt.isPresent()) {
                        Requisicao requisicao = requisicaoOpt.get();
                        boolean adicionarMaisItens = true;

                        while (adicionarMaisItens) {
                            System.out.print("O cliente deseja o menu fechado? (s/n): ");
                            String desejaMenuFechado = scanner.nextLine();

                            if (desejaMenuFechado.equalsIgnoreCase("s")) {

                                fazerPedidoMenuFechado(requisicao, scanner);

                            } else {

                                fazerPedidoNormal(scanner, restaurante, cardapio, requisicao);
                                break;

                            }

                            System.out.print("Deseja adicionar mais itens ao pedido? (s/n): ");
                            String desejaAdicionarMaisItens = scanner.nextLine();
                            adicionarMaisItens = desejaAdicionarMaisItens.equalsIgnoreCase("s");
                        }
                        System.out.println("Pedido atualizado:");
                        System.out.println(requisicao.getPedido().toString());
                    } else {
                        System.out.println("Cliente não encontrado ou não está alocado em uma mesa.");
                    }
                    break;

                case 4:
                    encerrarAtendimento(scanner, restaurante);
                    break;

                case 5:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }

    private static void atenderCliente(Scanner scanner, Restaurante restaurante) {
        System.out.print("Digite o ID do cliente: ");
        int clienteId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Digite o nome do cliente: ");
        String nomeCliente = scanner.nextLine();
        Cliente cliente = new Cliente(clienteId, nomeCliente);
        System.out.print("Digite o número de pessoas: ");
        int numeroDePessoas = scanner.nextInt();
        scanner.nextLine();

        restaurante.registrarCliente(cliente);
        Optional<Mesa> mesaOpt = restaurante.buscarMesaDisponivel(numeroDePessoas);

        if (mesaOpt.isPresent()) {
            Mesa mesa = mesaOpt.get();
            Requisicao requisicao = restaurante.criarRequisicao(mesa.getId(), clienteId, numeroDePessoas);
            System.out.println("Cliente atendido e sentado na mesa " + mesa.getId());
            System.out.println(requisicao);
        } else {
            System.out.println("Não há mesas disponíveis no momento. Cliente adicionado à fila de espera.");
            restaurante.adicionarRequisicao(new Requisicao(null, cliente, numeroDePessoas));
        }
    }

    public static void encerrarAtendimento(Scanner scanner, Restaurante restaurante) {
        System.out.print("Digite o ID do cliente para encerrar atendimento: ");
        int clienteIdParaEncerrar = scanner.nextInt();
        scanner.nextLine();

        Optional<Requisicao> requisicaoParaEncerrarOpt = restaurante.getRequisicoesEmMesas().stream()
                .filter(req -> req.getCliente().getId() == clienteIdParaEncerrar)
                .findFirst();

        if (requisicaoParaEncerrarOpt.isPresent()) {
            Requisicao requisicaoParaEncerrar = requisicaoParaEncerrarOpt.get();
            restaurante.encerrarAtendimento(requisicaoParaEncerrar);
            System.out.println("Atendimento encerrado.");
        } else {
            System.out.println("Cliente não encontrado ou não está alocado em uma mesa.");
        }

    }

    public static void fazerPedidoNormal(Scanner scanner, Restaurante restaurante, Cardapio cardapio,
            Requisicao requisicao) {
        while (true) {
            restaurante.exibirCardapio();
            System.out.print("Digite o ID do item a ser adicionado ao pedido: ");
            int itemId = scanner.nextInt();
            scanner.nextLine();

            Optional<Item> itemOpt = cardapio.buscarItemPorId(itemId);
            if (itemOpt.isPresent()) {
                requisicao.adicionarItemAoPedido(itemOpt.get());
                System.out.println("Item adicionado ao pedido.");
            } else {
                System.out.println("Item não encontrado no cardápio.");
            }

            System.out.print("Deseja adicionar outro item? (s/n): ");
            String desejaAdicionarOutroItem = scanner.nextLine();
            if (!desejaAdicionarOutroItem.equalsIgnoreCase("s")) {
                break;
            }
        }
    }

    private static void fazerPedidoMenuFechado(Requisicao requisicao, Scanner scanner) {
        for (int i = 0; i < requisicao.getNumeroDePessoas(); i++) {
            MenuFechado menuFechado = new MenuFechado(i + 1);
            menuFechado.exibirOpcoesDisponiveis();

            System.out.print("Escolha a comida (digite o número correspondente): ");
            int escolhaComida = scanner.nextInt();
            scanner.nextLine();

            switch (escolhaComida) {
                case 1:
                    menuFechado.adicionarComida(new Item("Falafel Assado", 20.0, 1));
                    break;
                case 2:
                    menuFechado.adicionarComida(new Item("Caçarola de Legumes", 22.0, 2));
                    break;
                default:
                    System.out.println("Opção inválida para comida.");
                    break;
            }

            System.out.print("Escolha a primeira bebida (digite o número correspondente): ");
            int escolhaBebida1 = scanner.nextInt();
            scanner.nextLine();
            switch (escolhaBebida1) {
                case 3:
                    menuFechado.adicionarBebida(new Item("Copo de Suco", 7.0, 3));
                    break;
                case 4:
                    menuFechado.adicionarBebida(new Item("Refrigerante Orgânico", 7.0, 4));
                    break;
                case 5:
                    menuFechado.adicionarBebida(new Item("Cerveja Vegana", 9.0, 5));
                    break;
                default:
                    System.out.println("Opção inválida para a primeira bebida.");
                    break;
            }

            System.out.print("Escolha a segunda bebida (digite o número correspondente): ");
            int escolhaBebida2 = scanner.nextInt();
            scanner.nextLine();
            switch (escolhaBebida2) {
                case 3:
                    menuFechado.adicionarBebida(new Item("Copo de Suco", 7.0, 3));
                    break;
                case 4:
                    menuFechado.adicionarBebida(new Item("Refrigerante Orgânico", 7.0, 4));
                    break;
                case 5:
                    menuFechado.adicionarBebida(new Item("Cerveja Vegana", 9.0, 5));
                    break;
                default:
                    System.out.println("Opção inválida para a segunda bebida.");
                    break;
            }

            requisicao.adicionarItemAoPedido(menuFechado);
        }
    }

}
