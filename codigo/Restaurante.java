package codigo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Restaurante {
    private List<Mesa> mesasDisponiveis;
    private List<Mesa> mesasOcupadas;
    private Queue<Requisicao> filaEspera;
    
    public Restaurante() {
        mesasDisponiveis = new ArrayList<>();
        mesasOcupadas = new ArrayList<>();
        filaEspera = new LinkedList<>();
        inicializarMesas();
    }
    
    private void inicializarMesas() {   
        for (int i = 0; i < 4; i++) {
            mesasDisponiveis.add(new Mesa(4));
        }
        for (int i = 0; i < 4; i++) {
            mesasDisponiveis.add(new Mesa(6));
        }
        for (int i = 0; i < 2; i++) {
            mesasDisponiveis.add(new Mesa(8));
        }
    }

    public Queue<Requisicao> getFilaEspera() {
        return new LinkedList<>(filaEspera);
    }

    public List<Mesa> getMesasOcupadas() {
        return new ArrayList<>(mesasOcupadas);
    }
    
    public void adicionarRequisicao(Requisicao requisicao) {
        Mesa mesaAdequada = encontrarMesaAdequada(requisicao.getQuantidadeDePessoas());
        if (mesaAdequada != null) {
            alocarMesa(mesaAdequada, requisicao);
        } else {
            filaEspera.offer(requisicao);
        }
    }
    
    private Mesa encontrarMesaAdequada(int numeroPessoas) {
        for (Mesa mesa : mesasDisponiveis) {
            if (mesa.getQuantidadeDeCadeiras() >= numeroPessoas) {
                return mesa;
            }
        }
        return null;
    }
    
    private void alocarMesa(Mesa mesa, Requisicao requisicao) {
        mesa.ocuparMesa();
        mesa.setRequisicaoAtual(requisicao);
        requisicao.setMesa(mesa);
        mesasDisponiveis.remove(mesa);
        mesasOcupadas.add(mesa);
    }

    public void liberarMesa(Mesa mesa) {
        if (mesa.getRequisicaoAtual() != null) {
            mesa.getRequisicaoAtual().encerrarRequisicao();
            mesa.desocuparMesa();
            mesa.setRequisicaoAtual(null);
            mesasOcupadas.remove(mesa);
            mesasDisponiveis.add(mesa);

            if (!filaEspera.isEmpty()) {
                Requisicao proximaRequisicao = filaEspera.poll();
                Mesa mesaAdequada = encontrarMesaAdequada(proximaRequisicao.getQuantidadeDePessoas());
                if (mesaAdequada != null) {
                    alocarMesa(mesaAdequada, proximaRequisicao);
                }
            }
        }
    }
   
}