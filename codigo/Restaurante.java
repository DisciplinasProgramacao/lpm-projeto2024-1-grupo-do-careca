package codigo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Restaurante {
    private List<Mesa> mesas;
    private Queue<Cliente> filaDeEspera;
    private List<Cliente> atendidos;

    public Restaurante() {
        mesas = new ArrayList<>();
        filaDeEspera = new LinkedList<>();
        atendidos = new ArrayList<>();
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

    public void enviarClienteParaFilaDeEspera(Cliente cliente) {
        filaDeEspera.add(cliente);
        System.out.println("Cliente " + cliente.getNome() + " adicionado à fila de espera.");
    }

    public void sentarCliente() {
        if (!filaDeEspera.isEmpty()) {
            Cliente cliente = filaDeEspera.poll(); // Obter o próximo cliente na fila de espera
            
            // Verifica se há uma mesa disponível para o cliente
            for (Mesa mesa : mesas) {
                if (mesa.isDisponivel(cliente.getQuantidadeDePessoas())) {
                    mesa.ocuparMesa(); // Ocupa a mesa
                    System.out.println("Cliente " + cliente.getNome() + " sentou-se à mesa.");
                    atendidos.add(cliente);
                    return;
                }
            }
            System.out.println("Não foi possível alocar uma mesa para o cliente " + cliente.getNome() + ".");
        } else {
            System.out.println("Não há clientes na fila de espera.");
        }
    }    
    

    public void alocarCliente(Cliente cliente) {
        for (Mesa mesa : mesas) {
            if (mesa.cabemNaMesa(cliente)) {
                mesa.ocuparMesa();
                System.out.println("Cliente " + cliente.getNome() + " sentou-se à mesa.");
                atendidos.add(cliente);
                return;
            }
        }
        enviarClienteParaFilaDeEspera(cliente);
    }

    public void listarClientesNaFilaDeEspera(){
        if(filaDeEspera.isEmpty()) {
            System.out.println("Não há clientes na fila de espera");
        } else {
            System.out.println("Clientes na fila de espera: ");
            for(Cliente c : filaDeEspera) {
                System.out.println(" - " + c.getNome());
            }
        }
    }

    
    
}
