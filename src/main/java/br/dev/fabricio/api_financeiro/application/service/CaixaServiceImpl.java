package br.dev.fabricio.api_financeiro.application.service;

import br.dev.fabricio.api_financeiro.domain.dto.CaixaRequestDto;
import br.dev.fabricio.api_financeiro.domain.dto.CaixaResponseDto;
import br.dev.fabricio.api_financeiro.domain.model.Caixa;
import br.dev.fabricio.api_financeiro.domain.port.in.CaixaService;
import br.dev.fabricio.api_financeiro.domain.port.out.CaixaRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class CaixaServiceImpl implements CaixaService {

  private final CaixaRepository caixaRepository;

  public CaixaServiceImpl(CaixaRepository caixaRepository){

    this.caixaRepository = caixaRepository;

  }

  @Override
  public CaixaResponseDto insert(CaixaRequestDto caixaRequestDto) {

    Caixa caixa = new Caixa();
    caixa.setData(caixaRequestDto.getData() == null ? LocalDate.now() : caixaRequestDto.getData());
    caixa.setDescricao(caixaRequestDto.getDescricao());
    caixa.setValor(caixaRequestDto.getValor() == null ? BigDecimal.ZERO : caixaRequestDto.getValor());
    caixa.setTipo(caixaRequestDto.getTipo());

    caixa = caixaRepository.insert(caixa);

    CaixaResponseDto caixaResponseDto = new CaixaResponseDto();
    caixaResponseDto.setId(caixa.getId());
    caixaResponseDto.setDescricao(caixa.getDescricao());
    caixaResponseDto.setData(caixa.getData());
    caixaResponseDto.setValor(caixa.getValor());
    caixaResponseDto.setTipo(caixa.getTipo());

    return caixaResponseDto;
  }

  @Override
  public CaixaResponseDto update(Long id, CaixaRequestDto caixaRequestDto) {
    return null;
  }

  @Override
  public void delete(Long id) {

  }

  @Override
  public CaixaResponseDto findById(Long id) {
    return null;
  }

  @Override
  public List<CaixaResponseDto> findByData(LocalDate data) {
    return List.of();
  }
}
