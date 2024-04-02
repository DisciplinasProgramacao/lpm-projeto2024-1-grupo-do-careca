package codigo;

import java.util.Date;

class Requisicao {
    private int quantidadeDePessoas;
    private Date chegada;
    private Date saida;
    private Cliente cliente;

    public Requisicao(int quantidadeDePessoas, Cliente cliente) {
        this.quantidadeDePessoas = quantidadeDePessoas;
        this.chegada = new Date();
        this.cliente = cliente;
    }
    public int getQuantidadeDePessoas() {
        return quantidadeDePessoas;
    }

    public void setQuantidadeDePessoas(int quantidadeDePessoas) {
        this.quantidadeDePessoas = quantidadeDePessoas;
    }

    public Date getChegada() {
        return chegada;
    }

    public void setChegada(Date chegada) {
        this.chegada = chegada;
    }

    public Date getSaida() {
        return saida;
    }

    public void setSaida(Date saida) {
        this.saida = saida;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


    public void solicitarMesa() {
        System.out.println("Cliente " + cliente.getNome() + " solicitou uma mesa para " + quantidadeDePessoas + " pessoas.");
    }

    public void sentarNaMesa() {
        System.out.println("Cliente " + cliente.getNome() + " sentou-se à mesa.");        
    }

    public void sairDaMesa() {
        saida = new Date();
        System.out.println("Cliente " + cliente.getNome() + " saiu da mesa.");
    }
}
