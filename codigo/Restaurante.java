package codigo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Restaurante {
    private List<Mesa> mesas;
    private Queue<Requisicao> filaDeEspera;
    private Cardapio cardapio;

    private Requisicao requisicao;

    public Restaurante() {
        mesas = new ArrayList<>();
        filaDeEspera = new LinkedList<>();
        cardapio = new Cardapio();

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

    public String exibirCardapio() {
        return cardapio.toString();
    }


    public String listarClientesNaFilaDeEspera() {
        String resposta = "Não há clientes na fila de espera";
        if (!filaDeEspera.isEmpty()) {
           resposta = ("Clientes na fila de espera: ");
            for (Requisicao c : filaDeEspera) {
                resposta += (" - " + c.toString());
            }
        }
        return resposta;
    }        



    public Cliente atenderCliente(int id) {
        for (Mesa mesa : mesas) {
            Requisicao req = mesa.getRequisicao();
            if (req != null && req.getCliente().getId() == id) {

                System.out.println("Atendendo o cliente " + req.getCliente().getNome() + ".");

                return req.getCliente();
            }
        }
    }

    public void adicionarClienteNaFilaDeEspera(Requisicao requisicao) {
        filaDeEspera.add(requisicao);
    }

    public void sentarCliente(Requisicao requisicao2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sentarCliente'");
    }

    public void adicionarItem(Requisicao requisicao2, Item item) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'adicionarItem'");
    }


}
