package br.dev.fabricio.api_financeiro.repository;

import br.dev.fabricio.api_financeiro.entity.CaixaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;

@Repository
public interface CaixaRepository extends JpaRepository<CaixaEntity, Long> {

  @Query("SELECT COALESCE(SUM(c.valor), 0) FROM CaixaEntity c " +
          "WHERE c.data BETWEEN :dataInicial AND :dataFinal AND c.tipo = :tipo")
  BigDecimal somarValorPorIntervaloDeDatas(
          @Param("dataInicial") LocalDate dataInicial,
          @Param("dataFinal") LocalDate dataFinal,
          @Param("tipo") String tipo);



}
