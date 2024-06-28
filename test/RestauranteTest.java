package codigo.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import src.main.java.myapp.grupocarecaspring.entities.*;

import java.util.List;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

public class RestauranteTest {

    private Restaurante restaurante;
    private Cardapio cardapio;
    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        cardapio = new Cardapio();
        restaurante = new Restaurante(cardapio);
        cliente = new Cliente("João");
    }

    @Test
    public void testInicializarMesas() {
        List<Mesa> mesas = restaurante.getMesaLivre();
        assertEquals(10, mesas.size());
    }

    @Test
    public void testAdicionarRequisicaoComMesaDisponivel() {
        Requisicao requisicao = new Requisicao(4, cliente);
        restaurante.adicionarRequisicao(requisicao);

        assertNotNull(requisicao.getMesa());
        assertTrue(requisicao.getMesa().isMesaOcupada());
        assertTrue(restaurante.getFilaEspera().isEmpty());
    }

    @Test
    public void testAdicionarRequisicaoSemMesaDisponivel() {
        for (int i = 0; i < 10; i++) {
            Requisicao requisicao = new Requisicao(4, new Cliente("Cliente " + i));
            restaurante.adicionarRequisicao(requisicao);
        }

        Requisicao novaRequisicao = new Requisicao(4, cliente);
        restaurante.adicionarRequisicao(novaRequisicao);

        assertNull(novaRequisicao.getMesa());
        Queue<Requisicao> filaEspera = restaurante.getFilaEspera();
        assertEquals(1, filaEspera.size());
        assertEquals(novaRequisicao, filaEspera.peek());
    }

    @Test
    public void testAdicionarPedido() {
        Requisicao requisicao = new Requisicao(4, cliente);
        restaurante.adicionarRequisicao(requisicao);
        Mesa mesa = requisicao.getMesa();
        Pedido pedido = new Pedido(false);
        pedido.pedirItem(new Item("Falafel Assado", 20.00, 2));

        boolean result = restaurante.adicionarPedido(mesa.getIdMesa(), pedido);

        assertTrue(result);
        assertEquals(pedido, mesa.getPedido());
    }

    @Test
    public void testAdicionarPedidoMesaInexistente() {
        Pedido pedido = new Pedido(false);
        boolean result = restaurante.adicionarPedido(999, pedido);

        assertFalse(result);
    }

    @Test
    public void testLiberarMesa() {
        Requisicao requisicao = new Requisicao(4, cliente);
        restaurante.adicionarRequisicao(requisicao);
        Mesa mesa = requisicao.getMesa();
        Pedido pedido = new Pedido(false);
        pedido.pedirItem(new Item("Falafel Assado", 20.00, 2));
        mesa.setPedido(pedido);

        restaurante.liberarMesa(mesa);

        assertFalse(mesa.isMesaOcupada());
        assertNull(mesa.getPedido());
    }

    @Test
    public void testObterItensCardapio() {
        List<Item> itens = restaurante.obterItensCardapio();
        assertEquals(11, itens.size());
    }

    @Test
    public void testObterItemCardapio() {
        Item item = restaurante.obterItemCardapio(2);
        assertNotNull(item);
        assertEquals("Falafel Assado", item.getNome());
    }

    @Test
    public void testVerificarMesaOcupada() {
        Requisicao requisicao = new Requisicao(4, cliente);
        restaurante.adicionarRequisicao(requisicao);
        Mesa mesa = requisicao.getMesa();

        assertTrue(restaurante.verificarMesaOcupada(mesa.getIdMesa()));
    }

    @Test
    public void testVerificarMesaNaoOcupada() {
        Mesa mesa = restaurante.getMesaLivre().get(0);
        assertFalse(restaurante.verificarMesaOcupada(mesa.getIdMesa()));
    }

    @Test
    public void testServirCliente() {
        Requisicao requisicao = new Requisicao(4, cliente);
        restaurante.adicionarRequisicao(requisicao);
        Mesa mesa = requisicao.getMesa();
        Pedido pedido = new Pedido(false);

        boolean result = restaurante.servirCliente(mesa.getIdMesa(), pedido);

        assertTrue(result);
        assertEquals(pedido, mesa.getPedido());
    }

    @Test
    public void testEncerrarAtendimento() {
        Requisicao requisicao = new Requisicao(4, cliente);
        restaurante.adicionarRequisicao(requisicao);
        Mesa mesa = requisicao.getMesa();
        Pedido pedido = new Pedido(false);
        pedido.pedirItem(new Item("Falafel Assado", 20.00, 2));
        mesa.setPedido(pedido);

        Requisicao result = restaurante.encerrarAtendimento(mesa.getIdMesa());

        assertNotNull(result);
        assertFalse(mesa.isMesaOcupada());
        assertNull(mesa.getPedido());
    }

    @Test
    public void testAdicionarPedidoMenuFechado() {
        Requisicao requisicao = new Requisicao(4, cliente);
        restaurante.adicionarRequisicao(requisicao);
        Mesa mesa = requisicao.getMesa();
        Pedido pedido = new Pedido(false);
        mesa.setPedido(pedido);
        MenuFechado menuFechado = new MenuFechado("Menu Especial", 50.00, 1, "Strogonoff de cogumelos", List.of("Cerveja Vegana"));

        restaurante.adicionarPedidoMenuFechado(mesa.getIdMesa(), menuFechado);

        assertTrue(mesa.getPedido().getItemsEscolhidos().contains(menuFechado));
    }

    @Test
    public void testObterRequisicaoPorMesa() {
        Requisicao requisicao = new Requisicao(4, cliente);
        restaurante.adicionarRequisicao(requisicao);
        Mesa mesa = requisicao.getMesa();
        Requisicao result = restaurante.obterRequisicaoPorMesa(mesa.getIdMesa());

        assertEquals(requisicao, result);
    }

    @Test
    public void testObterRequisicaoPorMesaNaoEncontrada() {
        Requisicao result = restaurante.obterRequisicaoPorMesa(999);
        assertNull(result);
    }
}
