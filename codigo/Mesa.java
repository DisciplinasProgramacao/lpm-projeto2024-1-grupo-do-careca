package codigo;

class Mesa {
    private int quantidadeDeCadeiras;
    private boolean mesaOcupada;

    public Mesa(int quantidadeDeCadeiras) {
        this.quantidadeDeCadeiras = quantidadeDeCadeiras;
        this.mesaOcupada = false;
    }

    public boolean cabemNaMesa(int quantidade) {
        return !mesaOcupada && quantidadeDeCadeiras >= quantidade;
    }

    public void ocuparMesa() {
        mesaOcupada = true;
    }

    //adicionei esse metodo para fazer funcionar o metodo temMesaDospinivel na claase restaurante
    public boolean isDisponivel(int quantidade) {
        return !mesaOcupada && quantidadeDeCadeiras >= quantidade;
    }
}
