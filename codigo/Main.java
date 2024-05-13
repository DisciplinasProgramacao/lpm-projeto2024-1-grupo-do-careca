package codigo;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Restaurante restaurante = new Restaurante();
        Scanner scanner = new Scanner(System.in);
        Cliente cliente = null;
        Requisicao requisicao = null;
        boolean continuarExecucao = true;

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

                    requisicao = new Requisicao(qtdPessoas, cliente);

                    restaurante.sentarCliente(requisicao);

                    // criar a requisição
                    // checar disponibilidade de mesa com a requisição
                    // aceitar requisição e alocar cliente OU mandar cliente para a fila de espera

                    break;
                case 2:
                    restaurante.listarClientesNaFilaDeEspera();
                    break;
                case 3:
                    System.out.println("Informe o ID do cliente que será removido: ");
                    id = scanner.nextInt();
                    restaurante.removerClienteDaMesa(id);
                    requisicao.fecharConta();
                    break;

                case 4:
                    System.out.println("Atender cliente");
                    System.out.println("Id do cliente a ser atendido: ");
                    id = scanner.nextInt();
                    restaurante.atenderCliente(id);
                    System.out.println("Escolha as opções por numero e separados por espaço: ");

                    String cardapio = restaurante.exibirCardapio();
                    System.out.println(cardapio);
                   
                    int codigoProd  = lerEntrada();
                    while(codProd!=0){
                        Item item = cardapio.getItem(codigoProd);
                        restaurante.adicionarItem(requisicao, item);
                        lerEntrada();
                    }

                    restaurante.getCardapio().exibirCardapio();
                    int[] itemsPedidos = lerEntrada();
                    cliente.pedirItemCardapio(itemsPedidos);


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

    public static int[] lerEntrada() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite os números separados por espaço:");
        String entrada = scanner.nextLine();

        String[] itemsString = entrada.split(" ");

        int[] numeros = new int[itemsString.length];

        for (int i = 0; i < itemsString.length; i++) {
            numeros[i] = Integer.parseInt(itemsString[i]);
        }


        for (int i = 0; i < numeros.length; i++) {
            System.out.println(numeros[i]);
        }


        return numeros;
    }
}
