package br.dev.fabricio.api_financeiro.application.service;

import br.dev.fabricio.api_financeiro.domain.dto.CaixaRequestDto;
import br.dev.fabricio.api_financeiro.domain.dto.CaixaResponseDto;
import br.dev.fabricio.api_financeiro.domain.model.Caixa;
import br.dev.fabricio.api_financeiro.domain.port.in.CaixaServicePortIn;
import br.dev.fabricio.api_financeiro.domain.port.out.CaixaRepositoryPortOut;
import br.dev.fabricio.api_financeiro.exceptions.LancamentoNaoEncontradoException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CaixaServiceImpl implements CaixaServicePortIn {

  private static final String LANCAMENTO_NAO_ENCONTRADO = "Lançamento não encontrado";

  private final CaixaRepositoryPortOut caixaRepository;

  public CaixaServiceImpl(CaixaRepositoryPortOut caixaRepository){
    this.caixaRepository = caixaRepository;
  }

  @Override
  public CaixaResponseDto insert(CaixaRequestDto caixaRequestDto) {

    Caixa caixa = new Caixa(caixaRequestDto);

    caixa = caixaRepository.insert(caixa);

    return new CaixaResponseDto(caixa);

  }

  @Override
  public CaixaResponseDto update(Long id, CaixaRequestDto caixaRequestDto) {

    Caixa caixa = caixaRepository.findById(id);

    if(caixa == null){
      throw new LancamentoNaoEncontradoException(LANCAMENTO_NAO_ENCONTRADO);
    }


    caixa.setData(caixaRequestDto.getData() == null ? LocalDate.now() : caixaRequestDto.getData());
    caixa.setDescricao(caixaRequestDto.getDescricao());
    caixa.setValor(caixaRequestDto.getValor() == null ? BigDecimal.ZERO : caixaRequestDto.getValor());
    caixa.setTipo(caixaRequestDto.getTipo());

    caixa = caixaRepository.update(caixa);

    return new CaixaResponseDto(caixa);
  }

  @Override
  public void delete(Long id) {
    Caixa caixa = caixaRepository.findById(id);

    if(caixa == null){
      throw new LancamentoNaoEncontradoException(LANCAMENTO_NAO_ENCONTRADO);
    }

    caixaRepository.delete(id);
  }

  @Override
  public CaixaResponseDto findById(Long id) {
    Caixa caixa = caixaRepository.findById(id);
    if(caixa == null){
      throw new LancamentoNaoEncontradoException(LANCAMENTO_NAO_ENCONTRADO);
    }

    return new CaixaResponseDto(caixa);
  }

  @Override
  public List<CaixaResponseDto> findByData(LocalDate data) {

    List<CaixaResponseDto> list = new ArrayList<>();

    List<Caixa> caixas = caixaRepository.findByData(data);

    caixas.forEach(caixa -> {

      CaixaResponseDto caixaResponseDto = new CaixaResponseDto(caixa);

      list.add(caixaResponseDto);

    });

    return list;
  }

  @Override
  public BigDecimal getSaldo(LocalDate data) {

    List<CaixaResponseDto> list = findByData(data);

    BigDecimal somaValor = BigDecimal.ZERO;

    for (CaixaResponseDto caixa : list) {
      somaValor = somaValor.add(caixa.getTipo().equalsIgnoreCase("S") ? caixa.getValor().negate() : caixa.getValor());
    }

    return somaValor;
  }
}
