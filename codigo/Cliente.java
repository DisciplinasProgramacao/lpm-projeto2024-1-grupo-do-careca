package codigo;

class Cliente {

    private String nome;
    private int id;

    private Pedido pedido;

    public Cliente(String nome, int id) {
        this.nome = nome;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // metodo para pedir items do cardapio
    public void pedirItemCardapio(Item[] pedidos) {
        for (int i = 0; i < pedidos.length; i++) {
            pedido.pedirItem(pedidos[i]);
        }

        for (Item i : pedidos) {
            System.out.println(i);
        }
    }

<<<<<<< HEAD
    // teste
=======
//   testando conflito de merge

>>>>>>> 2578d67e4fe0d10ab04417bdc68bd05060470da8
    @Override
    public String toString() {
        return "Cliente : " + nome;
    }

}