package codigo;

import java.util.Date;

class Requisicao {
    private int quantidadeDePessoas;
    private Date chegada;
    private Date saida;
    private Cliente cliente;

    public Requisicao(int quantidadeDePessoas, Cliente cliente) {
        this.chegada = new Date();
        this.quantidadeDePessoas = quantidadeDePessoas;
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
        //verificar se há mesa disponivel
    }


    //sentraNaMesa   e sairDaMesa devem estar na classe restaurante?
    //sentar na mesa e sair na mesa devem manipular a lista de mesas e fila de clientes
    public void sentarNaMesa() {
        System.out.println("Cliente " + cliente.getNome() + " sentou-se à mesa.");  
        //ocupar uma mesa      
    }

    public void sairDaMesa() {
        saida = new Date();
        setSaida(saida);
        System.out.println("Cliente " + cliente.getNome() + " saiu da mesa.");
        //desocupar a mesa
    }
}