package codigo;

class Mesa {

    private int quantidadeDeCadeiras;
    private boolean mesaOcupada;  

    private Requisicao requisicaoAtual;


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

    public Requisicao getRequisicaoAtual() {
        return requisicaoAtual;
    }

    public void setRequisicaoAtual(Requisicao requisicaoAtual) {
        this.requisicaoAtual = requisicaoAtual;
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
 
}