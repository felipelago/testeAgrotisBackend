package com.teste.agrotis.pessoa;

import com.teste.agrotis.pessoa.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    @Query("SELECT p FROM Pessoa p " +
            "JOIN FETCH p.propriedade prop " +
            "JOIN FETCH p.laboratorio lab")
    List<Pessoa> findAllWithRelationships();

    @Query("SELECT p FROM Pessoa p WHERE " +
            "(:dataInicialStart IS NULL OR p.dataInicial >= :dataInicialStart) AND " +
            "(:dataInicialEnd IS NULL OR p.dataInicial <= :dataInicialEnd) AND " +
            "(:dataFinalStart IS NULL OR p.dataFinal >= :dataFinalStart) AND " +
            "(:dataFinalEnd IS NULL OR p.dataFinal <= :dataFinalEnd) AND " +
            "(:observacoes IS NULL OR LOWER(p.observacoes) LIKE LOWER(CONCAT('%', :observacoes, '%')))")
    List<Pessoa> findPessoasComFiltros(
            @Param("dataInicialStart") LocalDateTime dataInicialStart,
            @Param("dataInicialEnd") LocalDateTime dataInicialEnd,
            @Param("dataFinalStart") LocalDateTime dataFinalStart,
            @Param("dataFinalEnd") LocalDateTime dataFinalEnd,
            @Param("observacoes") String observacoes
    );
}
