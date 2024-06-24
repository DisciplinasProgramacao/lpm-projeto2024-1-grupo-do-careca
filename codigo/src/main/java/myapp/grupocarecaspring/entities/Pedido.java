package myapp.grupocarecaspring.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
@Table(name = "pedidos")
public class Pedido {

    private final double TAXA = 0.1;
    // BOMBA nao pode ser booleano
    private boolean menuFechado;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private int idPedido;

    @OneToMany
    @JoinColumn(name = "pedido_id")
    private List<Item> itemsEscolhidos;

    public Pedido(boolean menuFechado) {
        this.itemsEscolhidos = new ArrayList<>();
        this.menuFechado = menuFechado;
    }

    public List<Item> getItemsEscolhidos() {
        return itemsEscolhidos;
    }

    public void pedirItem(Item pedido) {
        itemsEscolhidos.add(pedido);
    }

    public boolean isMenuFechado() {
        return menuFechado;
    }
    
    public double valorAPagar() {
        double valorTotal = 0.0;

        for (Item item : itemsEscolhidos) {
            valorTotal += item.getPreco();
        }

        return valorTotal * (1 + TAXA);
    }

    public double calcularValorPorPessoa(int numeroDePessoas) {
        return valorAPagar() / numeroDePessoas;
    }

    public String itemFormatado(Item item) {
        return item.toString() + "\n";
    }

    public List<String> relatorioItens() {
        List<String> relatorio = new ArrayList<>();
        for (Item item : itemsEscolhidos) {
            relatorio.add(itemFormatado(item));
        }
        return relatorio;

    }
}
