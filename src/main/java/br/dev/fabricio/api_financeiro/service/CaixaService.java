package br.dev.fabricio.api_financeiro.service;

import br.dev.fabricio.api_financeiro.dto.CaixaRequestDto;
import br.dev.fabricio.api_financeiro.dto.CaixaResponseDto;

import java.util.List;

public interface CaixaService {

  CaixaResponseDto insert(CaixaRequestDto request);
  CaixaResponseDto update(Long id, CaixaRequestDto request);
  void delete(Long id);
  CaixaResponseDto findById(Long id);
  List<CaixaResponseDto> findAll();


}
