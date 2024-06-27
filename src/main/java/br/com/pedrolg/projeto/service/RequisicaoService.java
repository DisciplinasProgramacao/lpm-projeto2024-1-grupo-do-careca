package br.com.pedrolg.projeto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pedrolg.projeto.entity.Requisicao;
import br.com.pedrolg.projeto.repository.RequisicaoRepository;

@Service
public class RequisicaoService {

    @Autowired
    private RequisicaoRepository requisicaoRepository;

    public List<Requisicao> listarRequisicoes() {
        return requisicaoRepository.findAll();
    }

    public Optional<Requisicao> encontrarRequisicaoPorId(int id) {
        return requisicaoRepository.findById(id);
    }

    public Requisicao salvarRequisicao(Requisicao requisicao) {
        return requisicaoRepository.save(requisicao);
    }

    public void deletarRequisicao(int id) {
        requisicaoRepository.deleteById(id);
    }
}
