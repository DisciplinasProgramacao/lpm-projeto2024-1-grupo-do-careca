package codigo;

import codigo.entities.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Queue;

import static org.junit.Assert.*;

public class RestauranteTest {
    private Restaurante restaurante;
    private Cardapio cardapio;

    @Before
    public void setUp() {
        cardapio = new Cardapio();
        restaurante = new Restaurante(cardapio);
    }

    @Test
    public void testAdicionarCliente() {
        Cliente cliente = new Cliente("João");
        Requisicao requisicao = new Requisicao(4, cliente);

        restaurante.adicionarRequisicao(requisicao);

        Queue<Requisicao> filaEspera = restaurante.getFilaEspera();
        assertTrue(filaEspera.contains(requisicao));
    }

    @Test
    public void testServirClienteMenuFechado() {
        Cliente cliente = new Cliente("Maria");
        Requisicao requisicao = new Requisicao(2, cliente);
        restaurante.adicionarRequisicao(requisicao);

        int numeroMesa = requisicao.getMesa().getIdMesa();
        String comida = "Falafel Assado";
        List<String> bebidas = List.of("Copo de suco", "Refrigerante orgânico");
        MenuFechado menuFechado = new MenuFechado("Menu Fechado", 32.0, 99, comida, bebidas);

        restaurante.adicionarPedidoMenuFechado(numeroMesa, menuFechado);

        Mesa mesa = restaurante.encontrarMesaPorNumero(numeroMesa);
        Pedido pedido = mesa.getRequisicaoAtual().getPedidos().get(0);
        assertTrue(pedido.getItens().contains(menuFechado));
    }

    @Test
    public void testServirClienteMenuAberto() {
        Cliente cliente = new Cliente("Ana");
        Requisicao requisicao = new Requisicao(2, cliente);
        restaurante.adicionarRequisicao(requisicao);

        int numeroMesa = requisicao.getMesa().getIdMesa();
        Pedido pedido = new Pedido(false);
        Item item = restaurante.obterItemCardapio(1);  // Assumindo que o item 1 existe no cardápio
        pedido.pedirItem(item);

        restaurante.encontrarMesaPorNumero(numeroMesa).getRequisicaoAtual().adicionarPedido(pedido);

        Mesa mesa = restaurante.encontrarMesaPorNumero(numeroMesa);
        Pedido pedidoRealizado = mesa.getRequisicaoAtual().getPedidos().get(0);
        assertTrue(pedidoRealizado.getItens().contains(item));
    }

    @Test
    public void testEncerrarAtendimento() {
        Cliente cliente = new Cliente("Carlos");
        Requisicao requisicao = new Requisicao(3, cliente);
        restaurante.adicionarRequisicao(requisicao);

        int numeroMesa = requisicao.getMesa().getIdMesa();
        restaurante.encerrarAtendimento(numeroMesa);

        assertFalse(restaurante.verificarMesaOcupada(numeroMesa));
    }

    @Test
    public void testVerFilaDeEspera() {
        Cliente cliente1 = new Cliente("Alice");
        Requisicao requisicao1 = new Requisicao(2, cliente1);
        restaurante.adicionarRequisicao(requisicao1);

        Cliente cliente2 = new Cliente("Bruno");
        Requisicao requisicao2 = new Requisicao(4, cliente2);
        restaurante.adicionarRequisicao(requisicao2);

        Queue<Requisicao> filaEspera = restaurante.getFilaEspera();
        assertEquals(2, filaEspera.size());
        assertTrue(filaEspera.contains(requisicao1));
        assertTrue(filaEspera.contains(requisicao2));
    }
}
