package br.dev.fabricio.api_financeiro.domain.port.out;

import br.dev.fabricio.api_financeiro.domain.model.Caixa;

import java.time.LocalDate;
import java.util.List;

public interface CaixaRepository {
  Caixa insert(Caixa caixa);

  Caixa findById(Long id);

  Caixa update(Caixa caixa);



  List<Caixa> findByData(LocalDate data);
}
