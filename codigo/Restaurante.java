class Restaurante {
    private List<Mesa> mesas;
    private Queue<Cliente> filaDeEspera;

    public Restaurante() {
        this.mesas = new ArrayList<>();
        this.filaDeEspera = new LinkedList<>();
    }

    public void addMesa(Mesa mesa) {
        this.mesas.add(mesa);
    }

    public boolean temMesaDisponivel(int quantidadeDePessoas) {
        for (Mesa mesa : mesas) {
            if (!mesa.isOcupada() && mesa.getCapacidade() >= quantidadeDePessoas) {
                return true;
            }
        }
        return false;
    }

    public void enviarClienteParaFilaDeEspera(Cliente cliente) {
        this.filaDeEspera.add(cliente);
    }

    public void sentarClienteDaFilaDeEspera() {
        if (!filaDeEspera.isEmpty()) {
            Cliente cliente = filaDeEspera.poll();
            alocarCliente(cliente);
        }
    }

    public void alocarCliente(Cliente cliente) {
        for (Mesa mesa : mesas) {
            if (!mesa.isOcupada() && mesa.getCapacidade() >= cliente.getQuantidadeDePessoas()) {
                mesa.setOcupada(true);
                break;
            }
        }
    }
}
