package codigo;

import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Restaurante restaurante = new Restaurante();
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("Menu:");
            System.out.println("1. Atender Cliente");
            System.out.println("2. Ver Fila de Espera");
            System.out.println("3. Servir Cliente");
            System.out.println("4. Encerrar Atendimento de Cliente");       
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Nome do Cliente: ");
                    String nome = scanner.nextLine();
                    System.out.print("Número de Pessoas: ");
                    int numeroPessoas = scanner.nextInt();
                    scanner.nextLine();
                    try {
                        Cliente cliente = new Cliente(nome);
                        Requisicao requisicao = new Requisicao(numeroPessoas, cliente);
                        restaurante.adicionarRequisicao(requisicao);
                        System.out.println("Cliente adicionado com sucesso!");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;
                case 2:
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
                    break;
                    case 3:
                    // aqui vao os metodos para servir o cliente

                case 4:
                    System.out.println("Mesas Ocupadas:");
                    List<Mesa> mesasOcupadas = restaurante.getMesasOcupadas();
                    if (mesasOcupadas.isEmpty()) {
                        System.out.println("Nenhuma mesa ocupada.");
                    } else {
                        for (int i = 0; i < mesasOcupadas.size(); i++) {
                            Mesa mesa = mesasOcupadas.get(i);
                            Requisicao req = mesa.getRequisicaoAtual();
                            System.out.println((i + 1) + ". Mesa para " + mesa.getQuantidadeDeCadeiras()
                                    + " pessoas ocupada por " + req.getCliente().getNome());
                        }
                        System.out.print("Escolha uma mesa para liberar (1-" + mesasOcupadas.size() + "): ");
                        int indiceMesa = scanner.nextInt();
                        scanner.nextLine();
                        if (indiceMesa > 0 && indiceMesa <= mesasOcupadas.size()) {
                            Mesa mesa = mesasOcupadas.get(indiceMesa - 1);
                            Requisicao req = mesa.getRequisicaoAtual();
                            if (req != null) {
                                restaurante.liberarMesa(mesa);
                                System.out.println("Mesa liberada com sucesso!");
                                System.out.println("Relatório de Atendimento:");
                                System.out.println(req.relatorioAtendimento());
                            } else {
                                System.out.println("Nenhuma requisição encontrada para esta mesa.");
                            }
                        } else {
                            System.out.println("Opção inválida.");
                        }
                    }
                    break;
                case 5:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 4);

        scanner.close();
    }
}