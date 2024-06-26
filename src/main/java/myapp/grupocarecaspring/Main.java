package myapp.grupocarecaspring;


//ENTITY E CONTROLLER


import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Optional;
import myapp.grupocarecaspring.entities.Cardapio;
import myapp.grupocarecaspring.entities.Cliente;
import myapp.grupocarecaspring.entities.Mesa;
import myapp.grupocarecaspring.entities.Requisicao;
import myapp.grupocarecaspring.entities.Restaurante;

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
                    System.out.print("Digite o ID do cliente: ");
                    int clienteId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Digite o nome do cliente: ");
                    String nomeCliente = scanner.nextLine();
                    System.out.print("Digite o número de pessoas: ");
                    int numeroDePessoas = scanner.nextInt();
                    scanner.nextLine();

                    Cliente cliente = new Cliente(clienteId, nomeCliente);
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
                    break;

                case 2:
                    System.out.println("Fila de Espera:");
                    for (Requisicao req : restaurante.getRequisicoesEmMesas()) {
                        System.out.println(req);
                    }
                    break;

                case 3:
                    System.out.print("Digite o ID do cliente a ser servido: ");
                    int clienteIdParaServir = scanner.nextInt();
                    restaurante.exibirCardapio();
                    scanner.nextLine();

                    Optional<Requisicao> requisicaoOpt = restaurante.getRequisicoesEmMesas().stream()
                            .filter(req -> req.getCliente().getId() == clienteIdParaServir)
                            .findFirst();

                    if (requisicaoOpt.isPresent()) {
                        Requisicao requisicao = requisicaoOpt.get();
                        boolean adicionarMaisItens = true;

                        while (adicionarMaisItens) {
                            System.out.print("Digite o ID do item a ser adicionado ao pedido (ou 0 para finalizar): ");
                            int itemId = scanner.nextInt();
                            scanner.nextLine();

                            if (itemId == 0) {
                                adicionarMaisItens = false;
                            } else {
                                try {
                                    restaurante.adicionarItemAoPedido(requisicao, itemId);
                                    System.out.println("Item adicionado ao pedido.");
                                } catch (IllegalArgumentException e) {
                                    System.out.println(e.getMessage());
                                }
                            }
                        }
                    } else {
                        System.out.println("Cliente não encontrado ou não está alocado em uma mesa.");
                    }
                    break;

                case 4:
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
}
