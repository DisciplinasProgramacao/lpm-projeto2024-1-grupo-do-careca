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
                    System.out.println("Cardápio:");

                    System.out.print("Digite o ID do cliente a ser servido: ");
                    int clienteIdParaServir = scanner.nextInt();
                    scanner.nextLine();

                    boolean clienteServido = false;

                    Optional<Requisicao> requisicaoOpt = restaurante.getRequisicoesEmMesas().stream()
                            .filter(req -> req.getCliente().getId() == clienteIdParaServir)
                            .findFirst();

                    if (requisicaoOpt.isPresent()) {
                        Requisicao requisicao = requisicaoOpt.get();

                        System.out.print("O cliente deseja o menu fechado? (s/n): ");
                        String desejaMenuFechado = scanner.nextLine();

                        if (desejaMenuFechado.equalsIgnoreCase("s")) {
                            MenuFechado menuFechado = new MenuFechado();
                            menuFechado.exibirOpcoesDisponiveis();

                            // Lógica para escolher a comida
                            System.out.print("Escolha a comida (digite o número correspondente): ");
                            int escolhaComida = scanner.nextInt();
                            scanner.nextLine(); // Consumir a quebra de linha após o nextInt

                            switch (escolhaComida) {
                                case 1:
                                    menuFechado.setComida("Falafel Assado");
                                    break;
                                case 2:
                                    menuFechado.setComida("Caçarola de Legumes");
                                    break;
                                default:
                                    System.out.println("Opção inválida para comida.");
                                    break;
                            }

                            // Lógica para escolher as bebidas
                            System.out.print("Escolha a primeira bebida (digite o número correspondente): ");
                            int escolhaBebida1 = scanner.nextInt();
                            scanner.nextLine(); // Consumir a quebra de linha após o nextInt

                            System.out.print("Escolha a segunda bebida (digite o número correspondente): ");
                            int escolhaBebida2 = scanner.nextInt();
                            scanner.nextLine(); // Consumir a quebra de linha após o nextInt

                            switch (escolhaBebida1) {
                                case 3:
                                    menuFechado.adicionarBebida("Copo de Suco");
                                    break;
                                case 4:
                                    menuFechado.adicionarBebida("Refrigerante Orgânico");
                                    break;
                                case 5:
                                    menuFechado.adicionarBebida("Cerveja Vegana");
                                    break;
                                default:
                                    System.out.println("Opção inválida para a primeira bebida.");
                                    break;
                            }

                            switch (escolhaBebida2) {
                                case 3:
                                    menuFechado.adicionarBebida("Copo de Suco");
                                    break;
                                case 4:
                                    menuFechado.adicionarBebida("Refrigerante Orgânico");
                                    break;
                                case 5:
                                    menuFechado.adicionarBebida("Cerveja Vegana");
                                    break;
                                default:
                                    System.out.println("Opção inválida para a segunda bebida.");
                                    break;
                            }

                            // Adicionar o menu fechado à requisição
                            requisicao.adicionarItemAoPedido(menuFechado);
                            clienteServido = true;
                        }
                    } else {
                        System.out.println("Cliente não encontrado ou não está alocado em uma mesa.");
                    }

                    if (clienteServido) {
                        System.out.println("Pedido atualizado:");
                        requisicaoOpt.ifPresent(requisicao -> System.out.println(requisicao.getPedido().toString()));
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
