package br.dev.fabricio.api_financeiro.service.impl;

import br.dev.fabricio.api_financeiro.dto.CaixaRequestDto;
import br.dev.fabricio.api_financeiro.dto.CaixaResponseDto;
import br.dev.fabricio.api_financeiro.entity.CaixaEntity;
import br.dev.fabricio.api_financeiro.repository.CaixaRepository;
import br.dev.fabricio.api_financeiro.service.CaixaService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CaixaServiceImpl implements CaixaService {


  private final CaixaRepository caixaRepository;

  public CaixaServiceImpl(CaixaRepository caixaRepository) {
    this.caixaRepository = caixaRepository;
  }


  @Override
  public CaixaResponseDto insert(CaixaRequestDto request) {

    CaixaEntity caixaEntity = new CaixaEntity();
    caixaEntity.setData(LocalDate.now());
    if(request.getData() != null){
      caixaEntity.setData(request.getData());
    }
    caixaEntity.setDescricao(request.getDescricao());
    caixaEntity.setTipo(request.getTipo());
    caixaEntity.setValor(request.getValor());

    caixaEntity = caixaRepository.save(caixaEntity);

    CaixaResponseDto response = new CaixaResponseDto();
    response.setId(caixaEntity.getId());
    response.setDescricao(caixaEntity.getDescricao());
    response.setData(caixaEntity.getData());
    response.setValor(caixaEntity.getValor());
    response.setTipo(caixaEntity.getTipo());

    return response;
  }

  @Override
  public CaixaResponseDto update(Long id, CaixaRequestDto request) {

    Optional<CaixaEntity> optional = caixaRepository.findById(id);
    if(optional.isEmpty()){
      throw new RuntimeException("Lançamento no caixa não encontrado");
    }

    CaixaEntity caixaEntity = optional.get();

    if(request.getData() != null){
      caixaEntity.setData(request.getData());
    }

    caixaEntity.setDescricao(request.getDescricao());
    caixaEntity.setTipo(request.getTipo());
    caixaEntity.setValor(request.getValor());

    caixaEntity = caixaRepository.save(caixaEntity);

    CaixaResponseDto response = new CaixaResponseDto();
    response.setId(caixaEntity.getId());
    response.setDescricao(caixaEntity.getDescricao());
    response.setData(caixaEntity.getData());
    response.setValor(caixaEntity.getValor());
    response.setTipo(caixaEntity.getTipo());

    return response;
  }

  @Override
  public void delete(Long id) {
    Optional<CaixaEntity> optional = caixaRepository.findById(id);
    if(optional.isEmpty()){
      throw new RuntimeException("Lançamento no caixa não encontrado");
    }

    caixaRepository.deleteById(id);

  }

  @Override
  public CaixaResponseDto findById(Long id) {
    Optional<CaixaEntity> optional = caixaRepository.findById(id);
    if(optional.isEmpty()){
      throw new RuntimeException("Lançamento no caixa não encontrado");
    }

    CaixaEntity caixaEntity = optional.get();

    CaixaResponseDto response = new CaixaResponseDto();
    response.setId(caixaEntity.getId());
    response.setDescricao(caixaEntity.getDescricao());
    response.setData(caixaEntity.getData());
    response.setValor(caixaEntity.getValor());
    response.setTipo(caixaEntity.getTipo());

    return response;
  }

  @Override
  public List<CaixaResponseDto> findAll() {

    List<CaixaResponseDto> response = new ArrayList<>();

    List<CaixaEntity> lista = caixaRepository.findAll();

    lista.forEach(c -> {
      CaixaResponseDto caixa = new CaixaResponseDto();
      caixa.setId(c.getId());
      caixa.setDescricao(c.getDescricao());
      caixa.setData(c.getData());
      caixa.setValor(c.getValor());
      caixa.setTipo(c.getTipo());

      response.add(caixa);
    });

    return response;
  }

  @Override
  public BigDecimal valorTotal(LocalDate dataInicial, LocalDate dataFinal, String tipo) {

    if(tipo.equalsIgnoreCase("E")){
      return valorTotalEntrada(dataInicial, dataFinal);
    }
    return valorTotalSaida(dataInicial, dataFinal);
  }

  @Override
  public BigDecimal valorTotalEntrada(LocalDate dataInicial, LocalDate dataFinal) {

    return caixaRepository.somarValorPorIntervaloDeDatas(dataInicial, dataFinal, "E");

  }

  @Override
  public BigDecimal valorTotalSaida(LocalDate dataInicial, LocalDate dataFinal) {
    return caixaRepository.somarValorPorIntervaloDeDatas(dataInicial, dataFinal, "S");
  }

  @Override
  public BigDecimal saldo(LocalDate dataInicial, LocalDate dataFinal) {

    BigDecimal entradas = valorTotalEntrada(dataInicial, dataFinal);
    BigDecimal saidas = valorTotalSaida(dataInicial, dataFinal);

    return entradas.subtract(saidas);
  }
}
