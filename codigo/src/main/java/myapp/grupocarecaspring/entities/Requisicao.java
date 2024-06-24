package myapp.grupocarecaspring.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor

@Entity
@Table(name = "requisicoes")
public class Requisicao {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_requisicao")
    private int id;
    
    @Column(name = "chegada", nullable = false)
    private LocalDateTime chegada;

    @Column(name = "saida")
    private LocalDateTime saida;

    @Column(name = "quantidade_pessoas", nullable = false)
    private int quantidadeDePessoas;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @OneToOne
    @JoinColumn(name = "mesa_id", nullable = false)
    private Mesa mesa;

    @OneToMany
    @JoinColumn(name = "requisicao_id")
    private List<Pedido> pedidos;

    public Requisicao(int quantidadeDePessoas, Cliente cliente) {
        if (quantidadeDePessoas > 8) {
            throw new IllegalArgumentException("A quantidade de pessoas não pode ser maior que 8.");
        }

        if (quantidadeDePessoas <= 0) {
            throw new IllegalArgumentException("A quantidade de pessoas deve ser maior que 0.");
        }
        this.quantidadeDePessoas = quantidadeDePessoas;
        this.chegada = LocalDateTime.now();
        this.cliente = cliente;
        this.pedidos = new ArrayList<>();
    }

    public int getQuantidadeDePessoas() {
        return quantidadeDePessoas;
    }

    //talvez esse metodo nem deveria existir 
    public void setQuantidadeDePessoas(int quantidadeDePessoas) {
        if (quantidadeDePessoas > 8) {
            throw new IllegalArgumentException("A quantidade de pessoas não pode ser maior que 8.");
        }
        if (quantidadeDePessoas <= 0) {
            throw new IllegalArgumentException("A quantidade de pessoas deve ser maior que 0.");
        }
        this.quantidadeDePessoas = quantidadeDePessoas;
    }

    public LocalDateTime getChegada() {
        return chegada;
    }

    public void setChegada(LocalDateTime chegada) {
        this.chegada = chegada;
    }

    public LocalDateTime getSaida() {
        return saida;
    }

    public void setMesa(Mesa mesa) {
        if (mesa == null) {
            throw new IllegalArgumentException("Mesa não pode ser nula.");
        }
        this.mesa = mesa;
    }

    public void setSaida(LocalDateTime saida) {
        this.saida = saida;
    }

    public Cliente getCliente() {
        return cliente;
    }

    //talvez outro metodo desnecessário
    public void setCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não pode ser nulo.");
        }
        this.cliente = cliente;
    }

    public Mesa getMesa() {
        return mesa;
    }

    // requisicao só tem um pedido e nao uma lista de pedidos. Este metodo vai adc
    // um item nesse pedido
    public void adicionarPedido(Pedido pedido) {
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não pode ser nulo.");
        }
        pedidos.add(pedido);
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void encerrarRequisicao() {
        if (pedidos.isEmpty()) {
            throw new IllegalStateException("Não é possível encerrar a requisição sem pedidos.");
        }
        saida = LocalDateTime.now();
    }

    public String relatorioAtendimento() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        StringBuilder sb = new StringBuilder();
        sb.append("Horário de Chegada: ").append(chegada.format(formatter)).append("\n");
        sb.append("Cliente: ").append(cliente.getNome()).append("\n");
        sb.append("Horário de Saída: ").append(saida.format(formatter)).append("\n");

        //
        sb.append(pedido.relatorioItens());

            sb.append("Total por Pessoa: R$ ").append(String.format("%.2f", pedido.calcularValorPorPessoa())).append("\n");
            return sb.toString();
        }

    @Override
    public String toString() {
        return cliente + "";
    }

  
}
