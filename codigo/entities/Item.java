package codigo.entities;

public class Item {
    private final String descricao;
    private final double preco;
    private final int id;

    public Item(String descricao, double preco, int id) {
        this.descricao = descricao;
        this.preco = preco;
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPreco() {
        return preco;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return id + " - " + descricao + '\'' +
                " -  preco: R$ " + preco;
    }
}
