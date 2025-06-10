package br.dev.fabricio.api_financeiro.config;

import br.dev.fabricio.api_financeiro.application.service.CaixaServiceImpl;
import br.dev.fabricio.api_financeiro.domain.port.in.CaixaServicePortIn;
import br.dev.fabricio.api_financeiro.domain.port.out.CaixaRepositoryPortOut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigBean {


  @Bean
  public CaixaServicePortIn getCaixaService(CaixaRepositoryPortOut caixaRepository) {
    return new CaixaServiceImpl(caixaRepository);

  }


}
