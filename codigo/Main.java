package codigo;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Restaurante restaurante = new Restaurante();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Checar disponibilidade de mesa");
            System.out.println("2. Entrar na fila de espera");
            System.out.println("3. Ver fila de espera");
            System.out.println("4. Sentar na mesa ");
            System.out.println("5. Sair da mesa");
            System.out.println("5. Sair... ");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                System.out.print("Informe a quantidade de pessoas: ");
                int quantidadePessoas = scanner.nextInt();
                if (restaurante.temMesaDisponivel(quantidadePessoas)) {
                    System.out.println("Mesa disponível.");
                } else {
                    System.out.println("Não há mesa disponível no momento.");
                }
                break;
                case 2:
                    System.out.print("Informe seu nome: ");
                    String nomeCliente = scanner.next();
                    System.out.print("Informe a quantidade de pessoas: ");
                    quantidadePessoas = scanner.nextInt();
                    Cliente cliente = new Cliente(nomeCliente,quantidadePessoas);
                    restaurante.enviarClienteParaFilaDeEspera(cliente);
                    break;
                    case 3:
                    restaurante.listarClientesNaFilaDeEspera();
                    break;
                case 4:
                    restaurante.sentarCliente();
                    break;    
                case 5:
                    System.out.println("Saindo da mesa");
                    System.out.println("Mesa desocupada");
                    
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
