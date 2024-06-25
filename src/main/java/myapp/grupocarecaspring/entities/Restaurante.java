package src.main.java.myapp.grupocarecaspring.entities;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class Restaurante {

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
                .filter(mesa -> !mesa.isMesaOcupada())
                .collect(Collectors.toList());
    }

    public Mesa encontrarMesaPorNumero(int numeroMesa) {
        return mesas.stream()
                .filter(mesa -> mesa.getIdMesa() == numeroMesa)
                .findFirst()
                .orElse(null);
    }

    private Mesa encontrarMesaAdequada(int numeroPessoas) {
        return getMesaLivre().stream()
                .filter(mesa -> mesa.getQuantidadeDeCadeiras() >= numeroPessoas)
                .findFirst()
                .orElse(null);
    }

    public void adicionarRequisicao(Requisicao requisicao) {
        Mesa mesaAdequada = encontrarMesaAdequada(requisicao.getQuantidadeDePessoas());
        if (mesaAdequada != null) {
            alocarMesa(mesaAdequada, requisicao);
        } else {
            requisicao.setMesa(null);
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
        requisicao.setMesa(mesa);
    }

    public void liberarMesa(Mesa mesa) {
        if (!mesa.isMesaOcupada()) {
            System.out.println("A mesa selecionada não está ocupada.");
            return;
        }

        Pedido pedido = mesa.getPedido();
        if (pedido == null) {
            System.out.println("Não há pedido associado a esta mesa.");
            return;
        }

        mesa.desocuparMesa();
        mesa.setPedido(null);

        if (!filaEspera.isEmpty()) {
            Requisicao proximaRequisicao = filaEspera.poll();
            Mesa mesaAdequada = encontrarMesaAdequada(proximaRequisicao.getQuantidadeDePessoas());
            if (mesaAdequada != null) {
                alocarMesa(mesaAdequada, proximaRequisicao);
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
        if (mesaALiberar == null || !mesaALiberar.isMesaOcupada()) {
            System.out.println("Mesa não encontrada ou não está ocupada.");
            return null;
        }

        Pedido pedido = mesaALiberar.getPedido();
        liberarMesa(mesaALiberar);

        Requisicao req = new Requisicao(mesaALiberar.getQuantidadeDeCadeiras(), new Cliente(""));

        if (pedido != null) {
            req.adicionarPedido(pedido);
        }

        return req;
    }

    public void adicionarPedidoMenuFechado(int numeroMesa, MenuFechado menuFechado) {
        Mesa mesa = encontrarMesaPorNumero(numeroMesa);
        if (mesa == null || !mesa.isMesaOcupada()) {
            System.out.println("Mesa não encontrada ou não está ocupada.");
            return;
        }

        mesa.getPedido().pedirItem(menuFechado);
    }

    public Requisicao obterRequisicaoPorMesa(int numeroMesa) {
        for (Requisicao requisicao : filaEspera) {
            if (requisicao.getMesa() != null && requisicao.getMesa().getIdMesa() == numeroMesa) {
                return requisicao;
            }
        }
        return null;
    }
}


