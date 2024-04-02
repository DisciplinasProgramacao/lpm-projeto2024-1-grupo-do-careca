package codigo;

class Cliente {
    private String nome;
    private int quantidadeDePessoas;

    public Cliente(String nome, int quantidadeDePessoas) {
        this.nome = nome;
        this.quantidadeDePessoas = quantidadeDePessoas;

    }    
    
    public String getNome() {
        return nome;
    }    
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidadeDePessoas() {
        return quantidadeDePessoas;
    }

    public boolean cabemNaMesa(int quantidadeDeCadeiras) {
        // só cabe na mesa se tiver mais cadeira do que cliente
        return quantidadeDeCadeiras > 0;
    }

    
}
