package codigo.test;

import org.junit.Test;

import codigo.entities.Cliente;
import codigo.entities.Mesa;
import codigo.entities.Pedido;
import codigo.entities.Requisicao;

import static org.junit.Assert.*;

import org.junit.Before;

public class MesaTest {

    private Mesa mesa;

    @Before
    public void setUp() {
        mesa = new Mesa(4);
    }

    @Test
    public void testMesaInicializacao() {
        assertEquals(4, mesa.getQuantidadeDeCadeiras());
        assertFalse(mesa.isMesaOcupada());
        assertNull(mesa.getRequisicaoAtual());
        assertNull(mesa.getPedido());
    }

    @Test
    public void testOcuparDesocuparMesa() {
        mesa.ocuparMesa();
        assertTrue(mesa.isMesaOcupada());

        mesa.desocuparMesa();
        assertFalse(mesa.isMesaOcupada());
    }

    @Test
    public void testSetQuantidadeDeCadeiras() {
        mesa.setQuantidadeDeCadeiras(6);
        assertEquals(6, mesa.getQuantidadeDeCadeiras());
    }

    @Test
    public void testSetRequisicaoAtual() {
        Cliente cliente = new Cliente("Xulambs");
        Requisicao requisicao = new Requisicao(4, cliente);
        mesa.setRequisicaoAtual(requisicao);
        assertEquals(requisicao, mesa.getRequisicaoAtual());
    }

    @Test
    public void testSetPedido() {
        Pedido pedido = new Pedido(false);
        mesa.setPedido(pedido);
        assertEquals(pedido, mesa.getPedido());
    }

    @Test
    public void testGetIdMesa() {
        int id = mesa.getIdMesa();
        Mesa novaMesa = new Mesa(2);
        assertNotEquals(id, novaMesa.getIdMesa());
    }
}