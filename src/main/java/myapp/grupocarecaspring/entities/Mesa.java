package src.main.java.myapp.grupocarecaspring.entities;

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
    private int IdMesa;
    
    @Column(name = "quantidade_cadeiras", nullable = false)
    private int quantidadeDeCadeiras;

    @Column(name = "mesa_ocupada", nullable = false)
    private boolean mesaOcupada;

    // remover requisicao daqui
    //private Requisicao requisicaoAtual;
    private Pedido pedido;

    public Mesa(int quantidadeDeCadeiras) {
        this.IdMesa = contador;
        this.quantidadeDeCadeiras = quantidadeDeCadeiras;
        this.mesaOcupada = false;
        //this.requisicaoAtual = null;
        this.pedido = null;
        contador++;
    }

    // metodos com problemas saem

    public int getQuantidadeDeCadeiras() {
        return quantidadeDeCadeiras;
    }

    public void setQuantidadeDeCadeiras(int quantidadeDeCadeiras) {
        this.quantidadeDeCadeiras = quantidadeDeCadeiras;
    }

    // public Requisicao getRequisicaoAtual() {
    //     return requisicaoAtual;
    // }

    // public void setRequisicaoAtual(Requisicao requisicaoAtual) {
    //     this.requisicaoAtual = requisicaoAtual;
    // }

    public int getIdMesa() {
        return IdMesa;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public boolean isMesaOcupada() {
        return mesaOcupada;
    }

    public void ocuparMesa() {
        mesaOcupada = true;
    }

    public void desocuparMesa() {
        mesaOcupada = false;
    }

    // @Override
    // public String toString() {
    //     return "Mesa: " + IdMesa + " - " + requisicaoAtual;
    // }

   
    

}