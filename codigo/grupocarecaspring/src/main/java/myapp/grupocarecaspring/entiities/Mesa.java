package myapp.grupocarecaspring.entiities;

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
@Table(name = "mesas")
public class Mesa {
    private static int contador = 1;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mesa")
    private int idMesa;
    
    @Column(name = "quantidade_cadeiras", nullable = false)
    private int quantidadeDeCadeiras;
    
    @Column(name = "mesa_ocupada", nullable = false)
    private boolean mesaOcupada;

    

    public Mesa(int quantidadeDeCadeiras) {
        this.idMesa = contador;
        this.quantidadeDeCadeiras = quantidadeDeCadeiras;
        this.mesaOcupada = false;
        contador++;
    }

    
}