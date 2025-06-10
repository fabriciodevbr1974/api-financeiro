package br.dev.fabricio.api_financeiro.domain.port.out;

import br.dev.fabricio.api_financeiro.domain.model.Caixa;

public interface CaixaRepository {
  Caixa insert(Caixa caixa);
}
