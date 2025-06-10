package br.dev.fabricio.api_financeiro.adapter.out;

import br.dev.fabricio.api_financeiro.domain.model.Caixa;
import br.dev.fabricio.api_financeiro.domain.port.out.CaixaRepositoryPortOut;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CaixaRepositoryImplAdapterOut implements CaixaRepositoryPortOut {

  private static List<Caixa> repositorio = new ArrayList<>();
  private static Long proximoId = 0L;


  @Override
  public Caixa insert(Caixa caixa) {
    proximoId++;
    caixa.setId(proximoId);
    repositorio.add(caixa);
    return caixa;
  }

  @Override
  public Caixa findById(Long id) {


    for (Caixa caixa : repositorio) {
      if (caixa.getId().equals(id)) {
        return caixa;
      }
    }
    return null;

  }

  @Override
  public Caixa update(Caixa caixa) {

    Caixa caixaCadastrado = findById(caixa.getId());

    if (caixaCadastrado == null) {
      return null;
    }

    caixaCadastrado.setValor(caixa.getValor());
    caixaCadastrado.setDescricao(caixa.getDescricao());
    caixaCadastrado.setData(caixa.getData());
    caixaCadastrado.setTipo(caixa.getTipo());


    return caixaCadastrado;
  }


  @Override
  public List<Caixa> findByData(LocalDate data) {

    List<Caixa> list = new ArrayList<>();

    for (Caixa caixa : repositorio) {
      if (caixa.getData().equals(data)) {
        list.add(caixa);
      }
    }

    return list;
  }

  @Override
  public void delete(Long id) {
    Caixa caixa = findById(id);
    if(caixa != null){
      repositorio.remove(caixa);
    }

  }
}
