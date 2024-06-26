package codigo.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MesaTest {

    @Test
    public void testMesaConstructor() {
        Mesa mesa1 = new Mesa(4);
        assertEquals(1, mesa1.getIdMesa());
        assertEquals(4, mesa1.getQuantidadeDeCadeiras());
        assertFalse(mesa1.isMesaOcupada());
        assertNull(mesa1.getPedido());

        Mesa mesa2 = new Mesa(6);
        assertEquals(2, mesa2.getIdMesa());
        assertEquals(6, mesa2.getQuantidadeDeCadeiras());
        assertFalse(mesa2.isMesaOcupada());
        assertNull(mesa2.getPedido());
    }

    @Test
    public void testSetQuantidadeDeCadeiras() {
        Mesa mesa = new Mesa(4);
        mesa.setQuantidadeDeCadeiras(5);
        assertEquals(5, mesa.getQuantidadeDeCadeiras());
    }

    @Test
    public void testOcuparMesa() {
        Mesa mesa = new Mesa(4);
        mesa.ocuparMesa();
        assertTrue(mesa.isMesaOcupada());
    }

    @Test
    public void testDesocuparMesa() {
        Mesa mesa = new Mesa(4);
        mesa.ocuparMesa();
        mesa.desocuparMesa();
        assertFalse(mesa.isMesaOcupada());
    }

    @Test
    public void testSetPedido() {
        Mesa mesa = new Mesa(4);
        Pedido pedido = new Pedido();
        mesa.setPedido(pedido);
        assertEquals(pedido, mesa.getPedido());
    }
}
