package myapp.grupocarecaspring.entiities;

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
    private List<Pedido> pedidos = new ArrayList<>();


    public Requisicao(int quantidadeDePessoas, Cliente cliente) {
        if (quantidadeDePessoas > 8) {
            throw new IllegalArgumentException("A quantidade de pessoas não pode ser maior que 8.");
        }
        this.quantidadeDePessoas = quantidadeDePessoas;
        this.chegada = LocalDateTime.now();
        this.cliente = cliente;
    }
}
