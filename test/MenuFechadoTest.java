package codigo.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

public class MenuFechadoTest {

    @Test
    public void testMenuFechadoConstructor() {
        List<String> bebidas = Arrays.asList("Suco de Laranja", "Água");
        MenuFechado menuFechado = new MenuFechado("Menu Vegano", 45.00, 1, "Moqueca de palmito", bebidas);
        
        assertEquals("Menu Vegano", menuFechado.getNome());
        assertEquals(45.00, menuFechado.getPreco());
        assertEquals(1, menuFechado.getIdentificador());
        assertEquals("Moqueca de palmito", menuFechado.toString().split(",")[2].split(": ")[1].trim());
        assertTrue(menuFechado.toString().contains("[Suco de Laranja, Água]"));
    }

    @Test
    public void testToString() {
        List<String> bebidas = Arrays.asList("Refrigerante", "Cerveja Vegana");
        MenuFechado menuFechado = new MenuFechado("Menu Especial", 55.00, 2, "Strogonoff de cogumelos", bebidas);
        
        String expected = "Item: Menu Especial, Preço: R$55.0, Comida: Strogonoff de cogumelos, Bebidas: [Refrigerante, Cerveja Vegana]";
        assertEquals(expected, menuFechado.toString());
    }
}
