package myapp.grupocarecaspring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myapp.grupocarecaspring.entities.Item;
import myapp.grupocarecaspring.entities.Pedido;
import myapp.grupocarecaspring.repositories.PedidoRepository;
import myapp.grupocarecaspring.repositories.ItemRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ItemRepository itemRepository;

    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> encontrarPedidoPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    public Pedido salvarPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public void deletarPedido(Long id) {
        pedidoRepository.deleteById(id);
    }
}
