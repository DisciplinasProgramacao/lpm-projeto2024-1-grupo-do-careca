package myapp.grupocarecaspring.entities;

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
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data

@Entity
@Table(name = "pedidos")
public class Pedido {
    private final double TAXA = 0.1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Long idPedido;

    @OneToMany(mappedBy = "pedido")
    private final List<Item> itens;


    public Pedido() {
        this.itens = new ArrayList<>();
    }

    public void adicionarItem(Item item) {
        itens.add(item);
    }

    public double calcularValorTotal() {
        return itens.stream()
                .mapToDouble(Item::getPreco)
                .sum();
    }

    public double calcularValorTotalComTaxa() {
        double valorTotal = calcularValorTotal();
        return valorTotal + (valorTotal * 0.10);
    }

    public double calcularValorPorPessoa(int numeroDePessoas) {
        return calcularValorTotalComTaxa() / numeroDePessoas;
    }

    public String listarItens() {
        StringBuilder builder = new StringBuilder();
        itens.forEach(item -> {
            builder.append(item.getDescricao()).append(" - R$ ").append(String.format("%.2f", item.getPreco()))
                    .append("\n");
        });
        return builder.toString();
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "itens=" + itens +
                '}';
    }
}