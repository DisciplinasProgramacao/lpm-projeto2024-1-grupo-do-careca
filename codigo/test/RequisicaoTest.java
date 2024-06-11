package codigo;

import org.junit.Before;
import org.junit.Test;

import codigo.entities.Cliente;
import codigo.entities.Item;
import codigo.entities.Mesa;
import codigo.entities.Pedido;
import codigo.entities.Requisicao;

import static org.junit.Assert.*;

public class RequisicaoTest {
    private Cliente cliente;
    private Mesa mesa;
    private Pedido pedido;

    @Before
    public void setUp() {
        cliente = new Cliente("João Silva");
        mesa = new Mesa(4); // Supondo que a mesa tenha 4 cadeiras
        pedido = new Pedido(false); // Supondo que o menu não está fechado
    }

    @Test
    public void testCriacaoRequisicao() {
        Requisicao requisicao = new Requisicao(4, cliente);
        assertEquals(4, requisicao.getQuantidadeDePessoas());
        assertEquals(cliente, requisicao.getCliente());
        assertNotNull(requisicao.getChegada());
    }

    @Test
    public void testAdicionarPedido() {
        Requisicao requisicao = new Requisicao(4, cliente);
        Item item = new Item("Café", 5.0, 1); // Supondo um item de nome "Café" com preço 5.0 e identificador 1
        pedido.pedirItem(item);
        requisicao.adicionarPedido(pedido);
        assertEquals(1, requisicao.getPedidos().size());
        assertEquals(pedido, requisicao.getPedidos().get(0));
    }

    @Test
    public void testEncerrarRequisicao() {
        Requisicao requisicao = new Requisicao(4, cliente);
        requisicao.encerrarRequisicao();
        assertNotNull(requisicao.getSaida());
    }

    @Test
    public void testRelatorioAtendimento() {
        Requisicao requisicao = new Requisicao(4, cliente);
        requisicao.setMesa(mesa);
        Item item = new Item("Copo de Suco", 7.0, 1);
        pedido.pedirItem(item);
        requisicao.adicionarPedido(pedido);
        requisicao.encerrarRequisicao();

        mesa.setPedido(pedido);
        pedido.valorAPagar();
        pedido.calcularValorPorPessoa(4);

        String relatorio = requisicao.relatorioAtendimento();
        assertTrue(relatorio.contains("Horário de Chegada:"));
        assertTrue(relatorio.contains("Cliente: João Silva"));
        assertTrue(relatorio.contains("Horário de Saída:"));
        assertTrue(relatorio.contains("Itens do Pedido:\n"));
        assertTrue(relatorio.contains("Total do Pedido: R$ 7.7"));
        assertTrue(relatorio.contains("Total por Pessoa: R$ 1,93"));

    }

    @Test
    public void calcularValorDoPedido() {
    }

    @Test
    public void testQuantidadeDePessoasInvalida() {
        try {
            new Requisicao(9, cliente);
            fail("Esperava exceção para quantidade de pessoas maior que 8.");
        } catch (IllegalArgumentException e) {
            assertEquals("A quantidade de pessoas não pode ser maior que 8.", e.getMessage());
        }
    }
}
