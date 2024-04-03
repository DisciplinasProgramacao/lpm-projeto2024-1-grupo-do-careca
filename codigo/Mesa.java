package codigo;

class Mesa {
    private int quantidadeDeCadeiras;
    private boolean mesaOcupada;
    private Cliente cliente;

    public Mesa(int quantidadeDeCadeiras) {
        this.quantidadeDeCadeiras = quantidadeDeCadeiras;
        this.mesaOcupada = false;
    }    

    public int getQuantidadeDeCadeiras() {
        return quantidadeDeCadeiras;
    }

    public void setQuantidadeDeCadeiras(int quantidadeDeCadeiras) {
        this.quantidadeDeCadeiras = quantidadeDeCadeiras;
    }

    public boolean isMesaOcupada() {
        return mesaOcupada;
    }

    public void setMesaOcupada(boolean mesaOcupada) {
        this.mesaOcupada = mesaOcupada;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public boolean cabemNaMesa(Cliente cliente) {
        return !mesaOcupada && quantidadeDeCadeiras >= cliente.getQuantidadeDePessoas();
    }

    public void ocuparMesa() {
        mesaOcupada = true;
    }    

    public void desocuparMesa() {
        mesaOcupada = false;
    }    

    

    //faz meio que a mesma coisa do cabemNaMesa mas deixei aqui por enquanto pq chamei esse metodo em outros locais

    //adicionei esse metodo para fazer funcionar o metodo temMesaDospinivel na claase restaurante
    public boolean isDisponivel(int quantidade) {
        return !mesaOcupada && quantidadeDeCadeiras >= quantidade;
    }
}
