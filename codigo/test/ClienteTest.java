package codigo.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClienteTest {

    @Test
    public void testClienteConstructor() {
        Cliente cliente1 = new Cliente("Alice");
        assertEquals("Alice", cliente1.getNome());
        assertEquals(1, cliente1.getId());

        Cliente cliente2 = new Cliente("Bob");
        assertEquals("Bob", cliente2.getNome());
        assertEquals(2, cliente2.getId());

        Cliente cliente3 = new Cliente("Charlie");
        assertEquals("Charlie", cliente3.getNome());
        assertEquals(3, cliente3.getId());
    }

    @Test
    public void testToString() {
        Cliente cliente = new Cliente("Alice");
        assertEquals("Cliente : Alice", cliente.toString());
    }
}
