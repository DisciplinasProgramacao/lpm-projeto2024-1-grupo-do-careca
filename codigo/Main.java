package codigo;

import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        Restaurante restaurante = new Restaurante();
        Scanner scanner = new Scanner(System.in);
        Cliente cliente = null;
        Requisicao requisicao = null;
        boolean continuarExecucao = true;
        Queue<Requisicao> filaDeEspera = new LinkedList<>(); // Inicialização da fila de espera
        List<Mesa> mesas = new ArrayList<>(); // Inicialização da lista de mesas

        while (continuarExecucao) {
            System.out.println("\nMenu:");
            System.out.println("1. Solicitar mesa");
            System.out.println("2. Ver fila de espera");
            System.out.println("3. Remover cliente da mesa");
            System.out.println("4. Atender cliente");
            System.out.println("5. Sair... ");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Nome do cliente: ");
                    scanner.nextLine();
                    String nomeDoCliente = scanner.nextLine();
                    System.out.print("Id do cliente: ");
                    int id = scanner.nextInt();
                    cliente = new Cliente(nomeDoCliente, id);

                    System.out.print("Informe a quantidade de pessoas: ");
                    int qtdPessoas = scanner.nextInt();

                    requisicao = new Requisicao(qtdPessoas, cliente, filaDeEspera, mesas); // Instanciação correta da Requisicao

                    restaurante.sentarCliente(requisicao); // Chamada do método na instância correta

                    break;
                case 2:
                    restaurante.listarClientesNaFilaDeEspera();
                    break;
                case 3:
                    System.out.println("Informe o ID do cliente que será removido: ");
                    id = scanner.nextInt();
                    requisicao.removerClienteDaMesa(id);
                    requisicao.fecharConta();
                    break;

                case 4:
                    // Código para atender cliente, ler entrada, etc.
                    break;

                case 5:
                    System.out.println("Saindo...");
                    continuarExecucao = false;
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
