package codigo.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class CardapioTest {

    @Test
    public void testGetItem() {
        Cardapio cardapio = new Cardapio();
        
        Item item = cardapio.getItem(1);
        assertNotNull(item);
        assertEquals("Moqueca de palmito", item.getNome());
        assertEquals(32.00, item.getPreco());

        item = cardapio.getItem(7);
        assertNotNull(item);
        assertEquals("Agua", item.getNome());
        assertEquals(3.00, item.getPreco());

        item = cardapio.getItem(999); // Código inexistente
        assertNull(item);
    }

    @Test
    public void testGetItems() {
        Cardapio cardapio = new Cardapio();
        List<Item> itens = cardapio.getItems();
        
        assertEquals(11, itens.size());
        
        Item item1 = itens.get(0);
        assertEquals("Moqueca de palmito", item1.getNome());
        assertEquals(32.00, item1.getPreco());
        assertEquals(1, item1.getIdentificador());

        Item item2 = itens.get(6);
        assertEquals("Agua", item2.getNome());
        assertEquals(3.00, item2.getPreco());
        assertEquals(7, item2.getIdentificador());
    }

    @Test
    public void testExibirMenu() {
        Cardapio cardapio = new Cardapio();
        cardapio.exibirMenu();
        // Não há assertivas para este teste já que o método imprime na saída padrão.
        // Para testes de saída padrão, é necessário usar bibliotecas adicionais ou redirecionamento da saída.
    }
}
