package br.dev.fabricio.api_financeiro.domain.port.in;

import br.dev.fabricio.api_financeiro.domain.dto.CaixaRequestDto;
import br.dev.fabricio.api_financeiro.domain.dto.CaixaResponseDto;

import java.time.LocalDate;
import java.util.List;

public interface CaixaService {

  CaixaResponseDto insert(CaixaRequestDto caixaRequestDto);
  CaixaResponseDto update(Long id, CaixaRequestDto caixaRequestDto);
  void delete(Long id);
  CaixaResponseDto findById(Long id);
  List<CaixaResponseDto> findByData(LocalDate data);


}
