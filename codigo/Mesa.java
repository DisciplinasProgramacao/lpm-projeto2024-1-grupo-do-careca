package codigo;

class Mesa {
    private int quantidadeDeCadeiras;
    private boolean mesaOcupada;
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

    public void ocuparMesa(Requisicao requisicao) {
        mesaOcupada = true;
        this.requisicao = requisicao;
    }

    public String clienteSentado() {
        return this.requisicao.getCliente().getNome();
    }

    public void desocuparMesa() {
        mesaOcupada = false;
    }

    public boolean isDisponivel(int quantidade) {
        return !mesaOcupada && quantidadeDeCadeiras >= quantidade;
    }

    public Requisicao getRequisicao() {
        return requisicao;
    }
}