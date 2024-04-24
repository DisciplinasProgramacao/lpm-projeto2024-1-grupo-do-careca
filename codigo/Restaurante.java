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
        // validação da qtd de pessoas na requisicao para entrar ou nao na fila de
        // espera

        if (!filaDeEspera.isEmpty()) {
            Requisicao proxCliente = filaDeEspera.peek(); // Obter o próximo cliente na fila de espera

            // Verifica se há uma mesa disponível para o cliente
            for (Mesa mesa : mesas) {
                if (mesa.isDisponivel(proxCliente.getQuantidadeDePessoas())) {
                    mesa.ocuparMesa(proxCliente); // Ocupa a mesa
                    System.out.println("Cliente " + proxCliente.getCliente().getNome() + " sentou-se à mesa.");
                    return;
                }
            }
        }

        // Se não houver clientes na fila de espera ou não houver mesas disponíveis para
        // o próximo cliente na fila de espera,
        // verifica se há uma mesa disponível para o cliente atual
        for (Mesa mesa : mesas) {
            if (mesa.isDisponivel(requisicao.getQuantidadeDePessoas())) {
                mesa.ocuparMesa(requisicao); // Ocupa a mesa com o cliente atual
                System.out.println("Cliente " + requisicao.getCliente().getNome() + " sentou-se à mesa.");
                return;
            }
        }

        filaDeEspera.add(requisicao);
        System.out
                .println("Não foi possível alocar uma mesa para o cliente " + requisicao.getCliente().getNome() + ".");

        System.out.println("Cliente " + requisicao.getCliente().getNome() + " movido para a fila de espera");

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

    public void removerClienteDaMesa(int id) {
        for (Mesa mesa : mesas) {
            Requisicao req = mesa.getRequisicao();
            if (req != null && req.getCliente().getId() == id) {
                mesa.desocuparMesa(); // provavelmente nesse metodo aqui
                System.out.println("Cliente " + req.getCliente().getNome() + " foi removido da mesa.");
                return;
            }
        }
        System.out.println("Cliente " + requisicao.getCliente().getNome() + " não está em nenhuma mesa.");
    }

    public void adicionarClienteNaFilaDeEspera(Requisicao requisicao) {
        filaDeEspera.add(requisicao);
    }

}
