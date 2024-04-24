package codigo;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Restaurante restaurante = new Restaurante();
        Scanner scanner = new Scanner(System.in);
        Cliente cliente = null;
        Requisicao requisicao = null;

        while (true) {
            System.out.println("\nMenu:");
<<<<<<< HEAD
            System.out.println("1. Solicitar mesa");   
            System.out.println("2. Ver fila de espera");         
            System.out.println("3. Remover cliente da mesa");
=======
            System.out.println("1. Solicitar mesa");
            // System.out.println("2. Entrar na fila de espera"); // provavelmente sairá
            // essa opção
            System.out.println("2. Ver fila de espera");
            // System.out.println("4. Sentar na mesa ");
            System.out.println("5. Remover cliente da mesa");
>>>>>>> c089e09dee520e9dda9c67d3b012e8fca67ef8df
            System.out.println("6. Sair... ");
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

                    requisicao = new Requisicao(qtdPessoas, cliente);

                    restaurante.sentarCliente(requisicao);

                    //criar a requisição
                    //checar disponibilidade de mesa com a requisição
                    //aceitar requisição e alocar cliente OU mandar cliente para a fila de espera      
                    
                break;
                case 2:                  
                restaurante.listarClientesNaFilaDeEspera();              
                break;
                case 3:
                System.out.println("Informe o ID do cliente que será removido: ");
                id = scanner.nextInt();
                restaurante.removerClienteDaMesa(id);
                break;    
                               
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

           
        }

    }
}
