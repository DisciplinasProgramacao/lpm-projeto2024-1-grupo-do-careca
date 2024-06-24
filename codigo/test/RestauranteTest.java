import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import codigo.src.main.java.myapp.grupocarecaspring.entities.*;

import java.util.List;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

public class RestauranteTest {

    private Restaurante restaurante;
    private Cardapio cardapio;

    @BeforeEach
    public void setUp() {
        cardapio = new Cardapio();
        cardapio.adicionarItem(new Item(1, "Hamburger", 10.00));
        cardapio.adicionarItem(new Item(2, "Pizza", 20.00));
        cardapio.adicionarItem(new Item(3, "Salada", 5.00));
        restaurante = new Restaurante(cardapio);
    }

    @Test
    public void testAdicionarRequisicao_ComMesaDisponivel() {
        Cliente cliente = new Cliente("Joao");
        Requisicao requisicao = new Requisicao(cliente, 4);
        restaurante.adicionarRequisicao(requisicao);

        List<Mesa> mesasOcupadas = restaurante.getMesasOcupadas();
        assertEquals(1, mesasOcupadas.size());
        assertTrue(mesasOcupadas.get(0).isMesaOcupada());
        assertEquals(requisicao, mesasOcupadas.get(0).getRequisicaoAtual());
    }

    @Test
    public void testAdicionarRequisicao_SemMesaDisponivel() {
        Cliente cliente = new Cliente("Jane Doe");
        Requisicao requisicao = new Requisicao(cliente, 10);
        restaurante.adicionarRequisicao(requisicao);

        Queue<Requisicao> filaEspera = restaurante.getFilaEspera();
        assertEquals(1, filaEspera.size());
        assertEquals(requisicao, filaEspera.peek());
    }

    @Test
    public void testLiberarMesa() {
        Cliente cliente = new Cliente("Joao");
        Requisicao requisicao = new Requisicao(cliente, 4);
        restaurante.adicionarRequisicao(requisicao);

        List<Mesa> mesasOcupadas = restaurante.getMesasOcupadas();
        assertEquals(1, mesasOcupadas.size());

        restaurante.liberarMesa(mesasOcupadas.get(0));
        assertTrue(mesasOcupadas.get(0).isMesaOcupada() == false);
    }

    @Test
    public void testAdicionarPedido() {
        Cliente cliente = new Cliente("Joao");
        Requisicao requisicao = new Requisicao(cliente, 4);
        restaurante.adicionarRequisicao(requisicao);

        List<Mesa> mesasOcupadas = restaurante.getMesasOcupadas();
        Mesa mesa = mesasOcupadas.get(0);

        Pedido pedido = new Pedido(false);
        pedido.pedirItem(new Item(1, "Hamburger", 10.00));
        boolean pedidoAdicionado = restaurante.adicionarPedido(mesa.getIdMesa(), pedido);

        assertTrue(pedidoAdicionado);
        assertEquals(pedido, mesa.getPedido());
    }

    @Test
    public void testEncerrarAtendimento() {
        Cliente cliente = new Cliente("Joao");
        Requisicao requisicao = new Requisicao(cliente, 4);
        restaurante.adicionarRequisicao(requisicao);

        List<Mesa> mesasOcupadas = restaurante.getMesasOcupadas();
        Mesa mesa = mesasOcupadas.get(0);

        Pedido pedido = new Pedido(false);
        pedido.pedirItem(new Item(1, "Hamburger", 10.00));
        restaurante.adicionarPedido(mesa.getIdMesa(), pedido);

        Requisicao requisicaoEncerrada = restaurante.encerrarAtendimento(mesa.getIdMesa());

        assertNotNull(requisicaoEncerrada);
        assertEquals(requisicao, requisicaoEncerrada);
        assertEquals(pedido, requisicaoEncerrada.getPedido());

        assertTrue(mesa.isMesaOcupada() == false);
    }

    @Test
    public void testAdicionarPedidoMenuFechado() {
        Cliente cliente = new Cliente("Joao");
        Requisicao requisicao = new Requisicao(cliente, 4);
        restaurante.adicionarRequisicao(requisicao);

        List<Mesa> mesasOcupadas = restaurante.getMesasOcupadas();
        Mesa mesa = mesasOcupadas.get(0);

        MenuFechado menuFechado = new MenuFechado(1, "Menu Completo", 25.00);
        restaurante.adicionarPedidoMenuFechado(mesa.getIdMesa(), menuFechado);

        Pedido pedido = mesa.getPedido();
        assertNotNull(pedido);
        assertTrue(pedido.getItens().contains(menuFechado));
    }
}

