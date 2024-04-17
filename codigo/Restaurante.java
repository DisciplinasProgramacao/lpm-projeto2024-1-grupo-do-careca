package codigo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Restaurante {
    private List<Mesa> mesas;
    private Queue<Requisicao> filaDeEspera;

    private Requisicao requisicao;

    public Restaurante() {
        mesas = new ArrayList<>();
        filaDeEspera = new LinkedList<>();

        // Inicializar as mesas
        mesas.add(new Mesa(4));
        mesas.add(new Mesa(4));
        mesas.add(new Mesa(4));
        mesas.add(new Mesa(4));
        mesas.add(new Mesa(6));
        mesas.add(new Mesa(6));
        mesas.add(new Mesa(6));
        mesas.add(new Mesa(6));
        mesas.add(new Mesa(8));
        mesas.add(new Mesa(8));
    }

    public boolean temMesaDisponivel(int quant) {
        for (Mesa mesa : mesas) {
            if (mesa.isDisponivel(quant)) {
                return true;
            }
        }
        return false;
    }

    /*
     * 
     * public void enviarClienteParaFilaDeEspera(Cliente cliente) {
     * if(cliente.getQuantidadeDePessoas() > 8) {
     * System.out.println("Quantidade acima do permitido");
     * } else {
     * 
     * filaDeEspera.add(cliente);
     * System.out.println("Cliente " + cliente.getNome() +
     * " adicionado à fila de espera.");
     * }
     * }
     */
    public void sentarCliente(Requisicao requisicao) {

        // Verifica se há uma mesa disponível para o cliente
        for (Mesa mesa : mesas) {
            if (mesa.isDisponivel(requisicao.getQuantidadeDePessoas())) {
                mesa.ocuparMesa(requisicao); // Ocupa a mesa
                System.out.println("Cliente " + requisicao.getCliente().getNome() + " sentou-se à mesa.");
                requisicao = filaDeEspera.remove(); // remover cliente da fila de espera
                return;
            }
        }
        System.out
                .println("Não foi possível alocar uma mesa para o cliente " + requisicao.getCliente().getNome() + ".");
        adicionarClienteNaFilaDeEspera(requisicao);
        System.out.println("Cliente movido para a fila de espera");
    }

    public void listarClientesNaFilaDeEspera() {
        if (filaDeEspera.isEmpty()) {
            System.out.println("Não há clientes na fila de espera");
        } else {
            System.out.println("Clientes na fila de espera: ");
            for (Requisicao c : filaDeEspera) {
                System.out.println(" - " + c.getCliente().getNome());
            }
        }
    }

    public Mesa acharCliente(String nome) {
        for (Mesa mesa : mesas) {
            if (mesa.clienteSentado().equals(nome)) {
                return mesa;
            }
        }
        return null;
    }

    // aqui falta direcionar qual cliente sera removido da mesa
    public void removerClienteDaMesa(String nomeDoCliente) {
        Mesa mesaDoCliente = acharCliente(nomeDoCliente);

        if (mesaDoCliente != null) {
            mesaDoCliente.desocuparMesa(); // troca o status da mesa
            System.out.println("Cliente " + nomeDoCliente + " foi removido da mesa com sucesso.");
        } else {
            System.out.println("Cliente " + nomeDoCliente + " não está em nenhuma mesa.");
        }
    }

    public void adicionarClienteNaFilaDeEspera(Requisicao requisicao) {
        filaDeEspera.add(requisicao);
    }

}
