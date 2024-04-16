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

    public void ocuparMesa(Cliente cliente) {
        mesaOcupada = true;
        this.cliente = cliente;
    }    

    public void desocuparMesa() {
        mesaOcupada = false;
    }     

    public boolean isDisponivel(int quantidade) {
        return !mesaOcupada && quantidadeDeCadeiras >= quantidade;
    }
}