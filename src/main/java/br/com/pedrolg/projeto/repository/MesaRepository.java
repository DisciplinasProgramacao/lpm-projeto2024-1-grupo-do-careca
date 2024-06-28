package br.com.pedrolg.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.pedrolg.projeto.entity.Mesa;

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Integer> {
}
