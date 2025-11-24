package br.com.api.lojainformatica.controller;

import br.com.api.lojainformatica.dto.CompraDTO;
import br.com.api.lojainformatica.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/compras")
public class CompraController {
    @Autowired
    private CompraService compraService;

    @PostMapping
    public ResponseEntity<CompraDTO> salvarCompra(@RequestBody CompraDTO compraDTO) {
        CompraDTO compraDTOSalvo = compraService.salvarCompra(compraDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(compraDTOSalvo);
    }

    @GetMapping
    public ResponseEntity<List<CompraDTO>> listarCompras() {
        List<CompraDTO> comprasDTO = compraService.listarCompras();
        return ResponseEntity.ok(comprasDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompraDTO> buscarCompraPorId(@PathVariable Long id) {
        CompraDTO compraDTO = compraService.buscarCompraPorId(id);
        return ResponseEntity.ok(compraDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCompra(@PathVariable Long id) {
        compraService.deletarCompra(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompraDTO> atualizarCompra(@PathVariable Long id, @RequestBody CompraDTO compraDTO) {
        CompraDTO compraDTOSalvo = compraService.atualizarCompra(id, compraDTO);
        return ResponseEntity.ok(compraDTOSalvo);
    }
}
