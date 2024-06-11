package codigo.entities;

public class Cliente {

    private static int idAutomatico = 1;
    private String nome;
    private int id;

    public Cliente(String nome) {
        this.nome = nome;
        this.id = idAutomatico;
        idAutomatico++;
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

    @Override
    public String toString() {
        return "Cliente : " + nome;
    }

    
   
}