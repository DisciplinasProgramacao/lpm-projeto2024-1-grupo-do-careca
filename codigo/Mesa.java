package codigo;

public class Mesa {
    private int quantidadeDeCadeiras;
    private boolean mesaOcupada;

    public Mesa(int quantidadeDeCadeiras) {
        if (quantidadeDeCadeiras != 4 && quantidadeDeCadeiras != 6 && quantidadeDeCadeiras != 8) {
            throw new IllegalArgumentException("A quantidade de cadeiras deve ser 4, 6 ou 8.");
        }
        this.quantidadeDeCadeiras = quantidadeDeCadeiras;
        this.mesaOcupada = false;
    }

    public boolean cabemNaMesa(int quantidade) {
        return !mesaOcupada && quantidade <= quantidadeDeCadeiras;
    }

    public void ocuparMesa() {
        this.mesaOcupada = true;
    }

    public void desocuparMesa() {
        this.mesaOcupada = false;
    }
}

