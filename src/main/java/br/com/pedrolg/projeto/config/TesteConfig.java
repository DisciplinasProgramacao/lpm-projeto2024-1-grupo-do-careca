package br.com.pedrolg.projeto.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.pedrolg.projeto.entity.Cliente;
import br.com.pedrolg.projeto.entity.Item;
import br.com.pedrolg.projeto.entity.Mesa;
import br.com.pedrolg.projeto.entity.Pedido;
import br.com.pedrolg.projeto.repository.ClienteRepository;
import br.com.pedrolg.projeto.repository.ItemRepository;
import br.com.pedrolg.projeto.repository.MesaRepository;
import br.com.pedrolg.projeto.repository.PedidoRepository;
import br.com.pedrolg.projeto.repository.RequisicaoRepository;

@Configuration
public class TesteConfig implements CommandLineRunner {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private MesaRepository mesaRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private RequisicaoRepository requisicaoRepository;
	
	

	@Override
	public void run(String... args) throws Exception {
		
		Cliente c1 = new Cliente(1, "Pedro");
		Cliente c2 = new Cliente(2, "Joao");
		Cliente c3 = new Cliente(3, "Lebron James");
		Cliente c4 = new Cliente(4, "Max Verstappen");
		
		clienteRepository.saveAll(Arrays.asList(c1,c2,c3,c4));
		
		Item i1 = new Item("Moqueca de palmito", 32.00, 1);
		Item i2 = new Item("Falafel Assado", 20.00, 2);
		Item i3 = new Item("Salada primavera com macarrão Konjac", 25.00, 3);
		
		Item i4 = new Item("Cerveja Vegana", 9.00, 10);
		
		itemRepository.saveAll(Arrays.asList(i1,i2,i3,i4));
		
		Pedido p1 = new Pedido();
		
		Mesa m1 = new Mesa(1, 4);
		Mesa m2 = new Mesa(2, 4);
		Mesa m3 = new Mesa(5, 6);
		Mesa m4 = new Mesa(9, 8);		

		
		mesaRepository.saveAll(Arrays.asList(m1,m2,m3,m4));

        
		// Requisicao req1 = new Requisicao(m1, c1, 4);
		// Requisicao req2 = new Requisicao(m2, c2, 4);
		// Requisicao req3 = new Requisicao(m3, c3, 6);
		// Requisicao req4 = new Requisicao(m4, c4, 8);
		
		
		// requisicaoRepository.saveAll(Arrays.asList(req1, req2, req3, req4));
		
		
	}
    
}
