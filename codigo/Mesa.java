package codigo;

class Mesa {
    private int quantidadeDeCadeiras;
    private boolean mesaOcupada;
<<<<<<< HEAD
    private Cliente cliente;
=======
>>>>>>> c089e09dee520e9dda9c67d3b012e8fca67ef8df
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
    
   
    public Cliente getCliente(){
        return cliente;
    }

    public void setCliente(Cliente cliente){
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
<<<<<<< HEAD

    public void setRequisicao(Requisicao requisicao) {
        this.requisicao = requisicao;
    }
=======
>>>>>>> c089e09dee520e9dda9c67d3b012e8fca67ef8df
}