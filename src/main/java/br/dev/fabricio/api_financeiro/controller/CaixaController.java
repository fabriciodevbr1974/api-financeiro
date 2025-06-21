package br.dev.fabricio.api_financeiro.controller;

import br.dev.fabricio.api_financeiro.dto.CaixaRequestDto;
import br.dev.fabricio.api_financeiro.dto.CaixaResponseDto;
import br.dev.fabricio.api_financeiro.service.CaixaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/financeiro")
public class CaixaController {


  private final CaixaService caixaService;

  public CaixaController(CaixaService caixaService) {
    this.caixaService = caixaService;
  }


  @PostMapping
  public ResponseEntity<CaixaResponseDto> insert(@RequestBody CaixaRequestDto request) {
    CaixaResponseDto response = caixaService.insert(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @GetMapping
  public ResponseEntity<List<CaixaResponseDto>> findAll() {
    List<CaixaResponseDto> lista = caixaService.findAll();
    return ResponseEntity.ok(lista);
  }

  @GetMapping("/{id}")
  public ResponseEntity<CaixaResponseDto> findById(@PathVariable Long id) {
    CaixaResponseDto response = caixaService.findById(id);
    return ResponseEntity.ok(response);
  }

  @PutMapping("/{id}")
  public ResponseEntity<CaixaResponseDto> update(@PathVariable Long id, @RequestBody CaixaRequestDto request) {
    CaixaResponseDto response = caixaService.update(id,request);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity delete(@PathVariable Long id) {
    caixaService.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

}
