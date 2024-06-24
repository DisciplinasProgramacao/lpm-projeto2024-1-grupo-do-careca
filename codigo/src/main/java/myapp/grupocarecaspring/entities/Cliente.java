package myapp.grupocarecaspring.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@Data
@NoArgsConstructor

@Entity
@Table(name = "clientes")
public class Cliente {

    private static int idAutomatico = 1;
    
    @Column(name = "nome_cliente", nullable = false, length = 100)
    private String nome;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private int id;

    public Cliente(String nome) {
        this.nome = nome;
        this.id = idAutomatico;
        idAutomatico++;
    }

    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Cliente : " + nome;
    }

}