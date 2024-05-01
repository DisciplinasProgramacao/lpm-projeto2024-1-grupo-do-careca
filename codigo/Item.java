package codigo;

public class Item {

    private String nome;
    private double preco;
    private int identificador;

    public Item(String nome, double preco, int identificador) {
        this.nome = nome;
        this.preco = preco;
        this.identificador = identificador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

}
