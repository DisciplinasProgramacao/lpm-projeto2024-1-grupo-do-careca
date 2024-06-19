package myapp.grupocarecaspring.entiities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@Data
@NoArgsConstructor

@Entity
@Table(name = "itens")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_item")
    private int idItem;
    
    @Column(name = "nome_item", nullable = false, length = 100)
    private String nome;
    
    @Column(name = "preco_item", nullable = false)
    private double preco;

    private int identificador;


    public Item(String nome, double preco, int identificador) {
        this.nome = nome;
        this.preco = preco;
        this.identificador = identificador;
    }

}
