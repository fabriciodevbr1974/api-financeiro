package br.dev.fabricio.api_financeiro.repository;

import br.dev.fabricio.api_financeiro.entity.CaixaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaixaRepository extends JpaRepository<CaixaEntity, Long> {
}
