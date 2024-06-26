package codigo.entities;

public class Cliente {
    private final int id;
    private final String nome;

    public Cliente(int id, String nome) {
        try {
            validarId(id);
            validarNome(nome);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao criar cliente: " + e.getMessage());            
            throw e;         }

        this.id = id;
        this.nome = nome;
    }

    private void validarId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("ID não pode ser menor do que 0.");
        }
    }

    private void validarNome(String nome) {
        if (nome == null || nome.length() == 0) {
            throw new IllegalArgumentException("Informe um nome válido.");
        }
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "Cliente= - " +
                "id: " + id +
                ", nome='" + nome + '\'' +
                ' ';
    }
}
