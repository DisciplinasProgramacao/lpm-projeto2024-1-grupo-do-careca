import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Restaurante {

    //Lista de mesas disponíveis no restaurante, e Fila de espera para clientes que precisam de mesas.
    private List<Mesa> mesas;
    private Queue<Cliente> filaDeEspera;

    //Construtor vazio
    public Restaurante() {
        this.mesas = new ArrayList<>();
        this.filaDeEspera = new LinkedList<>();
    }

    //Adiciona uma mesa ao restaurante.
    public void addMesa(Mesa mesa) {
        this.mesas.add(mesa);
    }

    //Verifica se existe uma mesa disponível 
    public boolean temMesaDisponivel(int quant) {
        for (Mesa mesa : mesas) {
            if (mesa.isDisponivel(quant)) {
                return true;
            }
        }
        return false;
     
    //Adiciona um cliente à fila de espera.
    public void enviarClienteParaFilaDeEspera(Cliente cliente) {
        if(cliente.getQuantidadeDePessoas() > 8) {
            System.out.println("Quantidade acima do permitido");
         } 
         else
          {
            filaDeEspera.add(cliente);
         System.out.println("Cliente " + cliente.getNome() +
         " adicionado à fila de espera.");
         }
         }

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
    public Mesa acharCliente(String nome) {
        for (Mesa mesa : mesas) {
            if (mesa.clienteSentado().equals(nome)) {
                return mesa;
            }
        }
        return null;
    }
        }
    }
}

