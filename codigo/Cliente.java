package codigo;

class Cliente {
    //talvez precise de um identificador aqui

    private String nome;
    private int id;   

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
    
}