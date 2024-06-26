package myapp.grupocarecaspring.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;

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
    private LocalDateTime chegada = LocalDateTime.now();

    @Column(name = "saida")
    private LocalDateTime saida = null;

    @Column(name = "numero_de_pessoas", nullable = false)
    private int numeroDePessoas;

    @OneToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @OneToOne
    @JoinColumn(name = "mesa_id", nullable = false)
    private Mesa mesa;

    @OneToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido  ;

    

    public Requisicao(Mesa mesa, Cliente cliente, int numeroDePessoas) {
        this.mesa = mesa;
        this.cliente = cliente;
        this.numeroDePessoas = numeroDePessoas;
    }

    // Adicione este método
    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public int getNumeroDePessoas() {
        return numeroDePessoas;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public LocalDateTime getChegada() {
        return chegada;
    }

    public LocalDateTime getSaida() {
        return saida;
    }

    public void encerrar() {
        this.saida = LocalDateTime.now();
    }

    public void adicionarItemAoPedido(Item item) {
        pedido.adicionarItem(item);
    }

    public String gerarRelatorio() {
        double valorTotal = pedido.calcularValorTotalComTaxa();
        double valorPorPessoa = pedido.calcularValorPorPessoa(numeroDePessoas);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return "Relatório da Requisição:\n" +
                "Cliente: " + cliente.getNome() + "\n" +
                "Quantidade de Pessoas: " + numeroDePessoas + "\n" +
                "Itens Pedidos:\n" + pedido.listarItens() +
                "Valor da Conta (com 10% de taxa): R$ " + String.format("%.2f", valorTotal) + "\n" +
                "Valor da Conta por Pessoa: R$ " + String.format("%.2f", valorPorPessoa) + "\n" +
                "Data de Chegada: " + chegada.format(formatter) + "\n" +
                "Horário de Saída: " + (saida != null ? saida.format(formatter) : "N/A") + "\n";
    }

    @Override
    public String toString() {
        return "Requisicao{" +
                "mesa=" + mesa +
                ", cliente=" + cliente +
                ", numeroDePessoas=" + numeroDePessoas +
                ", pedido=" + pedido +
                ", chegada=" + chegada +
                ", saida=" + saida +
                '}';
    }
}

