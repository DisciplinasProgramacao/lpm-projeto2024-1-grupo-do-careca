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

import br.com.pedrolg.projeto.entity.Mesa;
import br.com.pedrolg.projeto.service.MesaService;

@RestController
@RequestMapping("/mesas")
public class MesaController {

    @Autowired
    private MesaService mesaService;

    @GetMapping
    public ResponseEntity<List<Mesa>> listarMesas() {
        List<Mesa> mesas = mesaService.listarMesas();
        return new ResponseEntity<>(mesas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mesa> encontrarMesaPorId(@PathVariable int id) {
        return mesaService.encontrarMesaPorId(id)
                .map(mesa -> new ResponseEntity<>(mesa, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Mesa> salvarMesa(@RequestBody Mesa mesa) {
        Mesa novaMesa = mesaService.salvarMesa(mesa);
        return new ResponseEntity<>(novaMesa, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMesa(@PathVariable int id) {
        mesaService.deletarMesa(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
