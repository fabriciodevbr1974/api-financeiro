package br.dev.fabricio.api_financeiro.exceptions;

public class LancamentoNaoEncontradoException extends RuntimeException {
  public LancamentoNaoEncontradoException(String message) {
    super(message);
  }
}
