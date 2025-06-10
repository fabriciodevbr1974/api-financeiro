package br.dev.fabricio.api_financeiro.adapter.in;

import br.dev.fabricio.api_financeiro.domain.dto.CaixaRequestDto;
import br.dev.fabricio.api_financeiro.domain.dto.CaixaResponseDto;
import br.dev.fabricio.api_financeiro.domain.port.in.CaixaService;
import br.dev.fabricio.api_financeiro.exceptions.LancamentoNaoEncontradoException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api-financeiro")
public class CaixaController {

  private final CaixaService caixaService;


  public CaixaController(CaixaService caixaService) {
    this.caixaService = caixaService;
  }

  @PostMapping
  public ResponseEntity<?> insert(@RequestBody CaixaRequestDto request) {
    CaixaResponseDto response = caixaService.insert(request);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> findById(@PathVariable Long id) {

    try{
      CaixaResponseDto response = caixaService.findById(id);
      return ResponseEntity.ok(response);
    } catch (LancamentoNaoEncontradoException e){
      return ResponseEntity.notFound().build();
    }

  }

  @PutMapping("/{id}")
  public ResponseEntity<?> update(@PathVariable Long id, @RequestBody CaixaRequestDto request) {

    try{
      CaixaResponseDto response = caixaService.update(id, request);
      return ResponseEntity.ok(response);
    } catch (LancamentoNaoEncontradoException e){
      return ResponseEntity.notFound().build();
    }

  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {

    try {
      caixaService.delete(id);
      return ResponseEntity.noContent().build();

    } catch (LancamentoNaoEncontradoException e) {
      return ResponseEntity.notFound().build();
    }

  }


  @GetMapping()
  public ResponseEntity<?> findByData(@RequestParam LocalDate data) {
    List<CaixaResponseDto> response = caixaService.findByData(data);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/saldo")
  public ResponseEntity<?> getSaldo(@RequestParam LocalDate data) {
    BigDecimal response = caixaService.getSaldo(data);
    return ResponseEntity.ok(response);
  }


}
