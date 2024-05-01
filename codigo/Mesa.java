package codigo;

class Mesa {
    private int quantidadeDeCadeiras;
    private boolean mesaOcupada;
    private Cliente cliente;
    private Requisicao requisicao;

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

    public void ocuparMesa(Requisicao requisicao) {
        mesaOcupada = true;
        this.requisicao = requisicao;
    }

    public void desocuparMesa() {
        mesaOcupada = false;
        this.requisicao = null;
    }

    public boolean isDisponivel(int quantidade) {
        return !mesaOcupada && quantidadeDeCadeiras >= quantidade;
    }

    public Requisicao getRequisicao() {
        return requisicao;
    }

    public void setRequisicao(Requisicao requisicao) {
        this.requisicao = requisicao;
    }
}