package codigo;

class Mesa {
    private int quantidadeDeCadeiras;
    private boolean mesaOcupada;

    public Mesa(int quantidadeDeCadeiras) {
        this.quantidadeDeCadeiras = quantidadeDeCadeiras;
        this.mesaOcupada = false;
    }

    public boolean cabemNaMesa(Cliente cliente) {
        return !mesaOcupada && quantidadeDeCadeiras >= cliente.getQuantidadeDePessoas();
    }

    public void ocuparMesa() {
        mesaOcupada = true;
    }    

    //faz meio que a mesma coisa do cabemNaMesa mas deixei aqui por enquanto pq chamei esse metodo em outros locais

    //adicionei esse metodo para fazer funcionar o metodo temMesaDospinivel na claase restaurante
    public boolean isDisponivel(int quantidade) {
        return !mesaOcupada && quantidadeDeCadeiras >= quantidade;
    }
}
