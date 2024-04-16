package codigo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Restaurante {
    private List<Mesa> mesas;
    private Queue<Cliente> filaDeEspera;

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
     public void enviarClienteParaFilaDeEspera(Cliente cliente) {
         if(cliente.getQuantidadeDePessoas() > 8) {
             System.out.println("Quantidade acima do permitido");
            } else {
                
                filaDeEspera.add(cliente);
                System.out.println("Cliente " + cliente.getNome() + " adicionado à fila de espera.");
            }
        }
        */
        public void sentarCliente(Cliente cliente) {
            if (!filaDeEspera.isEmpty()) {
            cliente = filaDeEspera.peek(); // Obter o próximo cliente na fila de espera
           
            // Verifica se há uma mesa disponível para o cliente
            for (Mesa mesa : mesas) {
                if (mesa.isDisponivel(requisicao.getQuantidadeDePessoas())) {
                    mesa.ocuparMesa(cliente); // Ocupa a mesa
                    System.out.println("Cliente " + cliente.getNome() + " sentou-se à mesa.");    
                    cliente = filaDeEspera.remove(); //remover cliente da fila de espera                
                    return;
                }
            }
            System.out.println("Não foi possível alocar uma mesa para o cliente " + cliente.getNome() + ".");
        } else {
            System.out.println("Não há clientes na fila de espera.");
        }
    }    
    /* 
    
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
    
    */
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


    //aqui falta direcionar qual cliente sera removido da mesa
    public void removerClienteDaMesa(Cliente cliente) {
        for (Mesa mesa : mesas) {
            if (mesa.getCliente() != null && mesa.getCliente().equals(cliente)) {
                mesa.desocuparMesa(); //provavelmente nesse metodo aqui
                System.out.println("Cliente " + cliente.getNome() + " foi removido da mesa.");
                return;
            }
        }
        System.out.println("Cliente " + cliente.getNome() + " não está em nenhuma mesa.");
    }

    
}