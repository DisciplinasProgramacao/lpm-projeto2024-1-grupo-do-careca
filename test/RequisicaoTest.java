package codigo.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import src.main.java.myapp.grupocarecaspring.entities.Cliente;
import src.main.java.myapp.grupocarecaspring.entities.Item;
import src.main.java.myapp.grupocarecaspring.entities.Mesa;
import src.main.java.myapp.grupocarecaspring.entities.Pedido;
import src.main.java.myapp.grupocarecaspring.entities.Requisicao;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RequisicaoTest {

    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        cliente = new Cliente("João");
    }

    @Test
    public void testRequisicaoConstructorValid() {
        Requisicao requisicao = new Requisicao(4, cliente);
        assertEquals(4, requisicao.getQuantidadeDePessoas());
        assertEquals(cliente, requisicao.getCliente());
        assertNotNull(requisicao.getChegada());
        assertTrue(requisicao.getPedidos().isEmpty());
    }

    @Test
    public void testRequisicaoConstructorInvalidTooManyPeople() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Requisicao(9, cliente);
        });
        assertEquals("A quantidade de pessoas não pode ser maior que 8.", exception.getMessage());
    }

    @Test
    public void testRequisicaoConstructorInvalidNonPositivePeople() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Requisicao(0, cliente);
        });
        assertEquals("A quantidade de pessoas deve ser maior que 0.", exception.getMessage());
    }

    @Test
    public void testSetMesa() {
        Requisicao requisicao = new Requisicao(4, cliente);
        Mesa mesa = new Mesa(4);
        requisicao.setMesa(mesa);
        assertEquals(mesa, requisicao.getMesa());
    }

    @Test
    public void testSetMesaNull() {
        Requisicao requisicao = new Requisicao(4, cliente);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            requisicao.setMesa(null);
        });
        assertEquals("Mesa não pode ser nula.", exception.getMessage());
    }

    @Test
    public void testAdicionarPedido() {
        Requisicao requisicao = new Requisicao(4, cliente);
        Pedido pedido = new Pedido(false);
        requisicao.adicionarPedido(pedido);
        List<Pedido> pedidos = requisicao.getPedidos();
        assertEquals(1, pedidos.size());
        assertEquals(pedido, pedidos.get(0));
    }

    @Test
    public void testAdicionarPedidoNull() {
        Requisicao requisicao = new Requisicao(4, cliente);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            requisicao.adicionarPedido(null);
        });
        assertEquals("Pedido não pode ser nulo.", exception.getMessage());
    }

    @Test
    public void testEncerrarRequisicao() {
        Requisicao requisicao = new Requisicao(4, cliente);
        Pedido pedido = new Pedido(false);
        requisicao.adicionarPedido(pedido);
        requisicao.encerrarRequisicao();
        assertNotNull(requisicao.getSaida());
        assertTrue(requisicao.getSaida().isAfter(requisicao.getChegada()));
    }

    @Test
    public void testEncerrarRequisicaoWithoutPedidos() {
        Requisicao requisicao = new Requisicao(4, cliente);
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            requisicao.encerrarRequisicao();
        });
        assertEquals("Não é possível encerrar a requisição sem pedidos.", exception.getMessage());
    }

    @Test
    public void testRelatorioAtendimento() {
        Requisicao requisicao = new Requisicao(4, cliente);
        Mesa mesa = new Mesa(4);
        requisicao.setMesa(mesa);
        Pedido pedido = new Pedido(false);
        Item item = new Item("Falafel Assado", 20.00, 2);
        pedido.pedirItem(item);
        requisicao.adicionarPedido(pedido);
        requisicao.encerrarRequisicao();
        String relatorio = requisicao.relatorioAtendimento();
        assertTrue(relatorio.contains("Horário de Chegada: "));
        assertTrue(relatorio.contains("Cliente: João"));
        assertTrue(relatorio.contains("Horário de Saída: "));
        assertTrue(relatorio.contains("Item: Falafel Assado, Preço: R$20.0\n"));
        assertTrue(relatorio.contains("Total por Pessoa: R$ 22.00\n"));
    }
}
