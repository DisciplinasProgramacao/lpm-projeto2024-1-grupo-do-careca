package codigo.test;

import org.junit.Test;

import codigo.src.main.java.myapp.grupocarecaspring.entities.Item;

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
    public void testGetPreco() {
        assertEquals(15.0, item.getPreco(),0.1);
    }

  

    @Test
    public void testGetIdentificador() {
        assertEquals(1, item.getIdentificador());
    }

   
    
}