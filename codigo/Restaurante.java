package codigo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class Restaurante {
    // apenas uma lista de mesas, ocupada ou nao será um estado

    private List<Mesa> mesas;
    private Queue<Requisicao> filaEspera;
    private Cardapio cardapio;

    public Restaurante(Cardapio cardapio) {
        mesas = new ArrayList<>();
        filaEspera = new LinkedList<>();
        this.cardapio = cardapio;
        inicializarMesas();
    }

    private void inicializarMesas() {
        for (int i = 0; i < 4; i++) {
            mesas.add(new Mesa(4));
        }
        for (int i = 0; i < 4; i++) {
            mesas.add(new Mesa(6));
        }
        for (int i = 0; i < 2; i++) {
            mesas.add(new Mesa(8));
        }
    }

    public Queue<Requisicao> getFilaEspera() {
        return new LinkedList<>(filaEspera);
    }

    public List<Mesa> getMesasOcupadas() {
        return mesas.stream()
                .filter(Mesa::isMesaOcupada)
                .collect(Collectors.toList());
    }

    public List<Mesa> getMesaLivre() {
        return mesas.stream()
                .filter(x -> x.isMesaOcupada() == false)
                .collect(Collectors.toList());
    }

    public boolean mesaExiste(int numeroMesa) {
        return encontrarMesaPorNumero(numeroMesa) != null;
    }

    public Mesa encontrarMesaPorNumero(int numeroMesa) {
        List<Mesa> mesasOcupadas = getMesasOcupadas();
        for (Mesa mesa : mesasOcupadas) {
            if (mesa.getIdMesa() == numeroMesa) {
                return mesa;
            }
        }
        return null;
    }

    private Mesa encontrarMesaAdequada(int numeroPessoas) {
        List<Mesa> mesasLivres = getMesaLivre();
        for (Mesa mesa : mesasLivres) {
            if (mesa.getQuantidadeDeCadeiras() >= numeroPessoas) {
                return mesa;
            }
        }
        return null;
    }

    public void adicionarRequisicao(Requisicao requisicao) {
        Mesa mesaAdequada = encontrarMesaAdequada(requisicao.getQuantidadeDePessoas());
        if (mesaAdequada != null) {
            alocarMesa(mesaAdequada, requisicao);
        } else {
            filaEspera.offer(requisicao);
        }
    }

    public boolean adicionarPedido(int numeroMesa, Pedido pedido) {
        Mesa mesa = encontrarMesaPorNumero(numeroMesa);
        if (mesa != null) {
            mesa.setPedido(pedido);
            return true;
        }
        return false;
    }

    private void alocarMesa(Mesa mesa, Requisicao requisicao) {
        mesa.ocuparMesa();
        mesa.setRequisicaoAtual(requisicao);
        requisicao.setMesa(mesa);
        mesa.ocuparMesa();
    }

    public void liberarMesa(Mesa mesa) {
        if (mesa.getRequisicaoAtual() != null) {
            mesa.getRequisicaoAtual().encerrarRequisicao();
            mesa.desocuparMesa();
            mesa.setRequisicaoAtual(null);
            mesa.desocuparMesa();

            Requisicao req = mesa.getRequisicaoAtual();
            if (req != null) {

                req.adicionarPedido(mesa.getPedido());
            }

            if (!filaEspera.isEmpty()) {
                Requisicao proximaRequisicao = filaEspera.poll();
                Mesa mesaAdequada = encontrarMesaAdequada(proximaRequisicao.getQuantidadeDePessoas());
                if (mesaAdequada != null) {
                    alocarMesa(mesaAdequada, proximaRequisicao);
                }
            }
        }
    }

    public List<Item> obterItensCardapio() {
        return cardapio.getItems();
    }

    public Item obterItemCardapio(int codigoItem) {
        return cardapio.getItem(codigoItem);
    }

    public void exibirMenu() {
        cardapio.exibirMenu();
    }

    public boolean verificarMesaOcupada(int numeroMesa) {
        Mesa mesa = encontrarMesaPorNumero(numeroMesa);
        return mesa != null && mesa.isMesaOcupada();
    }

    public boolean servirCliente(int numeroMesa, Pedido pedido) {
        Mesa mesa = encontrarMesaPorNumero(numeroMesa);
        if (mesa != null && mesa.isMesaOcupada()) {
            mesa.setPedido(pedido);
            return true;
        } else {
            return false;
        }
    }

    public Requisicao encerrarAtendimento(int indiceMesa) {
        Mesa mesaALiberar = encontrarMesaPorNumero(indiceMesa);
        Requisicao req = mesaALiberar.getRequisicaoAtual();
        liberarMesa(mesaALiberar);
        return req;

    }

}