package codigo;

public class Mesa {
    public class Mesa {
    private int quantidadeDeCadeiras;
    private boolean mesaOcupada;

    public Mesa(int quantidadeDeCadeiras) {
        this.quantidadeDeCadeiras = quantidadeDeCadeiras;
        this.mesaOcupada = false;
    }

    public boolean cabemNaMesa(int quantidade) { //O método cabemNaMesa(int quantidade) verifica se a quantidade de pessoas cabe na mesa e retorna um booleano.
        return quantidade <= quantidadeDeCadeiras;
    } 

    public void ocuparMesa() { //O método ocuparMesa() marca a mesa como ocupada.
        this.mesaOcupada = true;
    }
}
}
