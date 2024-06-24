package codigo.src.main.java.myapp.grupocarecaspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import myapp.grupocarecaspring.entiities.Requisicao;

@Repository
public interface RequisicaoRepository extends JpaRepository<Requisicao, Integer> {
}