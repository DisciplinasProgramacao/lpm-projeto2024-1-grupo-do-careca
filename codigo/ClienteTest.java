package codigo;

import org.junit.Test;
import static org.junit.Assert.*;

public class ClienteTest {

    @Test
    public void testGetNome() {
        Cliente cliente = new Cliente("Joao", 99);
        assertEquals("João", cliente.getNome());
    }

    @Test
    public void testSetNome() {
        Cliente cliente = new Cliente("Maria",55);
        cliente.setNome("Ana");
        assertEquals("Ana", cliente.getNome());
        assertEquals(55, cliente.getId());
    }

    // @Test
    // public void testGetQuantidadeDePessoas() {
    //     Cliente cliente = new Cliente("Carlos", 5);
    //     assertEquals(5, cliente.getQuantidadeDePessoas());
    // }

    // @Test
    // public void testCabemNaMesa() {
    //     Cliente cliente = new Cliente("Fernanda", 1);
    //     assertTrue(cliente.cabemNaMesa(2)); // A mesa tem mais cadeiras do que clientes
    // }

}
