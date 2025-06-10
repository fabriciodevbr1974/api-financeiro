package br.dev.fabricio.api_financeiro.config;

import br.dev.fabricio.api_financeiro.application.service.CaixaServiceImpl;
import br.dev.fabricio.api_financeiro.domain.port.in.CaixaService;
import br.dev.fabricio.api_financeiro.domain.port.out.CaixaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigBean {


  @Bean
  public CaixaService getCaixaService(CaixaRepository caixaRepository) {
    return new CaixaServiceImpl(caixaRepository);

  }


}
