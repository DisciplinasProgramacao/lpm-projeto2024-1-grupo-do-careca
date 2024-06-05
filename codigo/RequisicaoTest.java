package codigo;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDateTime;

public class RequisicaoTest {
    private Cliente cliente;
    private Mesa mesa;
    private Pedido pedido;

    @Before
    public void setUp() {
        cliente = new Cliente("João Silva");
        mesa = new Mesa(4);  // Supondo que a mesa tenha 4 cadeiras
        pedido = new Pedido(false);  // Supondo que o menu não está fechado
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
        Item item = new Item("Café", 5.0, 1);  // Supondo um item de nome "Café" com preço 5.0 e identificador 1
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
        Item item = new Item("Café", 5.0, 1);
        pedido.pedirItem(item);
        requisicao.adicionarPedido(pedido);
        requisicao.encerrarRequisicao();

        String relatorio = requisicao.relatorioAtendimento();
        assertTrue(relatorio.contains("Horário de Chegada:"));
        assertTrue(relatorio.contains("Cliente: João Silva"));
        assertTrue(relatorio.contains("Horário de Saída:"));
        assertTrue(relatorio.contains("Itens do Pedido:"));
        assertTrue(relatorio.contains("Total do Pedido: R$"));
        assertTrue(relatorio.contains("Total por Pessoa: R$"));
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
