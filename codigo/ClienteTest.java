package codigo;

import org.junit.Test;
import static org.junit.Assert.*;

public class ClienteTest {

    

    @Test
    public void testGetNome() {
        Cliente cliente = new Cliente("João");
        assertEquals("João", cliente.getNome());
    }

    @Test
    public void testSetNome() {
        Cliente cliente = new Cliente("Maria");
        cliente.setNome("Ana");
        assertEquals("Ana", cliente.getNome());
    }

   @Test
   public void testaIdDoCliente() {
    Cliente cliente01 = new Cliente("Teste");
    Cliente cliente02 = new Cliente("Teste");
    assertEquals(1, cliente01.getId());
    assertEquals(2, cliente02.getId());
   }

}
