package br.com.pedrolg.projeto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pedrolg.projeto.entity.Requisicao;
import br.com.pedrolg.projeto.service.RequisicaoService;

@RestController
@RequestMapping("/requisicoes")
public class RequisicaoController {

    @Autowired
    private RequisicaoService requisicaoService;

    @GetMapping
    public ResponseEntity<List<Requisicao>> listarRequisicoes() {
        List<Requisicao> requisicoes = requisicaoService.listarRequisicoes();
        return new ResponseEntity<>(requisicoes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Requisicao> encontrarRequisicaoPorId(@PathVariable int id) {
        return requisicaoService.encontrarRequisicaoPorId(id)
                .map(requisicao -> new ResponseEntity<>(requisicao, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Requisicao> salvarRequisicao(@RequestBody Requisicao requisicao) {
        Requisicao novaRequisicao = requisicaoService.salvarRequisicao(requisicao);
        return new ResponseEntity<>(novaRequisicao, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarRequisicao(@PathVariable int id) {
        requisicaoService.deletarRequisicao(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
