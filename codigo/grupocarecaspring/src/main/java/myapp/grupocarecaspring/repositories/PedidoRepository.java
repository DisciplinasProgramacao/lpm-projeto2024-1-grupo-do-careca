package myapp.grupocarecaspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import myapp.grupocarecaspring.entiities.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
}
