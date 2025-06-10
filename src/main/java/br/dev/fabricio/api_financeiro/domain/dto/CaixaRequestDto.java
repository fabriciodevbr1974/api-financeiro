package br.dev.fabricio.api_financeiro.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CaixaRequestDto {

  private String descricao;
  private LocalDate data;
  private BigDecimal valor;
  private String tipo;//Entrada ou Saída

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public LocalDate getData() {
    return data;
  }

  public void setData(LocalDate data) {
    this.data = data;
  }

  public BigDecimal getValor() {
    return valor;
  }

  public void setValor(BigDecimal valor) {
    this.valor = valor;
  }

  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }
}
