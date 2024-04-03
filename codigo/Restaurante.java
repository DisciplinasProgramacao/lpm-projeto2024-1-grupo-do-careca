class Restaurante {

    //Lista de mesas disponíveis no restaurante, e Fila de espera para clientes que precisam de mesas.
    private List<Mesa> mesas;
    private Queue<Cliente> filaDeEspera;

    //Construtor vazio
    public Restaurante() {
        this.mesas = new ArrayList<>();
        this.filaDeEspera = new LinkedList<>();
    }

    //Adiciona uma mesa ao restaurante.
    public void addMesa(Mesa mesa) {
        this.mesas.add(mesa);
    }

    //Verifica se existe uma mesa disponível no restaurante com capacidade suficiente para o número de pessoas pedido.


    public boolean temMesaDisponivel(int quantidadeDePessoas) {
        for (Mesa mesa : mesas) {
            if (!mesa.isOcupada() && mesa.getCapacidade() >= quantidadeDePessoas) {
                return true;
            }
        }
        return false;
    }
     
    //Adiciona um cliente à fila de espera.

    public void enviarClienteParaFilaDeEspera(Cliente cliente) {
        this.filaDeEspera.add(cliente);
    }

    //Senta o cliente no início da fila de espera em uma mesa disponível.

    public void sentarClienteDaFilaDeEspera() {
        if (!filaDeEspera.isEmpty()) {
            Cliente cliente = filaDeEspera.poll();
            alocarCliente(cliente);
        }
    }
    
     //Aloca um cliente em uma mesa disponível.

    public void alocarCliente(Cliente cliente) {
        for (Mesa mesa : mesas) {
            if (!mesa.isOcupada() && mesa.getCapacidade() >= cliente.getQuantidadeDePessoas()) {
                mesa.setOcupada(true);
                break;
            }
        }
    }
}
