package br.dev.fabricio.api_financeiro.adapter.in;

import br.dev.fabricio.api_financeiro.domain.dto.CaixaRequestDto;
import br.dev.fabricio.api_financeiro.domain.dto.CaixaResponseDto;
import br.dev.fabricio.api_financeiro.domain.port.in.CaixaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
  public ResponseEntity<?> insert(@RequestBody CaixaRequestDto request){
    CaixaResponseDto response = caixaService.insert(request);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> findById(@PathVariable Long id){
    CaixaResponseDto response = caixaService.findById(id);
    if(response == null){
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(response);
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> update(@PathVariable Long id, @RequestBody CaixaRequestDto request){
    CaixaResponseDto response = caixaService.update(id, request);
    if(response == null){
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id){
    caixaService.delete(id);
    return ResponseEntity.noContent().build();
  }


  @GetMapping()
  public ResponseEntity<?> findByData(@RequestParam LocalDate data){
    List<CaixaResponseDto>  response = caixaService.findByData(data);
    return ResponseEntity.ok(response);
  }



}
