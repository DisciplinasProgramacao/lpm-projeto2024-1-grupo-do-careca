package codigo.src.main.java.myapp.grupocarecaspring.entities;

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

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Cliente : " + nome;
    }

}