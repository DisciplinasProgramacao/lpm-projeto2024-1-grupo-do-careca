package myapp.grupocarecaspring.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor


@Entity
@Table(name = "itens")
public class Item {

    @Column(name = "nome_item", nullable = false, length = 100)
    private String nome;

    @Column(name = "preco_item", nullable = false)
    private double preco;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_item")
    private int identificador;

    
    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    public Item(String nome, double preco, int identificador) {
        this.nome = nome;
        this.preco = preco;
        this.identificador = identificador;
    }

    // Validação do valor

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }
    
    public int getIdentificador() {
        return identificador;
    }

    @Override
    public String toString() {
        return "Item: " + getNome() + ", Preço: R$" + getPreco();
    }

}
