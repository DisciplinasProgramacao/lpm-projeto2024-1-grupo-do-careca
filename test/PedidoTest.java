package codigo.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.ArrayList;

public class PedidoTest {

    @Test
    public void testPedidoConstructor() {
        Pedido pedido1 = new Pedido(true);
        assertTrue(pedido1.isMenuFechado());
        assertTrue(pedido1.getItemsEscolhidos().isEmpty());

        Pedido pedido2 = new Pedido(false);
        assertFalse(pedido2.isMenuFechado());
        assertTrue(pedido2.getItemsEscolhidos().isEmpty());
    }

    @Test
    public void testPedirItem() {
        Pedido pedido = new Pedido(false);
        Item item = new Item("Falafel Assado", 20.00, 2);
        pedido.pedirItem(item);
        List<Item> itemsEscolhidos = pedido.getItemsEscolhidos();
        assertEquals(1, itemsEscolhidos.size());
        assertEquals(item, itemsEscolhidos.get(0));
    }

    @Test
    public void testValorAPagar() {
        Pedido pedido = new Pedido(false);
        pedido.pedirItem(new Item("Falafel Assado", 20.00, 2));
        pedido.pedirItem(new Item("Salada primavera com macarrão Konjac", 25.00, 3));
        double expectedValor = (20.00 + 25.00) * 1.1; // Inclui a taxa de 10%
        assertEquals(expectedValor, pedido.valorAPagar(), 0.01);
    }

    @Test
    public void testCalcularValorPorPessoa() {
        Pedido pedido = new Pedido(false);
        pedido.pedirItem(new Item("Falafel Assado", 20.00, 2));
        pedido.pedirItem(new Item("Salada primavera com macarrão Konjac", 25.00, 3));
        double valorTotal = (20.00 + 25.00) * 1.1;
        double valorPorPessoa = valorTotal / 3;
        assertEquals(valorPorPessoa, pedido.calcularValorPorPessoa(3), 0.01);
    }

    @Test
    public void testItemFormatado() {
        Pedido pedido = new Pedido(false);
        Item item = new Item("Falafel Assado", 20.00, 2);
        String expectedFormatado = "Item: Falafel Assado, Preço: R$20.0\n";
        assertEquals(expectedFormatado, pedido.itemFormatado(item));
    }

    @Test
    public void testRelatorioItens() {
        Pedido pedido = new Pedido(false);
        pedido.pedirItem(new Item("Falafel Assado", 20.00, 2));
        pedido.pedirItem(new Item("Salada primavera com macarrão Konjac", 25.00, 3));

        List<String> relatorioEsperado = new ArrayList<>();
        relatorioEsperado.add("Item: Falafel Assado, Preço: R$20.0\n");
        relatorioEsperado.add("Item: Salada primavera com macarrão Konjac, Preço: R$25.0\n");

        List<String> relatorioAtual = pedido.relatorioItens();
        assertEquals(relatorioEsperado, relatorioAtual);
    }
}
