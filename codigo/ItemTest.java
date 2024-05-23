package codigo;

import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.Before;

public class ItemTest { 

    private Item item;

    @Before
    public void setUp() {
        item = new Item("Falafel Assado", 15.0, 1);
    }

    @Test
    public void testGetNome() {
        assertEquals("Falafel Assado", item.getNome());
    }

    @Test
    public void testSetNome() {
        item.setNome("Caçarola de Legumes");
        assertEquals("Caçarola de Legumes", item.getNome());
    }

    @Test
    public void testGetPreco() {
        assertEquals(15.0, item.getPreco(),0.1);
    }

    @Test
    public void testSetPreco() {
        item.setPreco(20.0);
        assertEquals(20.0, item.getPreco(), 0.1);
    }

    @Test
    public void testGetIdentificador() {
        assertEquals(1, item.getIdentificador());
    }

    @Test
    public void testSetIdentificador() {
        item.setIdentificador(2);
        assertEquals(2, item.getIdentificador());
    }
    
}