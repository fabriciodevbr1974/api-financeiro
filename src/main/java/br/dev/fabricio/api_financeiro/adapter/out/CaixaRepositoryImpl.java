package br.dev.fabricio.api_financeiro.adapter.out;

import br.dev.fabricio.api_financeiro.domain.model.Caixa;
import br.dev.fabricio.api_financeiro.domain.port.out.CaixaRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class CaixaRepositoryImpl implements CaixaRepository {
  @Override
  public Caixa insert(Caixa caixa) {

    caixa.setId(1L);

    return caixa;
  }

  @Override
  public Caixa findById(Long id) {

    Caixa caixa = new Caixa();
    caixa.setId(1L);
    caixa.setDescricao("Salário caiu na conta");
    caixa.setTipo("E");
    caixa.setData(LocalDate.now());
    caixa.setValor(new BigDecimal(5000));
    return caixa;
  }

  @Override
  public Caixa update(Caixa caixa) {
    return null;
  }

  @Override
  public void update(Long id) {

  }

  @Override
  public List<Caixa> findByData(LocalDate data) {
    return List.of();
  }
}
