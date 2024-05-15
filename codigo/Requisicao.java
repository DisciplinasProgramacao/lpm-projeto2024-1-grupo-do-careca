package codigo;

import java.util.Date;
import java.util.List;
import java.util.Queue;

class Requisicao {
    private int quantidadeDePessoas;
    private Date chegada;
    private Date saida;
    private Cliente cliente;
    private Mesa mesa;
    private Queue<Requisicao> filaDeEspera;
    private List<Mesa> mesas;

    public Requisicao(int quantidadeDePessoas, Cliente cliente, Queue<Requisicao> filaDeEspera, List<Mesa> mesas) {
        this.chegada = new Date();
        this.quantidadeDePessoas = quantidadeDePessoas;
        this.cliente = cliente;
        this.filaDeEspera = filaDeEspera;
        this.mesa = (Mesa) mesas;
    }

    public int getQuantidadeDePessoas() {
        return quantidadeDePessoas;
    }

    public void setQuantidadeDePessoas(int quantidadeDePessoas) {
        this.quantidadeDePessoas = quantidadeDePessoas;
    }

    public Date getChegada() {
        return chegada;
    }

    public void setChegada(Date chegada) {
        this.chegada = chegada;
    }

    public Date getSaida() {
        return saida;
    }
    
    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public void setSaida(Date saida) {
        this.saida = saida;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void solicitarMesa() {
       // System.out.println(
        //        "Cliente " + cliente.getNome() + " solicitou uma mesa para " + quantidadeDePessoas + " pessoas.");
        // verificar se há mesa disponivel
    }

    

    public void fecharConta() {
        saida = new Date();
        setSaida(saida);
        //System.out.println("Relatorio do cliente: " + cliente.getNome() + " aqui vai o metodo com a conta");
        // desocupar a mesa
    }

    public void sentarCliente(Requisicao requisicao) {
        // validação da qtd de pessoas na requisicao para entrar ou nao na fila de
        // espera

        if (!filaDeEspera.isEmpty()) {
            Requisicao proxCliente = filaDeEspera.peek(); // Obter o próximo cliente na fila de espera

            // Verifica se há uma mesa disponível para o cliente
            for (Mesa mesa : mesas) {
                if (mesa.isDisponivel(proxCliente.getQuantidadeDePessoas())) {
                    mesa.ocuparMesa(proxCliente); // Ocupa a mesa
             //       System.out.println("Requisicao atendida: " + proxCliente);
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
                //System.out.println("Requisicao atendida: " + requisicao);
                // System.out.println("Cliente " + requisicao.getCliente().getNome() + "
                // sentou-se à mesa.");
                return;
            }
        }

        filaDeEspera.add(requisicao);
        //System.out
        //        .println("Não foi possível alocar uma mesa para o cliente " + requisicao.getCliente().getNome() + ".");

        //System.out.println("Cliente " + requisicao.getCliente().getNome() + " movido para a fila de espera");

    }

    

    public void removerClienteDaMesa(int id) {
        // Procura na fila de espera por uma requisição com o ID especificado
        for (Requisicao r : filaDeEspera) {
            if (r.getSaida().equals(id)) {
                r.fecharConta();
                return;
            }
        }

        // Se não encontrou na fila de espera, verifica nas mesas
        for (Requisicao r : filaDeEspera) {
            if (r.getCliente().getId() == id) {
                r.fecharConta();
                return;
            }
        }
    }
    
        // for (Mesa mesa : mesas) {
        //     Requisicao req = mesa.getRequisicao();
        //     if (req != null && req.getCliente().getId() == id) {
        //         mesa.desocuparMesa();
        //         System.out.println("Cliente " + req.getCliente().getNome() + " foi removido da mesa.");
        //         req.fecharConta();
        //         return;
        //     }
        // }
    }

