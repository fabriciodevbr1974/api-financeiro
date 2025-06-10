package br.dev.fabricio.api_financeiro.application.service;

import br.dev.fabricio.api_financeiro.domain.dto.CaixaRequestDto;
import br.dev.fabricio.api_financeiro.domain.dto.CaixaResponseDto;
import br.dev.fabricio.api_financeiro.domain.model.Caixa;
import br.dev.fabricio.api_financeiro.domain.port.in.CaixaService;
import br.dev.fabricio.api_financeiro.domain.port.out.CaixaRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CaixaServiceImpl implements CaixaService {

  private final CaixaRepository caixaRepository;

  public CaixaServiceImpl(CaixaRepository caixaRepository){

    this.caixaRepository = caixaRepository;

  }

  @Override
  public CaixaResponseDto insert(CaixaRequestDto caixaRequestDto) {

    Caixa caixa = new Caixa(caixaRequestDto);

    caixa = caixaRepository.insert(caixa);

    CaixaResponseDto caixaResponseDto = new CaixaResponseDto(caixa);

    return caixaResponseDto;
  }

  @Override
  public CaixaResponseDto update(Long id, CaixaRequestDto caixaRequestDto) {

    Caixa caixa = caixaRepository.findById(id);
    caixa.setData(caixaRequestDto.getData() == null ? LocalDate.now() : caixaRequestDto.getData());
    caixa.setDescricao(caixaRequestDto.getDescricao());
    caixa.setValor(caixaRequestDto.getValor() == null ? BigDecimal.ZERO : caixaRequestDto.getValor());
    caixa.setTipo(caixaRequestDto.getTipo());

    caixa = caixaRepository.update(caixa);

    CaixaResponseDto caixaResponseDto = new CaixaResponseDto(caixa);


    return caixaResponseDto;
  }

  @Override
  public void delete(Long id) {
    caixaRepository.update(id);
  }

  @Override
  public CaixaResponseDto findById(Long id) {
    Caixa caixa = caixaRepository.findById(id);

    CaixaResponseDto caixaResponseDto = new CaixaResponseDto(caixa);

    return caixaResponseDto;
  }

  @Override
  public List<CaixaResponseDto> findByData(LocalDate data) {

    List<CaixaResponseDto> list = new ArrayList<>();

    List<Caixa> caixas = caixaRepository.findByData(data);

    caixas.stream().forEach(caixa -> {

      CaixaResponseDto caixaResponseDto = new CaixaResponseDto(caixa);

      list.add(caixaResponseDto);

    });

    return list;
  }
}
