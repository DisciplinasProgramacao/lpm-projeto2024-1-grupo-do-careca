package codigo.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import src.main.java.myapp.grupocarecaspring.entities.Item;
public class ItemTest {

    @Test
    public void testItemConstructor() {
        Item item = new Item("Moqueca de palmito", 32.00, 1);
        assertEquals("Moqueca de palmito", item.getNome());
        assertEquals(32.00, item.getPreco());
        assertEquals(1, item.getIdentificador());
    }

    @Test
    public void testGetNome() {
        Item item = new Item("Falafel Assado", 20.00, 2);
        assertEquals("Falafel Assado", item.getNome());
    }

    @Test
    public void testGetPreco() {
        Item item = new Item("Salada primavera com macarrão Konjac", 25.00, 3);
        assertEquals(25.00, item.getPreco());
    }

    @Test
    public void testGetIdentificador() {
        Item item = new Item("Escondidinho de Inhame", 18.00, 4);
        assertEquals(4, item.getIdentificador());
    }

    @Test
    public void testToString() {
        Item item = new Item("Strogonoff de cogumelos", 35.00, 5);
        assertEquals("Item: Strogonoff de cogumelos, Preço: R$35.0", item.toString());
    }
}
