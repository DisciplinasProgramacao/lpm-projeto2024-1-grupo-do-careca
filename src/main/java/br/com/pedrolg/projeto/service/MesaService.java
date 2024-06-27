package br.com.pedrolg.projeto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pedrolg.projeto.entity.Mesa;
import br.com.pedrolg.projeto.repository.MesaRepository;

@Service
public class MesaService {

    @Autowired
    private MesaRepository mesaRepository;

    public List<Mesa> listarMesas() {
        return mesaRepository.findAll();
    }

    public Optional<Mesa> encontrarMesaPorId(int id) {
        return mesaRepository.findById(id);
    }

    public Mesa salvarMesa(Mesa mesa) {
        return mesaRepository.save(mesa);
    }

    public void deletarMesa(int id) {
        mesaRepository.deleteById(id);
    }
}
