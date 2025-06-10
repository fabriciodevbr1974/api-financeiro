package br.dev.fabricio.api_financeiro.config;

import br.dev.fabricio.api_financeiro.adapter.out.CaixaRepositoryImpl;
import br.dev.fabricio.api_financeiro.domain.port.out.CaixaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigBean {

  @Bean
  public CaixaRepository getCaixaRepository(){
    return new CaixaRepositoryImpl();

  }
}
